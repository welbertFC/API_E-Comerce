package com.curso.cursomc.domain.enums;

public enum EstadoPagamento {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private Integer codigo;
    private String descricao;

   private EstadoPagamento(Integer cod, String desc) {
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

    public static EstadoPagamento toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }

        for (EstadoPagamento x : EstadoPagamento.values()) {
            if(codigo.equals(x.getCodigo())){
                return x;
            }

        }

        throw new IllegalArgumentException("Id Invalido" + codigo);
    }
}
