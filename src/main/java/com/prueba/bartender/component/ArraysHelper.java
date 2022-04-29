package com.prueba.bartender.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.springframework.stereotype.Component;

/*
 * En esta clase llamada ArraysHelper, es donde colocamos toda la lógica del ejercicio
 * Donde determinamos si un numero es primo o no, donde obtenemos el enesimo primo de los números naurales positivos
 * Donde se construye un array con la respuesta donde se va agregando los elementos de cuyo numero sea divisible entre el primo pi.
 */

@Component
public class ArraysHelper {
	
	// Este metodo verifica si un número real es primo o no
    private boolean numeroEsPrimo(int numero) {
        Boolean esPrimoActual = true;
        if(numero<2)
        {
            esPrimoActual = false;
        }
        else
        {
            for(int x=2; x*x<=numero; x++)
            {
                if( numero%x==0 ){esPrimoActual = false;break;}
            }
        }
        return esPrimoActual;
    }
    
	// En este metodo obtiene el enesimo primo de los números naurales positivos.
    private int nPrimo(int n) {
        int numero = 0;
        int cantidad = 0;

        while (cantidad < n) {
            if (numeroEsPrimo(numero)) {
                cantidad++;
            }
            numero++;
        }
        return numero - 1;
    }
    
	// En este metodo agregamos los numeros a la respuesta
    private void agregaArray(ArrayList<Integer> arr, ArrayList<Integer> res) {
        Iterator<Integer> it = arr.iterator();
        while (it.hasNext()) {
            res.add(it.next());
            it.remove();
        }
    }
    
	// Este metodo recibe la cadena de números y la convierte en ArrayList de números.
    private ArrayList<Integer> stringArray(String cadena) {
        String[] array = cadena.split(",");

        ArrayList<String> ar = new ArrayList<String>(Arrays.asList(array));
        ArrayList<Integer> aI = new ArrayList<Integer>();

        for (String i : ar) {
            aI.add(Integer.parseInt(i));
        }
        return aI;
    }
    
    // Construye el array de respuesta donde se van agregando los vasos
    // cuyo número es divisible por el enesimo primo Pi y agrega los restantes al final
    public ArrayList<Integer> respuesta(int q, String cadena){

        ArrayList<Integer> a = stringArray(cadena);
        ArrayList<Integer> b = new ArrayList<Integer>();
        ArrayList<Integer> respuesta = new ArrayList<Integer>();

        for (int itera = 1; itera <= q; itera++) {

            Iterator<Integer> it = a.iterator();
            while (it.hasNext()) {
                int p = it.next();
                if (p % nPrimo(itera) == 0) { 
                    b.add(0, p); 
                    it.remove(); 
                }
            }
         // Se agrega los numeros que cumplen la condición despues de la respuesta anterior
            agregaArray(b,respuesta);
        }
     // Como Q que es la iteracion ya se completó,se agrega los elementos restantes a la respuesta
        agregaArray(a,respuesta);
        return respuesta;
    }
}
