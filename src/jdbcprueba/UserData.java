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
    private ArrayList<String> ownedTables;

    public UserData(String defaultTBS, String accountStatus) {
        this.defaultTBS = defaultTBS;
        this.accountStatus = accountStatus;
        this.ownedTables = ownedTables;
    }

    public UserData() {
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

    public ArrayList<String> getOwnedTables() {
        return ownedTables;
    }

    public void setOwnedTables(ArrayList<String> ownedTables) {
        this.ownedTables = ownedTables;
    }

    public String getDefaultTBS() {
        return defaultTBS;
    }

}
