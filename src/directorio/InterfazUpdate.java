package directorio;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    JButton botonGuardar, botonCancelar;
    //Termina declaracion de variables y componentes de modulo "Alta"
    
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
        
        frame.setVisible(true);
        
        //Evento para cerrrar la ventana con el boton X de la barra superior
        frame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0);}});
    }
    
    public void inicializarAlta()
    {
        JPanel panelAlta = new JPanel();
        panelAlta.setLayout(new FlowLayout());
        
        iFAlta = new JInternalFrame("Agregar Contacto");
        
        labelAltaNombre = new JLabel("Nombre:");
        fieldAltaNombre = new JTextField(25);
        fieldAltaNombre.setMinimumSize(new Dimension(25,250));
        
        panelAlta.add(labelAltaNombre);
        panelAlta.add(fieldAltaNombre);
        
        iFAlta.add(panelAlta);
        iFAlta.pack();
        iFAlta.setClosable(true);
        iFAlta.setResizable(true);
        
        desktopPane.add(iFAlta);
        
//        frame.add(desktopPane);
        iFAlta.setVisible(true);
    }
    
}
