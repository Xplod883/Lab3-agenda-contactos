import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class AgendaContactos extends JFrame {
    private GestorContactos gestor;
    private DefaultListModel<String> listModel;
    private JList<String> listaContactos;
    private JTextField campoNombre, campoTelefono;
    private JButton btnAgregar, btnModificar, btnEliminar;
    private int indiceSeleccionado = -1;
    
    public AgendaContactos() {
        gestor = new GestorContactos();
        initComponents();
        cargarContactosEnLista();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gestor.guardarContactos();
            }
        });
    }
    
    private void initComponents() {
        setTitle("Gestión de Contactos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelEntrada = new JPanel(new GridLayout(2, 2, 5, 5));
        panelEntrada.add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        panelEntrada.add(campoNombre);
        
        panelEntrada.add(new JLabel("Teléfono:"));
        campoTelefono = new JTextField();
        panelEntrada.add(campoTelefono);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        
        listModel = new DefaultListModel<>();
        listaContactos = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listaContactos);
        
        panel.add(panelEntrada, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        add(panel);
        
        configurarEventos();
    }
    
    private void configurarEventos() {
        btnAgregar.addActionListener(e -> agregarContacto());
        
        btnModificar.addActionListener(e -> modificarContacto());
        
        btnEliminar.addActionListener(e -> eliminarContacto());
        
        listaContactos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                indiceSeleccionado = listaContactos.getSelectedIndex();
                if (indiceSeleccionado != -1) {
                    Contacto contacto = gestor.getContactos().get(indiceSeleccionado);
                    campoNombre.setText(contacto.getNombre());
                    campoTelefono.setText(contacto.getTelefono());
                    btnModificar.setEnabled(true);
                    btnEliminar.setEnabled(true);
                }
            }
        });
        
        // Evento para limpiar selección al hacer doble clic
        listaContactos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    listaContactos.clearSelection();
                    indiceSeleccionado = -1;
                    campoNombre.setText("");
                    campoTelefono.setText("");
                    btnModificar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                }
            }
        });
    }
    
    private void agregarContacto() {
        String nombre = campoNombre.getText().trim();
        String telefono = campoTelefono.getText().trim();
        
        if (nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        gestor.agregarContacto(new Contacto(nombre, telefono));
        listModel.addElement(nombre + " - " + telefono);
        
        campoNombre.setText("");
        campoTelefono.setText("");
    }
    
    private void modificarContacto() {
        if (indiceSeleccionado == -1) {
            return;
        }
        
        String nombre = campoNombre.getText().trim();
        String telefono = campoTelefono.getText().trim();
        
        if (nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        gestor.modificarContacto(indiceSeleccionado, new Contacto(nombre, telefono));
        listModel.set(indiceSeleccionado, nombre + " - " + telefono);
        
        campoNombre.setText("");
        campoTelefono.setText("");
        listaContactos.clearSelection();
        indiceSeleccionado = -1;
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }
    
    private void eliminarContacto() {
        if (indiceSeleccionado == -1) {
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de que desea eliminar este contacto?", 
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            gestor.eliminarContacto(indiceSeleccionado);
            listModel.remove(indiceSeleccionado);
            
            campoNombre.setText("");
            campoTelefono.setText("");
            indiceSeleccionado = -1;
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }
    }
    
    private void cargarContactosEnLista() {
        for (Contacto contacto : gestor.getContactos()) {
            listModel.addElement(contacto.getNombre() + " - " + contacto.getTelefono());
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AgendaContactos().setVisible(true);
        });
    }
}
