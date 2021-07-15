package consolasql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
//cargar los datos    
    String driver = ("com.mysql.jdbc.Driver");
    String url = ("jdbc:mysql://localhost:3306/persona");
    String user = "root";
    String password = "";
    Connection conex = null;

    public Connection Conexion() {
    try {
        Class.forName(driver);
        conex = DriverManager.getConnection(url,user,password);
        System.out.println("conexion SQL con exito");
    } catch (ClassNotFoundException | SQLException ex) {
        System.out.println("conexion sin exito " + ex);
        
    }
    return conex;
    }  
    public static void main(String [] args){
        Conexion conectar = new Conexion();
        Connection conectado = conectar.Conexion();
        String SQL ="";
        String nombre;
        Scanner teclado = new Scanner(System.in);
            System.out.println("ingrese el nombre a guardar a la base de dato MYSQL");
            nombre=teclado.nextLine();
            SQL = "insert into alumno(nombre) values(?)";
            try {
                PreparedStatement preparar = conectado.prepareCall(SQL);
                preparar.setString(1, nombre);
                int n = preparar.executeUpdate();
                System.out.println("alumno guardado");
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}

