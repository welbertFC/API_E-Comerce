package com.curso.cursomc.resources.exception;

import lombok.Data;

import java.io.Serializable;


public class FieldMessage implements Serializable {

    private String fieldMessage;
    private String message;

    public FieldMessage() {

    }

    public FieldMessage(String fieldMessage, String message) {
        this.fieldMessage = fieldMessage;
        this.message = message;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
