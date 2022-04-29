package com.prueba.bartender.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.bartender.entity.ListaArray;
import com.prueba.bartender.repository.ArraysRepository;

/*
 * Clase donde se implementa el metodo del repositorio
 */

@Service
public class ArraysImpl {
	
	@Autowired
	ArraysRepository arraysRepository;

	public Optional<ListaArray> findById(Integer id){
        return arraysRepository.findById(id);
    }
}
