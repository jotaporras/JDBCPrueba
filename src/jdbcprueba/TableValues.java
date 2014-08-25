/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbcprueba;

/**
 *
 * @author Javier
 */
public class TableValues {
    private String owner;
    private float usedSpace;//In KB.
    private int numRows;
    private int avgRowLen; 

    public TableValues(String owner, float usedSpace, int numRows, int avgRowLen) {
        this.owner = owner;
        this.usedSpace = usedSpace;
        this.numRows = numRows;
        this.avgRowLen = avgRowLen;
    }

    public TableValues() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public float getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(float usedSpace) {
        this.usedSpace = usedSpace;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getAvgRowLen() {
        return avgRowLen;
    }

    public void setAvgRowLen(int avgRowLen) {
        this.avgRowLen = avgRowLen;
    }
    
    
}
