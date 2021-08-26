package Controladores;

        

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import vistas.VentanaPrincipal;
import modelo.Conexion;

public class Controlador implements ActionListener {

    Conexion conectar = new Conexion();
    Connection connect = conectar.getConexion();

    //manejar el modelo y la vista
    private Persona mod; //mod.operacion a realizar llamar 
    private VentanaPrincipal vista;

    public Controlador(Persona mod, VentanaPrincipal vista) {
        this.mod = mod;
        this.vista = vista;

        //agregar los botones para que esten escuchando
        this.vista.btn_consultar.addActionListener(this);
        this.vista.btn_guardar.addActionListener(this);
        this.vista.btn_eliminar.addActionListener(this);
        this.vista.btn_editar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("MVC persona");
        vista.setLocationRelativeTo(null); //centro de nuestra pantalla
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //eventos de los botones
        //tabla
        DefaultTableModel tablamodelo = (DefaultTableModel) vista.tbl_usuarios.getModel();

        if (e.getSource() == vista.btn_consultar) {
            try {
                tablamodelo.setRowCount(0);
                ResultSet rs = null;
                PreparedStatement ps = connect.prepareStatement("SELECT * FROM personas"); //consulta a la bd
                rs = ps.executeQuery(); //ejecutar la consulta
                while (rs.next()) {
                    tablamodelo.addRow(new Object[]{rs.getString("tipo_doc"), rs.getInt("doc"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("cant_agnos")});
                }
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, ee.getMessage().toString());
            }
        } else if (e.getSource() == vista.btn_guardar) {
            try {
                tablamodelo.setRowCount(0);
                PreparedStatement ps = connect.prepareStatement("INSERT INTO personas (tipo_doc,doc,nombre,apellido,cant_agnos) VALUES (?,?,?,?,?)");
                
                ps.setString(1, vista.cbx_tipo_doc.getItemAt(vista.cbx_tipo_doc.getSelectedIndex()));
                
                System.out.println(vista.cbx_tipo_doc.getItemAt(vista.cbx_tipo_doc.getSelectedIndex()));
                ps.setInt(2, Integer.parseInt(vista.txt_doc.getText()));
                System.out.println(Integer.parseInt(vista.txt_doc.getText()));
                ps.setString(3, vista.txt_nombre.getText());
                System.out.println(vista.txt_nombre.getText());
                ps.setString(4, vista.txt_apellido.getText());
                System.out.println(vista.txt_apellido.getText());
                ps.setInt(5, (Integer)vista.spnn_cant.getValue());
                System.out.println((Integer)vista.spnn_cant.getValue());
                ps.execute();
                JOptionPane.showMessageDialog(null, "Datos guardados.");
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, "Ingrese los datos correctos.");
            }
        } else if (e.getSource() == vista.btn_eliminar) {
            try {
                tablamodelo.setRowCount(0);
                PreparedStatement ps = connect.prepareStatement("DELETE FROM personas WHERE doc=? and tipo_doc=?");
                ps.setInt(1, Integer.parseInt(vista.txt_doc.getText()));
                ps.setString(2,vista.cbx_tipo_doc.getSelectedItem().toString());
                ps.execute();
                
                
                /*
                System.out.println(r.getRow());
                
                if(elimino){
                   JOptionPane.showMessageDialog(null, "Datos eliminados."); 
                }else{
                    JOptionPane.showMessageDialog(null, "TIENE UN DATO ERRADO, VERIFIQUE");
                }
                */
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, ee.getMessage().toString());
            }
        } else if (e.getSource() == vista.btn_editar) {
            try {
                tablamodelo.setRowCount(0);
                PreparedStatement ps = connect.prepareStatement("UPDATE personas SET nombre=?,apellido=?, cant_agnos=? WHERE doc=? and tipo_doc=?");
                ps.setInt(4,Integer.parseInt(vista.txt_doc.getText()) );
                ps.setString(1, vista.txt_nombre.getText());
                ps.setString(2, vista.txt_apellido.getText());
                ps.setInt(3, (Integer)vista.spnn_cant.getValue());
                
                
                ps.setString(5, vista.cbx_tipo_doc.getSelectedItem().toString());
                ps.execute();
                JOptionPane.showMessageDialog(null, "Datos actualizados.");
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, "Ingrese datos a actualizar.");
            }
        }
    }
}
