/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author iran
 */
public class Teste {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Unable to load MySQL Driver");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        String jdbcUrl = "jdbc:mysql://localhost/programas?user=root&password=anabolero";
        Connection con = DriverManager.getConnection(jdbcUrl);
        Statement stmt = con.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.print("Seu email ");
        String email = sc.next();
        System.out.print("Sua senha ");
        String senha = sc.next();
        //String SQL = "INSERT INTO `jma`(`email`,`senha`,`situacao`) VALUES ('"+email+"','"+senha+"',1"+")";
        //stmt.execute(SQL);
        Properties properties = System.getProperties();
        Set<Object> keys = properties.keySet();
        for(Object key : keys){
            System.out.println(key + ": " + properties.get(key));
        }

        try {
            String result = null;
            Process p = Runtime.getRuntime().exec("wmic baseboard get serialnumber");
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            if (result.equalsIgnoreCase(" ")) {
                System.out.println("Result is empty");
            } else {
                System.out.println(result);
            }
            input.close();
        } catch (IOException ex) {
           
        }

        /*
        rs.next();
        while(!rs.isLast()){
            rs.next();
            int id_coluna = rs.getInt("id");
            String nome = rs.getString("nome");
            System.out.println(id_coluna+" "+nome+" ");
        }*/
        con.close();
    }

}
