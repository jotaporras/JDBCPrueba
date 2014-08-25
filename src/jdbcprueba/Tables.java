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
public class Tables {
    ArrayList<String> tableList = new ArrayList<>();
    HashMap<String,TableValues> hashTables = new HashMap<>();

    public ArrayList<String> getTableList() {
        return tableList;
    }

    public void setTableList(ArrayList<String> tableList) {
        this.tableList = tableList;
    }

    public HashMap<String, TableValues> getHashTables() {
        return hashTables;
    }

    public void setHashTables(HashMap<String, TableValues> hashTables) {
        this.hashTables = hashTables;
    }
    
    public void updateTables(String name,String owner,float usedSpace,int numRows,int avgRowLen,String tablespace) {     
        TableValues values;
        if(hashTables.containsKey(name)){
            values = hashTables.get(name);
            values.setOwner(owner);
            values.setUsedSpace(usedSpace);
            values.setNumRows(numRows);
            values.setAvgRowLen(avgRowLen);
            values.setTablespace(tablespace);
        }
        else values = new TableValues(owner, usedSpace, numRows, avgRowLen,tablespace);
        hashTables.put(name, values);//No se si se ocupa para el primer caso.
    }
}
