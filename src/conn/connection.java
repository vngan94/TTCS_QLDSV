/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class connection {
    private Connection conn=null;
    public connection() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = (Connection) DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename="
                    + "QLDSV_TC;"
                    + "username=sa;password=123;");   
    }  
    public Connection getConnect(){
        return this.conn;
    }
}
