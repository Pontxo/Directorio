package directorio;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Directorio {
    
    JFrame frame;
    JPanel panel;
    
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
        
    public Directorio()
    {
        inicializarComponentes();
    }
    
    public void inicializarComponentes()
    {
        frame = new JFrame("Directorio");
        frame.setSize(640,480);
        frame.setMinimumSize(new Dimension (640,480));
        
        panel = new JPanel();
        
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
        
        frame.setVisible(true);
        
        //Evento para cerrrar la ventana con el boton X de la barra superior
        frame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0);}});
    }

    public static void main(String[] args) {
        
        new Directorio();
    }
    
}
