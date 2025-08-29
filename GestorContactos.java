import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorContactos {
    private List<Contacto> contactos;
    private String archivo = "contactos.txt";
    
    public GestorContactos() {
        contactos = new ArrayList<>();
        cargarContactos();
    }
    
    public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
    }
    
    public void eliminarContacto(int indice) {
        if (indice >= 0 && indice < contactos.size()) {
            contactos.remove(indice);
        }
    }
    
    public void modificarContacto(int indice, Contacto contacto) {
        if (indice >= 0 && indice < contactos.size()) {
            contactos.set(indice, contacto);
        }
    }
    
    public List<Contacto> getContactos() {
        return contactos;
    }
    
    public void cargarContactos() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    contactos.add(new Contacto(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
        }
    }
    
    public void guardarContactos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Contacto contacto : contactos) {
                pw.println(contacto.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
