package directorio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Directorio {
    
    JFrame frame;
    JPanel panelNuevo;
    JPanel panelNuevoNombre;
    JPanel panelNuevoAvenida;
    JPanel panelNuevoColonia;
    
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
        
    public Directorio()
    {
        inicializarComponentes();
    }
    
    public void inicializarComponentes()
    {
        frame = new JFrame("Directorio");
        frame.setSize(640,480);
        frame.setMinimumSize(new Dimension (640,480));
        frame.setLayout(new GridBagLayout());
        
        panelNuevo = new JPanel();
        panelNuevo.setLayout(new BoxLayout(panelNuevo, BoxLayout.Y_AXIS));
        
        panelNuevoNombre = new JPanel();
        panelNuevoNombre.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelNuevoAvenida = new JPanel();
        panelNuevoAvenida.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelNuevoColonia = new JPanel();
        panelNuevoColonia.setLayout(new FlowLayout(FlowLayout.LEFT));
        
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
        
        labelNombre = new JLabel("Nombre:");
        fieldNombre = new JTextField(15);
        fieldNombre.setMinimumSize(new Dimension(25,250));
        labelApellidos = new JLabel("Apellidos:");
        fieldApellidos = new JTextField(15);
        fieldApellidos.setMinimumSize(new Dimension(25,250));
        labelAvenida = new JLabel("Avenida:");
        fieldAvenida = new JTextField(15);
        fieldAvenida.setMinimumSize(new Dimension(25,250));
        labelNumero = new JLabel("Numero:  ");
        fieldNumero = new JTextField(5);
        fieldNumero.setMinimumSize(new Dimension(25,100));
        labelColonia = new JLabel("Colonia: ");
        fieldColonia = new JTextField(15);
        fieldColonia.setMinimumSize(new Dimension(25,250));
        
        panelNuevoNombre.add(labelNombre);
        panelNuevoNombre.add(fieldNombre);
        panelNuevoNombre.add(labelApellidos);
        panelNuevoNombre.add(fieldApellidos);
        
        panelNuevoAvenida.add(labelAvenida);
        panelNuevoAvenida.add(fieldAvenida);
        panelNuevoAvenida.add(labelNumero);
        panelNuevoAvenida.add(fieldNumero);
        
        panelNuevoColonia.add(labelColonia);
        panelNuevoColonia.add(fieldColonia);
        
        panelNuevo.add(panelNuevoNombre);
        panelNuevo.add(panelNuevoAvenida);
        panelNuevo.add(panelNuevoColonia);
        
        frame.add(panelNuevo);
        frame.setVisible(true);
        
        //Evento para cerrrar la ventana con el boton X de la barra superior
        frame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0);}});
    }
    
    public void mostrarNuevo()
    {
        
    }

    public static void main(String[] args) {
        
        new Directorio();
    }
    
}
