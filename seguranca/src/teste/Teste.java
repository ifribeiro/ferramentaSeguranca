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
     * @param args the command line argifrasuments
     */
    public static void main(String[] args) throws SQLException, IOException {
        //String jdbcUrl = "jdbc:mysql://localhost/programas?user=root&password=anabolero";
        Teste t = new Teste();
        String jdbcUrl = "jdbc:mysql://localhost/programas?user=root";
        Connection con = DriverManager.getConnection(jdbcUrl);
        Statement stmt = con.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.print("Seu email ");
        String email = sc.next();
        System.out.print("Sua senha ");
        String senha = sc.next();
        //String SQL = "INSERT INTO `jma`(`email`,`senha`,`situacao`) VALUES ('"+email+"','"+senha+"',1"+")";
        //stmt.execute(SQL);
        if(!t.isRegistrado(stmt)){
            //exibir janela indicando que o computador nao esta registrado
            //sair do jogo
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
    
    public boolean isRegistrado(Statement stmt) throws SQLException{
        String serialNumber = numeroRegistro();
        boolean registrado = false;
        if(serialNumber.isEmpty()){
            registrado = false;
        }else{
            
            registrado = true;
        }
        /**
        String SQL = "SELECT `situacao` FROM `jma` WHERE senha='"+serialNumber+"'";
        stmt.execute(SQL);
        stmt.getResultSet();*/      
               
        return registrado;
    }

    private String numeroRegistro() {
        String SO = getSO();//versao do SO
        String numeroRegistro = "";
        switch(SO){
            case "WINDOWS":
                numeroRegistro = getNumeroRegistroWindows();
                break;
            case "linux":
                numeroRegistro = getNumeroRegistroLinux();
                break;   
        }        
        return numeroRegistro;       
    }

    private String getSO() {
        //fazer funçao que retorna o SO
        String SO = "";
        String nome = System.getProperty("os.name");
        nome = nome.toUpperCase();
        if(nome.contains("WINDOWS")){
            SO = "WINDOWS";
        }else{
            SO = "LINUX";
        }
        System.out.println("SO "+SO);
        return SO;
    }

    private String getNumeroRegistroWindows() {
        String result = "";
        try {            
            Process p = Runtime.getRuntime().exec("wmic baseboard get serialnumber");
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (IOException ex) {
           
        }
        String[] numero = result.split( " " );        
        return numero[2];
    }

    private String getNumeroRegistroLinux() {
        //fazer versao do linux
        return "";
    }
}