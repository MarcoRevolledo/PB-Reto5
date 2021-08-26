package modelo;

public class Persona {
    private int id,cant_agnos;
    private String nombre, apellido, tipo_doc;
    
      public Persona() {
      
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCant_agnos() {
        return cant_agnos;
    }

    public void setCant_agnos(int cant_agnos) {
        this.cant_agnos = cant_agnos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

  

    
}