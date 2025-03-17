package br.com.edujguerra.msvotacao.repository;

import br.com.edujguerra.msvotacao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
