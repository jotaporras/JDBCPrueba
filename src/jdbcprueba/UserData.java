/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcprueba;

import java.util.ArrayList;

/**
 *
 * @author Javier
 */
public class UserData {

    private String defaultTBS;
    private String tempTBS;
    private String accountStatus;

    public UserData(String defaultTBS, String tempTBS, String accountStatus) {
        this.defaultTBS = defaultTBS;
        this.tempTBS = tempTBS;
        this.accountStatus = accountStatus;
    }
    public UserData() {
    }


    public String getTempTBS() {
        return tempTBS;
    }

    public void setTempTBS(String tempTBS) {
        this.tempTBS = tempTBS;
    }

    
    public void setDefaultTBS(String defaultTBS) {
        this.defaultTBS = defaultTBS;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    
    public String getDefaultTBS() {
        return defaultTBS;
    }

}
