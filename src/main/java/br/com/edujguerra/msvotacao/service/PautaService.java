package br.com.edujguerra.msvotacao.service;

import br.com.edujguerra.msvotacao.model.Pauta;
import org.springframework.http.ResponseEntity;

public interface PautaService {
    public Pauta salvar(Pauta pauta);

    public ResponseEntity<Object> buscarUm(Long id );

    public ResponseEntity<Object> atualizar(Long id, Pauta novo);

    public ResponseEntity<Object> excluir(Long id);
}
