package br.com.edujguerra.msvotacao.repository;

import br.com.edujguerra.msvotacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta,Long> {
}
