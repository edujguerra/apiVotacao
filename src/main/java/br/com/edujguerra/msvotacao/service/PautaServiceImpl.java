package br.com.edujguerra.msvotacao.service;

import br.com.edujguerra.msvotacao.model.Pauta;
import br.com.edujguerra.msvotacao.repository.PautaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    public PautaServiceImpl(PautaRepository repository) {
        this.pautaRepository = repository;
    }

    public List<Pauta> buscarTodos() {
        return pautaRepository.findAll();
    }

    public Pauta salvar(Pauta pauta) {

        ResponseEntity<Object> response = validaCampos(pauta);
        if (!response.getStatusCode().equals(HttpStatus.OK)  ){
            throw new NoSuchElementException("Pauta com problemas..." + response);
        }

        pauta = pautaRepository.save(pauta);
        return pauta;
    }

    private ResponseEntity<Object> validaCampos(Pauta pauta) {

        if (pauta.getNome() == null
                || pauta.getNome().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome não pode ser vazio.");
        }

        if (pauta.getHoraInicio() == null) {
            pauta.setHoraInicio(LocalDateTime.now());
        }

        //try {
            //String uriCep = "https://viacep.com.br/ws/" + cliente.getCep() + "/json/";
            //RestTemplate restTemplate = new RestTemplate();

            //CepResponse cepResponse = restTemplate.getForEntity(uriCep, CepResponse.class).getBody();
        //} catch (Exception err) {
        //    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cep não encontrado. ");
        //}
        return ResponseEntity.ok(pauta);
    }

    public ResponseEntity<Object> buscarUm(Long id ) {

        Pauta pauta = pautaRepository.findById(id).orElse(null);

        if (pauta != null) {
            return ResponseEntity.ok(pauta);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pauta não encontrada.");
        }
    }

    public ResponseEntity<Object> atualizar(Long id, Pauta novo) {

        Pauta existente = pautaRepository.findById(id).orElse(null);

        if (existente != null) {
            ResponseEntity<Object> response = validaCampos(novo);
            if (!response.getStatusCode().equals(HttpStatus.OK)  ){
                return response;
            }

            existente.setNome(novo.getNome());
            existente.setDuracao(novo.getDuracao());

            return ResponseEntity.ok(pautaRepository.save(existente));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pauta não encontrada.");
        }
    }

    public ResponseEntity<Object> excluir(Long id) {

        Pauta existente = pautaRepository.findById(id).orElse(null);

        if (existente != null) {
            pautaRepository.delete(existente);
            return ResponseEntity.ok("Pauta deletada");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Pauta não encontrada.");
        }

    }

    public static boolean isDateValid(String strDate) {
        String dateFormat = "dd/MM/uuuu";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
