package br.com.edujguerra.msvotacao.controller;

import br.com.edujguerra.msvotacao.model.Usuario;
import br.com.edujguerra.msvotacao.service.UsuarioService;
import br.com.edujguerra.msvotacao.service.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl service;

    public UsuarioController(UsuarioServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> buscarTodos() {

        return service.buscarTodos();
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody Usuario usuario){

        usuario = service.salvar(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarUm(@PathVariable Integer id) {

        return service.buscarUm(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Integer id, @RequestBody Usuario novo) {

        return service.atualizar(id,novo);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {

        service.excluir(id);
    }
}
