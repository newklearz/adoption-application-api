package com.p5.adoptions.service.exceptions;

import java.io.Serializable;

public class Violation implements  Serializable{
   private String field;

   private String message;

   private String receivedValued;

    public Violation() {
    }

    public Violation(String field, String message, String receivedValued) {
        this.field = field;
        this.message = message;
        this.receivedValued = receivedValued;
    }

    public Violation(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public Violation setField(String field) {
        this.field = field;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Violation setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getReceivedValued() {
        return receivedValued;
    }

    public Violation setReceivedValued(String receivedValued) {
        this.receivedValued = receivedValued;
        return this;
    }
}
