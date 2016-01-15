package directorio;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
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
import javax.swing.border.TitledBorder;

public class Interfaz {
    
    //Inicia declaracion de variables y componentes del menu principal
    JFrame frame;
    JDesktopPane desktopPane;
    JInternalFrame iFNuevo;
    
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
    
    //Inicia declaracion de variables y componentes de modulo "Nuevo"
    JPanel panelNuevo;
    JPanel panelNuevoBotones;
    
    TitledBorder tituloPanelNuevo;
    
    JLabel labelNombre;
    JTextField fieldNombre;
    JLabel labelApellidos;
    JTextField fieldApellidos;
    JLabel labelAvenida;
    JTextField fieldAvenida;
    JLabel labelNumero;
    JTextField fieldNumero;
    JLabel labelColonia;
    JTextField fieldColonia;
    JLabel labelCodigoPostal;
    JTextField fieldCodigoPostal;
    JLabel labelTelefono;
    JTextField fieldTelefono;
    JButton botonGuardar, botonCancelar;
    //Termina declaracion de variables y componentes de modulo "Nuevo"
    
    //Inicia declaracion de variables y componentes de modulo "Modificar"
    JPanel panelModificar;
    JPanel panelModificarBotones;
    JComboBox comboBuscar;
    JButton botonModificarGuardar;
    JButton botonModificarCancelar;
    //Termina declaracion de variables y componentes de modulo "Nuevo"
    
    //Inicia declaracion de variables y componentes de modulo "Baja"
    JPanel panelBaja;
    //Termina declaracion de variables y componentes de modulo "Baja"
    
    Image icon;
    
    Conexion iConexion;

    public Interfaz()
    {
        iConexion = new Conexion();
        inicializarComponentes();
    }
    
