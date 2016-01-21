package directorio;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

public class Interfaz {
    
    //Inicia declaracion de variables y componentes del menu principal
    JFrame frame;
    
    JMenuBar menuBar;
    
    JMenu menuArchivo;
    JMenuItem itemSalir;
    
    JMenu menuAccion;
    JMenuItem itemNuevo;
    JMenuItem itemModificar;
    JMenuItem itemBorrar;
    
    JMenu menuAyuda;
    JMenuItem itemAyuda;
    JMenuItem itemAbout;
    //Termina declaracion de variables y componentes del menu principal
    
    //Inicia declaracion de variables y componentes de modulo "Alta"
    JPanel panelAlta, panelAltaBotones;
    JInternalFrame iFAlta;
    
    JLabel labelAltaNombre;
    JTextField fieldAltaNombre;
    JLabel labelAltaApellidos;
    JTextField fieldAltaApellidos;
    JLabel labelAltaAvenida;
    JTextField fieldAltaAvenida;
    JLabel labelAltaNumero;
    JTextField fieldAltaNumero;
    JLabel labelAltaColonia;
    JTextField fieldAltaColonia;
    JLabel labelAltaCodigoPostal;
    JTextField fieldAltaCodigoPostal;
    JLabel labelAltaTelefono;
    JTextField fieldAltaTelefono;
    JButton botonAltaGuardar, botonAltaCancelar;
    //Termina declaracion de variables y componentes de modulo "Alta"
    
    //Inicia declaracion de variables y componentes de modulo "Modificar"
    JPanel panelModificar;
    JInternalFrame iFModificar;
    
    JComboBox<String> comboBuscar;
    JButton botonModificarGuardar;
    JButton botonModificarCancelar;
    //Termina declaracion de variables y componentes de modulo "Modificar"
    
    JDesktopPane desktopPane;
    Image icon;
    
    Conexion iConexion;
    
