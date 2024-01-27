package com.joel.br.CatalogExpert.exceptions;

import lombok.Data;

import java.time.Instant;
@Data
public class FieldMessage {

    private String message;
    private Instant timestamp;
    private String status;
    private String error;
    private String path;


}
