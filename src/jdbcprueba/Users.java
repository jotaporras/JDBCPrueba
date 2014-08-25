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
public class Users {
    private ArrayList<String> listaNombres = new ArrayList<>();
    private HashMap<String,UserData> hashUsuarios = new HashMap<String, UserData>();
    
     public void updateUsers(String nombre,String defaultTBS,String tempTBS,String estado) {     
        UserData values;
        if(hashUsuarios.containsKey(nombre)){
            values = hashUsuarios.get(nombre);
            values.setDefaultTBS(defaultTBS);
            values.setAccountStatus(estado);
        }
        else values = new UserData(defaultTBS,estado);
        hashUsuarios.put(nombre, values);//No se si se ocupa para el primer caso.
    }
    
    
    
}
