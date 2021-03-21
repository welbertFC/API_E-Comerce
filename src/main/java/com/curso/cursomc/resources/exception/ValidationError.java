package com.curso.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError  extends  StandardError{

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }


    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addError(String fieldName, String messagem){
        erros.add(new FieldMessage(fieldName, messagem));
    }

}
