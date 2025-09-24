package com.crm.franquias.model;

public class Franqueador extends Usuario {
    
    private String nome_empresa;

    public Franqueador(int id, String email, String nome_usuario, String senha, String nome_empresa) {
        super(id, email, nome_usuario, senha);
        this.nome_empresa = nome_empresa;
    }

    public String getNome_empresa() {
        return this.nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    @Override
    public String toString() {
        return "Nome do Franqueador: " + getNome_usuario() + "\n" + "Franqueadora: " + nome_empresa;
    }
}