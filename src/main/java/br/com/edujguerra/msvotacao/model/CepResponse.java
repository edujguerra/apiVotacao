package br.com.edujguerra.msvotacao.model;

import lombok.Data;

@Data
public class CepResponse {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
