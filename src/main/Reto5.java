
package main;

import modelo.Persona;
import Controladores.Controlador;
        
import vistas.VentanaPrincipal;
public class Reto5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Persona mod = new Persona();
        VentanaPrincipal vista = new VentanaPrincipal();
        
        Controlador ctrl = new Controlador(mod,vista);
        ctrl.iniciar();    }
    
}
