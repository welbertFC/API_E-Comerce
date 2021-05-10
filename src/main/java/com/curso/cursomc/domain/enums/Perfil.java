package com.curso.cursomc.domain.enums;

public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");


    private Integer codigo;
    private String descricao;

    private Perfil(Integer cod, String desc) {
        this.codigo = cod;
        this.descricao = desc;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }

        for (Perfil x : Perfil.values()) {
            if(codigo.equals(x.getCodigo())){
                return x;
            }

        }

        throw new IllegalArgumentException("Id Invalido" + codigo);
    }
}
