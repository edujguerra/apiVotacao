package br.com.edujguerra.msvotacao.controller;

import br.com.edujguerra.msvotacao.model.Voto;
import br.com.edujguerra.msvotacao.service.VotoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votos")
public class VotoController {

    private final VotoServiceImpl service;

    public VotoController(VotoServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<Voto> buscarTodos() {

        return service.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody Voto voto){

        voto = service.salvar(voto);
        return new ResponseEntity<>(voto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarUm(@PathVariable Long id) {

        return service.buscarUm(id);
    }
}
