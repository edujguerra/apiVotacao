package br.com.edujguerra.msvotacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_usuario", nullable = false)
    private int id;

    @NotBlank(message = "Nome não pode ser vazio.")
    @Column(name = "nm_usuario", nullable = false)
    private String nome;

    @NotBlank(message = "CPF não pode ser vazio.")
    @Column(name = "nr_cpf", nullable = false)
    private String cpf;
}
