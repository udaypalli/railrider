/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package railrider.standards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MSI
 */
public class DatabaseHandler {
     private Connection conn;
     public DatabaseHandler() throws SQLException {
        conn = DBConnection.getDBConnection();
    }
     public ResultSet executeQuery(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
     
      public void executeUpdate(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }
      public void executeInsertPrepare(String sql,String[] parameters) throws SQLException{
        PreparedStatement statement =conn.prepareStatement(sql);
        int length = parameters.length;
        for(int i=1;i<=length;i++)
        {
        statement.setString(i,parameters[i-1]);
        }
        statement.executeUpdate();
        
    }
      public ResultSet executePrepare(String sql,String[] parameters) throws SQLException{
        PreparedStatement statement =conn.prepareStatement(sql);
        int length = parameters.length;
        for(int i=1;i<=length;i++)
        {
        statement.setString(i,parameters[i-1]);
        }
        ResultSet result = statement.executeQuery();
        return result;
    }
    public void handleException(SQLException ex) {
        ex.printStackTrace();
    }
}
