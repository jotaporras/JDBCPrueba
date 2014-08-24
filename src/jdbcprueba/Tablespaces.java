/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcprueba;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Javier
 */
public class Tablespaces {

    HashMap<String, ValoresTBS> hashTBS = new HashMap<>();
    ArrayList<String> nombres = new ArrayList<>();//Las llaves


    public ArrayList<String> getNombres() {
        return nombres;
    }

    public void setNombres(ArrayList<String> nombres) {
        this.nombres = nombres;
    }

    public ArrayList<String> getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(ArrayList<String> ubicacion) {
        this.ubicacion = ubicacion;
    }
    ArrayList<String> ubicacion = new ArrayList<>();

    public void updateTBS(String nombre, boolean estado, int tamTotal, int tamUsado) {     
        ValoresTBS values;
        if(hashTBS.containsKey(nombre)){
            values = hashTBS.get(nombre);
            values.setEstado(estado);
            values.setTamTotal(tamTotal);
            values.setTamUsado(tamUsado);
        }
        else values = new ValoresTBS(estado,tamTotal,tamUsado);
        hashTBS.put(nombre, values);//No se si se ocupa para el primer caso.
    }

}
