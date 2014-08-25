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
public class TBSValues {

    boolean estado;
    int tamTotal;
    int tamUsado;
    String dirDBF;

    public TBSValues(boolean estado, int tamTotal, int tamUsado, String dirDBF) {
        this.estado = estado;
        this.tamTotal = tamTotal;
        this.tamUsado = tamUsado;
        this.dirDBF = dirDBF;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getTamTotal() {
        return tamTotal;
    }

    public void setTamTotal(int tamTotal) {
        this.tamTotal = tamTotal;
    }

    public int getTamUsado() {
        return tamUsado;
    }

    public void setTamUsado(int tamUsado) {
        this.tamUsado = tamUsado;
    }

    public String getDirDBF() {
        return dirDBF;
    }

    public void setDirDBF(String dirDBF) {
        this.dirDBF = dirDBF;
    }
}
