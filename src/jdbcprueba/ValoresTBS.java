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
public class ValoresTBS {
    boolean estado;
    int tamTotal;
    int tamUsado;

    public ValoresTBS(boolean estado, int tamTotal, int tamUsado) {
        this.estado = estado;
        this.tamTotal = tamTotal;
        this.tamUsado = tamUsado;
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
}
