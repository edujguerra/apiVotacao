package br.com.edujguerra.msvotacao.service;

import br.com.edujguerra.msvotacao.model.Voto;
import org.springframework.http.ResponseEntity;

public interface VotoService {
    public Voto salvar(Voto voto);

    public ResponseEntity<Object> buscarUm(Long id );

    public ResponseEntity<Object> atualizar(Long id, Voto novo);

    public ResponseEntity<Object> excluir(Long id);
}
