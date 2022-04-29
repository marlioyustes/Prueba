package com.prueba.bartender.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.bartender.Services.ArraysImpl;
import com.prueba.bartender.component.ArraysHelper;
import com.prueba.bartender.entity.ListaArray;

@RestController
@RequestMapping("/arrays")
public class ArraysController {

	@Autowired
	private ArraysImpl arraysImpl;

	@Autowired
	private ArraysHelper arrayslogica;
	
	//Creacion del servicio donde se encarga de preparar la respuesta con sus respectivos parámetros a enviar
	@GetMapping("/bartender")
	public ResponseEntity<?> resultado(@RequestParam Integer iteracion, @RequestParam Integer id) {

		ResponseEntity<ListaArray> entidad;
		String cadena;

		entidad = findById(id);
		cadena = entidad.getBody().getInputArray();
		ArrayList<Integer> rta = arrayslogica.respuesta(iteracion, cadena);
		String respuesta = "Respuesta= ";

		for (Integer dato : rta) {
			if (!respuesta.equals("Respuesta= "))
				respuesta = respuesta.concat(", ").concat(String.valueOf(dato));
			else
				respuesta = respuesta.concat(String.valueOf(dato));
		}

		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	}

	//Metodo de busqueda por JPA del registro existente en base de datos por id
	public ResponseEntity<ListaArray> findById(Integer id) {
		return arraysImpl.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	protected ResponseEntity<?> validar(BindingResult result) {
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El atributo" + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}

}
