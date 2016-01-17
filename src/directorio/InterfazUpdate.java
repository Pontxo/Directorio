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
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

public class InterfazUpdate {
    
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
    JPanel panelAlta;
    JInternalFrame iFAlta;
    
    JLabel labelAltaNombre;
    JTextField fieldAltaNombre;
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
    //Termina declaracion de variables y componentes de modulo "Alta"
    
    //Inicia declaracion de variables y componentes de modulo "Modificar"
    JPanel panelModificar;
    JInternalFrame iFModificar;
    
    JComboBox comboBuscar;
    JButton botonModificarGuardar;
    JButton botonModificarCancelar;
    //Termina declaracion de variables y componentes de modulo "Modificar"
    
    JDesktopPane desktopPane;
    Image icon;
    
    public InterfazUpdate()
    {
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
    
    public void inicializarAlta()
    {
        JPanel panelAlta = new JPanel();
        panelAlta.setLayout(new BoxLayout(panelAlta, BoxLayout.Y_AXIS));
        
        iFAlta = new JInternalFrame("Agregar Contacto");
        
        labelAltaNombre = new JLabel("Nombre:");
        fieldAltaNombre = new JTextField(25);
        //fieldAltaNombre.setMinimumSize(new Dimension(25,250));
        
        labelApellidos = new JLabel("Apellidos:");
        fieldApellidos = new JTextField(25);
        //fieldApellidos.setMinimumSize(new Dimension(25,250));
        
        labelTelefono = new JLabel("Telefono:");
        fieldTelefono = new JTextField(10);
        
        labelAvenida = new JLabel("Avenida:");
        fieldAvenida = new JTextField(15);
        //fieldAvenida.setMinimumSize(new Dimension(25,250));
        
        labelNumero = new JLabel("Numero:");
        fieldNumero = new JTextField(10);
        //fieldNumero.setMinimumSize(new Dimension(25,100));
        
        labelColonia = new JLabel("Colonia: ");
        fieldColonia = new JTextField(15);
        //fieldColonia.setMinimumSize(new Dimension(25,250));
        
        labelCodigoPostal = new JLabel("Codigo Postal:");
        fieldCodigoPostal = new JTextField(10);
        //fieldCodigoPostal.setMinimumSize(new Dimension(25,250));
        
        panelAlta.add(labelAltaNombre);
        panelAlta.add(fieldAltaNombre);
        panelAlta.add(labelApellidos);
        panelAlta.add(fieldApellidos);
        panelAlta.add(labelTelefono);
        panelAlta.add(fieldTelefono);
        panelAlta.add(labelAvenida);
        panelAlta.add(fieldAvenida);
        panelAlta.add(labelNumero);
        panelAlta.add(fieldNumero);
        panelAlta.add(labelColonia);
        panelAlta.add(fieldColonia);
        panelAlta.add(labelCodigoPostal);
        panelAlta.add(fieldCodigoPostal);
        
        iFAlta.add(panelAlta);
        iFAlta.pack();
        iFAlta.setClosable(true);
        iFAlta.setMaximizable(true);
        iFAlta.setResizable(true);
        iFAlta.setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        desktopPane.add(iFAlta);
        
    }
    
    public void inicializarModificar()
    {
        panelModificar = new JPanel();
        panelModificar.setLayout(new FlowLayout());
        
        iFModificar = new JInternalFrame("Modificar Contacto");
        
        comboBuscar = new JComboBox();
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
    
    public void actionListeners()
    {
        itemSalir.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        itemNuevo.addActionListener((ActionEvent e) -> {
            if(iFAlta.isClosed())
            {
                //Insertar codigo aqui
            }
            mostrarNuevo(true);
        });
        
        itemModificar.addActionListener((ActionEvent e) -> {
            mostrarModificar(true);
        });
    }
    
    public void DBG(String text)
    {
        System.out.println(text);
    }
}
