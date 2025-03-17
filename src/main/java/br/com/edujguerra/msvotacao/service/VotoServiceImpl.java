package br.com.edujguerra.msvotacao.service;

import java.util.List;
import java.util.NoSuchElementException;

import br.com.edujguerra.msvotacao.model.Usuario;
import br.com.edujguerra.msvotacao.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.usuarioRepository = repository;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) {

        ResponseEntity<Object> response = validaCampos(usuario);
        if (!response.getStatusCode().equals(HttpStatus.OK)  ){
            throw new NoSuchElementException("Usuário com problemas..." + response);
        }

        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    private ResponseEntity<Object> validaCampos(Usuario usuario) {

        if (usuario.getNome() == null
                || usuario.getNome().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome não pode ser vazio.");
        }
        if (usuario.getCpf() == null ||
                usuario.getCpf().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF não pode ser vazio.");
        }

        try {
            //String uriCep = "https://viacep.com.br/ws/" + cliente.getCep() + "/json/";
            //RestTemplate restTemplate = new RestTemplate();

            //CepResponse cepResponse = restTemplate.getForEntity(uriCep, CepResponse.class).getBody();
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cep não encontrado. ");
        }
        return ResponseEntity.ok(usuario);
    }

    public ResponseEntity<Object> buscarUm(Long id ) {

        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado.");
        }
    }

    public ResponseEntity<Object> atualizar(Long id, Usuario novo) {

        Usuario existente = usuarioRepository.findById(id).orElse(null);

        if (existente != null) {
            ResponseEntity<Object> response = validaCampos(novo);
            if (!response.getStatusCode().equals(HttpStatus.OK)  ){
                return response;
            }

            existente.setNome(novo.getNome());
            existente.setCpf(novo.getCpf());

            return ResponseEntity.ok(usuarioRepository.save(existente));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado.");
        }
    }

    public ResponseEntity<Object> excluir(Long id) {

        Usuario existente = usuarioRepository.findById(id).orElse(null);

        if (existente != null) {
            usuarioRepository.delete(existente);
            return ResponseEntity.ok("Usuário deletado");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado.");
        }

    }
}
