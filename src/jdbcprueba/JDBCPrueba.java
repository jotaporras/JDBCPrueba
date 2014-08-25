/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcprueba;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

/**
 *
 * @author Javier
 */
public class JDBCPrueba {

    /**
     * @param args the command line arguments
     */
    public static Connection ConnectDB() {
        try {
            // TODO code application logic here
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
            return con;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCPrueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        Connection con = ConnectDB();
        PreparedStatement pst;
        ResultSet rs;
        if (con != null) {
            System.out.println("WUJU SI SE PUDO");
            String sql = "select * from a1";
            try {/*
                 pst = con.prepareStatement(sql);
                 //pst.setString(1,"c##jota");
                 //pst.setString(2,"jota");
                 rs =  pst.executeQuery();
                 while(rs.next()){
                 System.out.println("Datos: " +rs.getInt("A")+"\t\t\t"+rs.getInt("B")+"\t\t\t"+rs.getInt("C"));
                 }*/

                sql = "Select t.tablespace_name \"Tablespace\", t.status \"Estado\",\n"
                        + "ROUND(MAX(d.bytes)/1024/1024,2) \"MB Tamaño\",\n"
                        + "ROUND((MAX(d.bytes)/1024/1024) -\n"
                        + "(SUM(decode(f.bytes, NULL,0, f.bytes))/1024/1024),2) \"MB Usados\",\n"
                        + "ROUND(SUM(decode(f.bytes, NULL,0, f.bytes))/1024/1024,2) \"MB Libres\",\n"
                        + "t.pct_increase \"% incremento\",\n"
                        + "SUBSTR(d.file_name,1,80) \"Fichero de datos\"\n"
                        + "FROM DBA_FREE_SPACE f, DBA_DATA_FILES d, DBA_TABLESPACES t\n"
                        + "WHERE t.tablespace_name = d.tablespace_name AND\n"
                        + "f.tablespace_name(+) = d.tablespace_name\n"
                        + "AND f.file_id(+) = d.file_id GROUP BY t.tablespace_name,\n"
                        + "d.file_name, t.pct_increase, t.status ORDER BY 1,3 DESC";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                List<String> tbsNames = new ArrayList<String>();
                int i=0;
                while(rs.next()){
                    tbsNames.add(rs.getString("Tablespace"));
                    System.out.println(tbsNames.get(i++));
                }
                

            } catch (SQLException ex) {
                Logger.getLogger(JDBCPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("NOOO NO SE PUDO T_T");
        }

    }

}
