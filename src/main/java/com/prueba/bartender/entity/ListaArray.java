package com.prueba.bartender.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ARRAYS")
public class ListaArray implements Serializable {

	private static final long serialVersionUID = 5358003203599544001L;
	
	@Id
	@Column(name = "ID")
    private Integer id;

    @Column(name = "INPUT_ARRAY")
    private String inputArray;

}
