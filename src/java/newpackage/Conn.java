/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.sql.*;

public class Conn {

    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/registration", "root", "");
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }

    private static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Database.close() Error -->" + ex.getMessage());
        }
    }

    public static boolean Validate(String user, String pass) {
        Connection conn = null;

        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM register WHERE username='" + user + "' AND password='" + pass + "'");

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            close(conn);
        }
        return false;
    }

    public static boolean Register(String user, String pass,String City,String dob) {
        Connection conn = null;

        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO register VALUES( NULL,'" + user + "', '" + pass + "', '" + City + "', '" + dob + "')";
            stmt.execute(query);
        } catch (SQLException ex) {
            System.out.println("signup error -->" + ex.getMessage());
            return false;
        } finally {
            close(conn);
        }
        return false;
    }
}
