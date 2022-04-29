package com.prueba.bartender.exception;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionsResponse {
	
    private String mensaje;
    private Map<String, String> erroresValidacion;
    
    public ExceptionsResponse(String mensaje) {
        this.mensaje = mensaje;
    }

}
