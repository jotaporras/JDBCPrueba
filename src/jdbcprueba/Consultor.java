/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcprueba;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

/**
 *
 * @author Javier
 */
public class Consultor {

    private static Connection con = null;/*Estatico. Por el momento solo se permite conexion a una sola base.*/


    public static boolean conectar(String user, String pass) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(Consultor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Consultor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static void getTBSpacesInfo(Tablespaces tbSpaces) {
        PreparedStatement pst;
        ResultSet rs;
        String sql = "Select t.tablespace_name \"Tablespace\", t.status \"Estado\","
                + "ROUND(MAX(d.bytes)/1024/1024,2) \"MB Tamaño\","
                + "ROUND((MAX(d.bytes)/1024/1024) -"
                + "(SUM(decode(f.bytes, NULL,0, f.bytes))/1024/1024),2) \"MB Usados\","
                + "ROUND(SUM(decode(f.bytes, NULL,0, f.bytes))/1024/1024,2) \"MB Libres\","
                + "t.pct_increase \"% incremento\","
                + "SUBSTR(d.file_name,1,80) \"DireccionDBF\""
                + "FROM DBA_FREE_SPACE f, DBA_DATA_FILES d, DBA_TABLESPACES t"
                + "WHERE t.tablespace_name = d.tablespace_name AND"
                + "f.tablespace_name(+) = d.tablespace_name"
                + "AND f.file_id(+) = d.file_id GROUP BY t.tablespace_name,"
                + "d.file_name, t.pct_increase, t.status ORDER BY 1,3 DESC";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("Tablespace");
                boolean estado = rs.getString("Estado").equals("ONLINE");//Habra que cambiarlo si hay mas de dos estados.
                int tamTotal = rs.getInt("MB Tamaño");
                int tamUsado = rs.getInt("MB Usados");
                String dirDBF = rs.getString("DireccionDBF");
                tbSpaces.updateTBS(nombre, estado, tamTotal, tamUsado, dirDBF);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*public void getTablespaceInfo(Tablespaces tbSpaces, String nombre) {
     PreparedStatement pst;
     ResultSet rs;
     String sql = "Select t.tablespace_name \"Tablespace\", t.status \"Estado\","
     + "ROUND(MAX(d.bytes)/1024/1024,2) \"MB Tamaño\","
     + "ROUND((MAX(d.bytes)/1024/1024) -"
     + "(SUM(decode(f.bytes, NULL,0, f.bytes))/1024/1024),2) \"MB Usados\","
     + "ROUND(SUM(decode(f.bytes, NULL,0, f.bytes))/1024/1024,2) \"MB Libres\","
     + "t.pct_increase \"% incremento\","
     + "SUBSTR(d.file_name,1,80) \"Fichero de datos\""
     + "FROM DBA_FREE_SPACE f, DBA_DATA_FILES d, DBA_TABLESPACES t"
     + "WHERE t.tablespace_name = d.tablespace_name AND"
     + "f.tablespace_name(+) = d.tablespace_name"
     + "AND f.file_id(+) = d.file_id GROUP BY t.tablespace_name,"
     + "d.file_name, t.pct_increase, t.status ORDER BY 1,3 DESC";
     try {
     pst = con.prepareStatement(sql);
     rs = pst.executeQuery();
     while (rs.next()) {
     String x = rs.getString("Tablespace");
     boolean estado = rs.getString("Estado").equals("ONLINE");//Habra que cambiarlo si hay mas de dos estados.
     int tamTotal = rs.getInt("MB Tamaño");
     int tamUsado = rs.getInt("MB Usados");
     // tbSpaces.updateTBS(x, estado, tamTotal, tamUsado);
     }
     } catch (SQLException ex) {
     Logger.getLogger(Consultor.class.getName()).log(Level.SEVERE, null, ex);
     }

     }*/
    public void getUsersInfo(Users users) {
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT username,\n"
                + "       default_tablespace,\n"
                + "       temporary_tablespace,\n"
                + "       ACCOUNT_STATUS,\n"
                + " FROM dba_users";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                String defaultTBS = rs.getString("default_tablespace");
                String tempTBS = rs.getString("temporary_tablespace");
                String status = rs.getString("ACCOUNT_STATUS");
                users.updateUsers(username, defaultTBS, tempTBS, status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getTablesInfo(Tables tables) {
        PreparedStatement pst;
        ResultSet rs;
        String sql = "select owner,table_name,tablespace_name,num_rows\n"
                + "from dba_tables\n"
                + "where \n"
                + "TABLESPACE_NAME NOT LIKE '%SYS%'\n"
                + "AND OWNER NOT LIKE '%SYS%'";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("table_name");
                String owner = rs.getString("owner");
                String TBSName = rs.getString("tablespace_name");
                int numRows = rs.getInt("num_rows");
                float tamTabla =  (float)1337.69; //WORK IN PROGRESS.
                tables.updateTables(name, owner, tamTabla, numRows, numRows);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
