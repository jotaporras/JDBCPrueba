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

    HashMap<String, TBSValues> hashTBS = new HashMap<>();
    ArrayList<String> nombres = new ArrayList<>();//Las llaves

    public ArrayList<String> getNombres() {
        return nombres;
    }

    public void setNombres(ArrayList<String> nombres) {
        this.nombres = nombres;
    }
    

    public void updateTBS(String name, boolean state, int totalSize, int usedSpace,String dirDBF) {     
        TBSValues values;
        if(hashTBS.containsKey(name)){
            values = hashTBS.get(name);
            values.setEstado(state);
            values.setTamTotal(totalSize);
            values.setTamUsado(usedSpace);
            values.setDirDBF(dirDBF);
        }
        else values = new TBSValues(state,totalSize,usedSpace,dirDBF);
        hashTBS.put(name, values);//No se si se ocupa para el primer caso.
    }

}