    public Interfaz()
    {
        iConexion = new Conexion();
        
        frame = new JFrame("Directorio");
        
        desktopPane = new JDesktopPane();
        frame.getContentPane().add(desktopPane);
        
        //Codigo para cambiar el icono en la parte superior izquierda del frame.
        try{
            icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/icon.png"));
            frame.setIconImage(icon);
        }
        catch(Exception e)
        {
            System.out.println("Error al cargar el ícono");
        }
        
        //Linea para que el frame aparezca maximizado por defecto.
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        frame.setMinimumSize(new Dimension (640,480));
        
        menuBar = new JMenuBar();
        
        menuArchivo = new JMenu("Archivo");
        itemSalir = new JMenuItem("Salir");
        menuArchivo.add(itemSalir);
        
        menuAccion = new JMenu("Accion");
        itemNuevo = new JMenuItem("Nuevo");
        itemModificar = new JMenuItem("Modificar");
        itemBorrar = new JMenuItem("Borrar");
        menuAccion.add(itemNuevo);
        menuAccion.add(itemModificar);
        menuAccion.add(itemBorrar);
        
        menuAyuda = new JMenu("Ayuda");
        itemAyuda = new JMenuItem("Ayuda");
        itemAbout = new JMenuItem("Acerca de");
        menuAyuda.add(itemAyuda);
        menuAyuda.add(itemAbout);
        
        menuBar.add(menuArchivo);
        menuBar.add(menuAccion);
        menuBar.add(menuAyuda);
        
        frame.setJMenuBar(menuBar);
        
        inicializarAlta();
        inicializarModificar();
        
        frame.setVisible(true);
        
        //Evento para cerrrar la ventana con el boton X de la barra superior
        frame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0);}});
        
        actionListeners();
    }
    
    private void inicializarAlta()
    {
        panelAlta = new JPanel();
        panelAlta.setLayout(new BoxLayout(panelAlta, BoxLayout.Y_AXIS));
        panelAltaBotones = new JPanel();
        panelAltaBotones.setLayout(new FlowLayout());
        
        iFAlta = new JInternalFrame("Agregar Contacto");
        
        labelAltaNombre = new JLabel("Nombre:");
        //labelAltaNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldAltaNombre = new JTextField(25);
        //fieldAltaNombre.setMinimumSize(new Dimension(25,250));
        
        labelAltaApellidos = new JLabel("Apellidos:");
        fieldAltaApellidos = new JTextField(25);
        //fieldApellidos.setMinimumSize(new Dimension(25,250));
        
        labelAltaTelefono = new JLabel("Telefono:");
        fieldAltaTelefono = new JTextField(10);
        
        labelAltaAvenida = new JLabel("Avenida:");
        fieldAltaAvenida = new JTextField(15);
        //fieldAvenida.setMinimumSize(new Dimension(25,250));
        
        labelAltaNumero = new JLabel("Numero:");
        fieldAltaNumero = new JTextField(10);
        //fieldNumero.setMinimumSize(new Dimension(25,100));
        
        labelAltaColonia = new JLabel("Colonia: ");
        fieldAltaColonia = new JTextField(15);
        //fieldColonia.setMinimumSize(new Dimension(25,250));
        
        labelAltaCodigoPostal = new JLabel("Codigo Postal:");
        fieldAltaCodigoPostal = new JTextField(10);
        //fieldCodigoPostal.setMinimumSize(new Dimension(25,250));
        
        botonAltaGuardar = new JButton("Guardar");
        botonAltaCancelar = new JButton("Cancelar");
        
        panelAltaBotones.add(botonAltaGuardar);
        panelAltaBotones.add(botonAltaCancelar);
        //Alinea todos los componentes a la izquierda del panel.
        panelAltaBotones.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelAlta.add(labelAltaNombre);
        panelAlta.add(fieldAltaNombre);
        panelAlta.add(labelAltaApellidos);
        panelAlta.add(fieldAltaApellidos);
        panelAlta.add(labelAltaTelefono);
        panelAlta.add(fieldAltaTelefono);
        panelAlta.add(labelAltaAvenida);
        panelAlta.add(fieldAltaAvenida);
        panelAlta.add(labelAltaNumero);
        panelAlta.add(fieldAltaNumero);
        panelAlta.add(labelAltaColonia);
        panelAlta.add(fieldAltaColonia);
        panelAlta.add(labelAltaCodigoPostal);
        panelAlta.add(fieldAltaCodigoPostal);
        panelAlta.add(panelAltaBotones);
        
        iFAlta.add(panelAlta);
        iFAlta.pack();
        iFAlta.setClosable(true);
        iFAlta.setMaximizable(true);
        iFAlta.setResizable(true);
        iFAlta.setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        desktopPane.add(iFAlta);
        
    }
    
    private void inicializarModificar()
    {
        panelModificar = new JPanel();
        panelModificar.setLayout(new FlowLayout());
        
        iFModificar = new JInternalFrame("Modificar Contacto");
        
        comboBuscar = new JComboBox<String>();
        comboBuscar.addItem("Buscar por...");
        comboBuscar.addItem("Nombre");
        comboBuscar.addItem("Apellido");
        comboBuscar.addItem("Telefono");
        
        botonModificarGuardar = new JButton("Guardar");
        botonModificarCancelar = new JButton("Cancelar");
        
        panelModificar.add(comboBuscar);
        panelModificar.add(botonModificarGuardar);
        panelModificar.add(botonModificarCancelar);
        
        iFModificar.add(panelModificar);
        iFModificar.setClosable(true);
        iFModificar.setResizable(true);
        iFModificar.pack();
        iFModificar.setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        desktopPane.add(iFModificar);
    }
    
    public void mostrarNuevo(boolean mostrar) {
        
        iFAlta.setVisible(mostrar);
    }
    
    public void mostrarModificar(boolean mostrar) {
        
        iFModificar.setVisible(mostrar);
    }
    
    private void actionListeners()
    {
        itemSalir.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        botonAltaGuardar.addActionListener((ActionEvent e) -> {
            try {
                if(iConexion.crearRegistro(fieldAltaNombre.getText(), fieldAltaApellidos.getText(), 
                        fieldAltaTelefono.getText(), fieldAltaAvenida.getText(), fieldAltaNumero.getText(), 
                        fieldAltaColonia.getText(), fieldAltaCodigoPostal.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Usuario guardado con exito", "Usuario guardado", 1);
                    limpiarCampos();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Directorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
                        
        botonAltaCancelar.addActionListener((ActionEvent e) -> {
            mostrarNuevo(false);
        });
        
        itemNuevo.addActionListener((ActionEvent e) -> {
            mostrarNuevo(true);
        });
        
        itemModificar.addActionListener((ActionEvent e) -> {
            mostrarModificar(true);
        });
    }
    
    public void limpiarCampos()
    {
        fieldAltaNombre.setText("");
        fieldAltaApellidos.setText("");
        fieldAltaTelefono.setText("");
        fieldAltaAvenida.setText("");
        fieldAltaNumero.setText("");
        fieldAltaColonia.setText("");
        fieldAltaCodigoPostal.setText("");
    }
    
    public void DBG(String text)
    {
        System.out.println(text);
    }
}
