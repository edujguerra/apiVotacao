package br.com.edujguerra.msvotacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_pautas")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pauta", nullable = false)
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio.")
    @Column(name = "ds_pauta", nullable = false)
    private String nome;

    @Column(name = "dt_inicio", nullable = false)
    private LocalDateTime horaInicio;

    @Column(name = "nr_duracao", nullable = false)
    private int duracao = 1;
}
