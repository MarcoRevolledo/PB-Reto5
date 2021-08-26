package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    String url = "jdbc:sqlite:C:/reto5.db"; //ruta de acceso donde se almacena mi db
    Connection connect;
    
    public Connection getConexion(){
        try {
            System.out.println("PROCESANDO CONEXION");
            connect = DriverManager.getConnection(url);
            if(connect!=null){ //si se conecto a db
                System.out.println("CONEXXION EXITOSA");
                        }
        } catch (Exception e) {
            System.out.println("Problemas");
            JOptionPane.showMessageDialog(null, e.getMessage().toString());
            
        }
        return connect;
    }
    
}