    public void inicializarComponentes()
    {
        frame = new JFrame("Directorio");
        
        desktopPane = new JDesktopPane();
        //frame.add(desktopPane);
        
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
        
        frame.setSize(640,480);
        frame.setMinimumSize(new Dimension (640,480));
        //frame.setLayout(new GridBagLayout());
        
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
        
        inicializarNuevo();
        inicializarModificar();
        
        frame.setVisible(true);
        
        //Evento para cerrrar la ventana con el boton X de la barra superior
        frame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0);}});
        
        //Metodo donde estaran todos los eventos
        actionListeners();
    }
    
    public void inicializarNuevo()
    {       
        panelNuevo = new JPanel();
        panelNuevo.setLayout(new BoxLayout(panelNuevo, BoxLayout.Y_AXIS));
        
        panelNuevoBotones = new JPanel();
        panelNuevoBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        tituloPanelNuevo = BorderFactory.createTitledBorder("Alta");
        panelNuevo.setBorder(tituloPanelNuevo);
        
        labelNombre = new JLabel("Nombre:");
        labelNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldNombre = new JTextField(15);
        fieldNombre.setMinimumSize(new Dimension(25,250));
        fieldNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelApellidos = new JLabel("Apellidos:");
        labelApellidos.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldApellidos = new JTextField(15);
        fieldApellidos.setMinimumSize(new Dimension(25,250));
        fieldApellidos.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelTelefono = new JLabel("Telefono:");
        labelTelefono.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldTelefono = new JTextField(10);
        fieldTelefono.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelAvenida = new JLabel("Avenida:");
        labelAvenida.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldAvenida = new JTextField(15);
        fieldAvenida.setMinimumSize(new Dimension(25,250));
        fieldAvenida.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelNumero = new JLabel("Numero:");
        labelNumero.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldNumero = new JTextField(10);
        fieldNumero.setMinimumSize(new Dimension(25,100));
        fieldNumero.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelColonia = new JLabel("Colonia: ");
        labelColonia.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldColonia = new JTextField(15);
        fieldColonia.setMinimumSize(new Dimension(25,250));
        fieldColonia.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        labelCodigoPostal = new JLabel("Codigo Postal:");
        labelCodigoPostal.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldCodigoPostal = new JTextField(10);
        fieldCodigoPostal.setMinimumSize(new Dimension(25,250));
        fieldCodigoPostal.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        botonGuardar = new JButton("Guardar");
        botonCancelar = new JButton("Cancelar");
        
        panelNuevoBotones.add(botonGuardar);
        panelNuevoBotones.add(botonCancelar);
        panelNuevoBotones.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panelNuevo.add(labelNombre);
        panelNuevo.add(fieldNombre);
        panelNuevo.add(labelApellidos);
        panelNuevo.add(fieldApellidos);
        panelNuevo.add(labelTelefono);
        panelNuevo.add(fieldTelefono);
        panelNuevo.add(labelAvenida);
        panelNuevo.add(fieldAvenida);
        panelNuevo.add(labelNumero);
        panelNuevo.add(fieldNumero);
        panelNuevo.add(labelColonia);
        panelNuevo.add(fieldColonia);
        panelNuevo.add(labelCodigoPostal);
        panelNuevo.add(fieldCodigoPostal);
        panelNuevo.add(panelNuevoBotones);
        
        //panelNuevo.setVisible(false);
        //panelNuevoBotones.setVisible(false);
        
        //public JInternalFrame(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable)
        iFNuevo = new JInternalFrame("Nuevo Contacto");
        iFNuevo.add(panelNuevo);
        iFNuevo.pack();
        iFNuevo.setResizable(true);
        iFNuevo.setClosable(true);
        
        desktopPane.add(iFNuevo);
        
        frame.add(iFNuevo);
        
        //iFNuevo.setVisible(true);
    }
    
    public void inicializarModificar()
    {
        panelModificar = new JPanel();
        panelModificar.setLayout(new BoxLayout(panelModificar, BoxLayout.Y_AXIS));
        panelModificarBotones = new JPanel();
        panelModificarBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        comboBuscar = new JComboBox();
        comboBuscar.addItem("Buscar por...");
        comboBuscar.addItem("Nombre");
        comboBuscar.addItem("Apellido");
        comboBuscar.addItem("Telefono");
        
        botonModificarGuardar = new JButton("Guardar");
        botonModificarCancelar = new JButton("Cancelar");
        
        panelModificarBotones.add(botonModificarGuardar);
        panelModificarBotones.add(botonModificarCancelar);
        
        panelModificar.add(comboBuscar);
        panelModificar.add(panelModificarBotones);
        
        panelModificar.setVisible(false);
        frame.add(panelModificar);
    }
    
    public void mostrarNuevo(boolean mostrar)
    {
        iFNuevo.setVisible(mostrar);
    }
    
//    public void mostrarModificar(boolean mostrar)
//    {
//        panelModificar.setVisible(mostrar);
//    }
    
    public void mostrarBaja(boolean mostrar)
    {
        
    }
    
    public void actionListeners()
    {
        itemSalir.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        itemNuevo.addActionListener((ActionEvent e) -> {
//            mostrarModificar(false);
            mostrarNuevo(true);
        });
        
//        itemModificar.addActionListener((ActionEvent e) -> {
//            mostrarModificar(true);
//        });
        
        botonGuardar.addActionListener((ActionEvent e) -> {
            try {
                if(iConexion.crearRegistro(fieldNombre.getText(), fieldApellidos.getText(), fieldTelefono.getText(),
                        fieldAvenida.getText(), fieldNumero.getText(), fieldColonia.getText(), fieldCodigoPostal.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Usuario guardado con exito", "Usuario guardado", 1);
                    limpiarCampos();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Directorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        botonCancelar.addActionListener((ActionEvent e) -> {
            mostrarNuevo(false);
        });
        
//        botonModificarCancelar.addActionListener((ActionEvent e) -> {
//            mostrarModificar(false);
//        });
    }
    
    public void limpiarCampos()
    {
        fieldNombre.setText("");
        fieldApellidos.setText("");
        fieldTelefono.setText("");
        fieldAvenida.setText("");
        fieldNumero.setText("");
        fieldColonia.setText("");
        fieldCodigoPostal.setText("");
    }
    
    public void DBG(String text)
    {
        System.out.println(text);
    }
    
}
