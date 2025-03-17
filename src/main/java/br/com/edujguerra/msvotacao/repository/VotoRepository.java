package br.com.edujguerra.msvotacao.repository;

import br.com.edujguerra.msvotacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto,Long> {
}
