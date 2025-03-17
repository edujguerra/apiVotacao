package br.com.edujguerra.msvotacao.service;

import br.com.edujguerra.msvotacao.model.Pauta;
import br.com.edujguerra.msvotacao.model.Usuario;
import br.com.edujguerra.msvotacao.model.Voto;
import br.com.edujguerra.msvotacao.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final PautaServiceImpl pautaServiceImpl;
    private final UsuarioServiceImpl usuarioServiceImpl;

    public VotoServiceImpl(VotoRepository repository, PautaServiceImpl pautaServiceImpl, UsuarioServiceImpl usuarioServiceImpl) {
        this.votoRepository = repository;
        this.pautaServiceImpl = pautaServiceImpl;
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    public List<Voto> buscarTodos() {
        return votoRepository.findAll();
    }

    public Voto salvar(Voto voto) {

        ResponseEntity<Object> response = validaCampos(voto);
        if (!response.getStatusCode().equals(HttpStatus.OK)  ){
            throw new NoSuchElementException("Voto com problemas..." + response);
        }

        voto = votoRepository.save(voto);
        return voto;
    }

    private ResponseEntity<Object> validaCampos(Voto voto) {

        if (voto.getPauta() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pauta não pode ser vazia.");
        }

        if (voto.getUsuario() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não pode ser vazio.");
        }
        if (voto.getStVoto() == null ||
                voto.getStVoto().isEmpty() ||
                (!voto.getStVoto().equals("S") && !voto.getStVoto().equals("N"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voto não pode ser vazio, e valores devem ser S ou N.");
        }

        ResponseEntity<Object> pauta =pautaServiceImpl.buscarUm(voto.getPauta().getId());
        if (pauta.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pauta não encontrada.");
        }
        voto.setPauta((Pauta) pauta.getBody());

        ResponseEntity<Object> usuario =usuarioServiceImpl.buscarUm(voto.getUsuario().getId());
        if (usuario.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        voto.setUsuario((Usuario) usuario.getBody());

        try {
            //String uriCep = "https://viacep.com.br/ws/" + cliente.getCep() + "/json/";
            //RestTemplate restTemplate = new RestTemplate();

            //CepResponse cepResponse = restTemplate.getForEntity(uriCep, CepResponse.class).getBody();
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cep não encontrado. ");
        }
        return ResponseEntity.ok(voto);
    }

    public ResponseEntity<Object> buscarUm(Long id ) {

        Voto voto = votoRepository.findById(id).orElse(null);

        if (voto != null) {
            return ResponseEntity.ok(voto);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Voto não encontrado.");
        }
    }

    public ResponseEntity<Object> atualizar(Long id, Voto novo) {

        Voto existente = votoRepository.findById(id).orElse(null);

        if (existente != null) {
            ResponseEntity<Object> response = validaCampos(novo);
            if (!response.getStatusCode().equals(HttpStatus.OK)  ){
                return response;
            }

            existente.setUsuario(novo.getUsuario());
            existente.setPauta(novo.getPauta());
            existente.setStVoto(novo.getStVoto());

            return ResponseEntity.ok(votoRepository.save(existente));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Voto não encontrado.");
        }
    }

    public ResponseEntity<Object> excluir(Long id) {

        Voto existente = votoRepository.findById(id).orElse(null);

        if (existente != null) {
            votoRepository.delete(existente);
            return ResponseEntity.ok("Voto deletado");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Voto não encontrado.");
        }

    }
}
