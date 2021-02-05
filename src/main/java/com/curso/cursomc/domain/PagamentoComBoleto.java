package com.curso.cursomc.domain;

import com.curso.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date datePagemento;

    public PagamentoComBoleto(){

    }


    public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date datePagemento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.datePagemento = datePagemento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDatePagemento() {
        return datePagemento;
    }

    public void setDatePagemento(Date datePagemento) {
        this.datePagemento = datePagemento;
    }
}
