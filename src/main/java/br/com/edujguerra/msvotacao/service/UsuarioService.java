package br.com.edujguerra.msvotacao.service;

import br.com.edujguerra.msvotacao.model.Usuario;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {
    public Usuario salvar(Usuario usuario);

    public ResponseEntity<Object> buscarUm(Long id );

    public ResponseEntity<Object> atualizar(Long id, Usuario novo);

    public ResponseEntity<Object> excluir(Long id);
}
