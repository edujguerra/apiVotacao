package br.com.edujguerra.msvotacao.controller;

import br.com.edujguerra.msvotacao.model.Pauta;
import br.com.edujguerra.msvotacao.model.Usuario;
import br.com.edujguerra.msvotacao.service.PautaServiceImpl;
import br.com.edujguerra.msvotacao.service.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pautas")
public class PautaController {

    private final PautaServiceImpl service;

    public PautaController(PautaServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<Pauta> buscarTodos() {

        return service.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody Pauta pauta){

        pauta = service.salvar(pauta);
        return new ResponseEntity<>(pauta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarUm(@PathVariable Long id) {

        return service.buscarUm(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody Pauta novo) {

        return service.atualizar(id,novo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {

        return service.excluir(id);
    }
}
