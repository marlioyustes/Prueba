package com.prueba.bartender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.bartender.entity.ListaArray;

/*
 * Clase donde se utiliza la interface JpaRepository para extraer las operaciones mas utilizadas del CRUD
 */

@Repository
public interface ArraysRepository extends JpaRepository<ListaArray,Integer> {

}
