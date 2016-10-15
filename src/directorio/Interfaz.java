package directorio;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import javax.swing.JToolBar;
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
    JMenuItem itemMostrarRegistros;
    
    JMenu menuAyuda;
    JMenuItem itemAyuda;
    JMenuItem itemAbout;
    
    JToolBar toolBar;
    JButton botonNuevo, botonModificar, botonBorrar, botonSalir;
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
    JPanel panelModificar, panelModificarBotones, panelModificarBuscar;
    JInternalFrame iFModificar;
    
    JComboBox<String> comboBuscar;
    
    JLabel labelModificarBuscar;
    JTextField fieldModificarBuscar;
    JButton botonModificarGuardar;
    JButton botonModificarCancelar;
    
    String columna;
    String dato;
    //Termina declaracion de variables y componentes de modulo "Modificar"
    
    //Inicia declaracion de variables y componentes de modulo "Mostrar Registros"
    JPanel panelMostrarRegistros;
    JInternalFrame iFMostrarRegistros;
    
    //Termina declaracion de variables y componentes de modulo "Mostrar Registros"
    
    JDesktopPane desktopPane;
    Image icon;
    
    Image img;
    JLabel background;
    
    Conexion iConexion;
    
    final int _ALTA = 0;
    final int _MODIFICAR = 1;    
    
    //Variables para determinar la resolucion de la pantalla
    Dimension screenSize;
    double width, height;
    
    public Interfaz()
    {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        
        iConexion = new Conexion();
        
        frame = new JFrame("Directorio");
        frame.setLayout(new BorderLayout());
        
        desktopPane = new JDesktopPane();
        frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
        
        //Codigo para cambiar el icono en la parte superior izquierda del frame.
        try{
            icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/icon.png"));
            frame.setIconImage(icon);
        }
        catch(Exception e)
        {
            DBG("Error al cargar el ícono");
        }
        
        try{
            img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/phone.png"));
            background = new JLabel(new ImageIcon(img));
            //frame.getContentPane().add(background); // CON IMAGEN DE FONDO NO MUESTRA OTROS COMPONENTES
        }
        catch(Exception e)
        {
            DBG("Error al cargar la imagen de fondo");
        }
        
        //Linea para que el frame aparezca maximizado por defecto.
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        frame.setMinimumSize(new Dimension (640,480));
        
        inicializarToolBar();
        
        menuBar = new JMenuBar();
        
        menuArchivo = new JMenu("Archivo");
        itemSalir = new JMenuItem("Salir");
        menuArchivo.add(itemSalir);
        
        menuAccion = new JMenu("Accion");
        itemNuevo = new JMenuItem("Nuevo");
        itemModificar = new JMenuItem("Modificar");
        itemBorrar = new JMenuItem("Borrar");
        itemMostrarRegistros = new JMenuItem("Mostrar Registros");
        menuAccion.add(itemNuevo);
        menuAccion.add(itemModificar);
        menuAccion.add(itemBorrar);
        menuAccion.add(itemMostrarRegistros);
        
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
        inicializarMostrarRegistros();
        
        
        frame.setVisible(true);
        
        //Evento para cerrrar la ventana con el boton X de la barra superior
        frame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {System.exit(0);}});
        
        actionListeners();
    }
    
    private void inicializarToolBar()
    {
        toolBar = new JToolBar();
 
        ImageIcon icono;
        
        icono = new ImageIcon(getClass().getResource("res/agregar.png"));
        botonNuevo = new JButton(icono);
        botonNuevo.setToolTipText("Agregar contacto");
        
        icono = new ImageIcon(getClass().getResource("res/editar.png"));
        botonModificar = new JButton(icono);
        botonModificar.setToolTipText("Modificar contacto");
        
        icono = new ImageIcon(getClass().getResource("res/eliminar.png"));
        botonBorrar = new JButton(icono);
        botonBorrar.setToolTipText("Eliminar contacto");
        
        icono = new ImageIcon(getClass().getResource("res/salir.png"));
        botonSalir = new JButton(icono);
        botonSalir.setToolTipText("Salir");
        
        toolBar.add(botonNuevo);
        toolBar.add(botonModificar);
        toolBar.add(botonBorrar);
        toolBar.add(botonSalir);
        
        frame.getContentPane().add(toolBar, BorderLayout.NORTH);
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
        fieldAltaNombre.setToolTipText("Agregar nombre");
        fieldAltaNombre.setMaximumSize(new Dimension((int) width,25));
        //fieldAltaNombre.setMinimumSize(new Dimension(25,250));
        
        labelAltaApellidos = new JLabel("Apellidos:");
        fieldAltaApellidos = new JTextField(25);
        fieldAltaApellidos.setToolTipText("Agregar apellidos");
        fieldAltaApellidos.setMaximumSize(new Dimension((int) width,25));
        //fieldApellidos.setMinimumSize(new Dimension(25,250));
        
        labelAltaTelefono = new JLabel("Telefono:");
        fieldAltaTelefono = new JTextField(10);
        fieldAltaTelefono.setMaximumSize(new Dimension((int) width,25));
        fieldAltaTelefono.setToolTipText("Agregar numero telefonico");
        
        labelAltaAvenida = new JLabel("Avenida:");
        fieldAltaAvenida = new JTextField(15);
        fieldAltaAvenida.setMaximumSize(new Dimension((int) width,25));
        fieldAltaAvenida.setToolTipText("Agregar avenida");
        //fieldAvenida.setMinimumSize(new Dimension(25,250));
        
        labelAltaNumero = new JLabel("Numero:");
        fieldAltaNumero = new JTextField(10);
        fieldAltaNumero.setMaximumSize(new Dimension((int) width,25));
        fieldAltaNumero.setToolTipText("Agregar numero");
        //fieldNumero.setMinimumSize(new Dimension(25,100));
        
        labelAltaColonia = new JLabel("Colonia: ");
        fieldAltaColonia = new JTextField(15);
        fieldAltaColonia.setMaximumSize(new Dimension((int) width,25));
        fieldAltaColonia.setToolTipText("Agregar colonia");
        //fieldColonia.setMinimumSize(new Dimension(25,250));
        
        labelAltaCodigoPostal = new JLabel("Codigo Postal:");
        fieldAltaCodigoPostal = new JTextField(10);
        fieldAltaCodigoPostal.setMaximumSize(new Dimension((int) width,25));
        fieldAltaCodigoPostal.setToolTipText("Agregar codigo postal");
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
        panelModificar.setLayout(new BoxLayout(panelModificar, BoxLayout.Y_AXIS));
        
        panelModificarBotones = new JPanel();
        panelModificarBotones.setLayout(new FlowLayout());
        
        panelModificarBuscar = new JPanel();
        panelModificarBuscar.setLayout(new FlowLayout());
        
        iFModificar = new JInternalFrame("Modificar Contacto");
        
        comboBuscar = new JComboBox<>();
        comboBuscar.addItem("Buscar por...");
        comboBuscar.addItem("Nombre");
        comboBuscar.addItem("Apellido");
        comboBuscar.addItem("Telefono");
        comboBuscar.addItem("Colonia");
        comboBuscar.addItem("Codigo Postal");
        
        labelModificarBuscar = new JLabel("Buscar:");
        fieldModificarBuscar = new JTextField(20);
        
        panelModificarBuscar.add(labelModificarBuscar);
        panelModificarBuscar.add(fieldModificarBuscar);
        panelModificarBuscar.add(comboBuscar);
        
        botonModificarGuardar = new JButton("Guardar");
        botonModificarCancelar = new JButton("Cancelar");
        
        panelModificar.add(panelModificarBuscar);
               
        panelModificarBotones.add(botonModificarGuardar);
        panelModificarBotones.add(botonModificarCancelar);
        
        try {
            panelModificar.add(iConexion.mostrarConsulta());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        
        panelModificar.add(panelModificarBotones);
        
        iFModificar.add(panelModificar);
        iFModificar.setClosable(true);
        iFModificar.setResizable(true);
        iFModificar.setMaximizable(true);
        iFModificar.pack();
        iFModificar.setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        desktopPane.add(iFModificar);
        
        columna = "";
        dato = "";
    }
    
    public void inicializarMostrarRegistros()
    {
        panelMostrarRegistros = new JPanel();
        panelMostrarRegistros.setLayout(new BoxLayout(panelModificar, BoxLayout.Y_AXIS));
        
        iFMostrarRegistros = new JInternalFrame("Mostrar Registros");
        //iFMostrarRegistros.setSize(new Dimension(1000,1000));
        
        try {
            iFMostrarRegistros.add(iConexion.mostrarConsulta());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        iFMostrarRegistros.setClosable(true);
        iFMostrarRegistros.setResizable(true);
        iFMostrarRegistros.pack();
        iFMostrarRegistros.setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        desktopPane.add(iFMostrarRegistros);
    }
    
    public void mostrarNuevo(boolean mostrar) {
        
        if(mostrar)
        {
            int posX = (desktopPane.getWidth()/2) - (iFAlta.getWidth()/2);
            int posY = (desktopPane.getHeight()/2) - (iFAlta.getHeight()/2);
            
            iFAlta.setLocation(posX, posY);
        }
        
        iFAlta.setVisible(mostrar);
    }
    
    public void mostrarModificar(boolean mostrar) {
        
        if(mostrar)
        {
            int posX = (desktopPane.getWidth()/2) - (iFModificar.getWidth()/2);
            int posY = (desktopPane.getHeight()/2) - (iFModificar.getHeight()/2);
        
            iFModificar.setLocation(posX, posY);
        }
        
        iFModificar.setVisible(mostrar);
    }
    
    public void mostrarMostrarRegistros(boolean mostrar)
    {
        if(mostrar)
        {
            int posX = (desktopPane.getWidth()/2) - (iFModificar.getWidth()/2);
            int posY = (desktopPane.getHeight()/2) - (iFModificar.getHeight()/2);
        
            iFMostrarRegistros.setLocation(posX, posY);
        }
        
        iFMostrarRegistros.setVisible(mostrar);
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
                    limpiarCampos(_ALTA);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Directorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
                        
        botonAltaCancelar.addActionListener((ActionEvent e) -> {
            mostrarNuevo(false);
        });
        
        botonModificarGuardar.addActionListener((ActionEvent e) ->{
//            try {
//                if(iConexion.buscarRegistros("NOMBRE"))
//                {
//                    DBG("Se encontraron resultados en la busqueda");
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
//            }
            columna = comboBuscar.getSelectedItem().toString();
            dato = fieldModificarBuscar.getText();
            if(!columna.equals("") && !dato.equals("") && !dato.equalsIgnoreCase("buscar por...")) {
                iConexion.consultar(columna, dato);
            } 
            else {
                JOptionPane.showMessageDialog(null, "Falta llenar alguno de los campos", "Datos incompletos", 1);
            }
            limpiarCampos(_MODIFICAR);
        });
        
        botonModificarCancelar.addActionListener((ActionEvent e) -> {
            mostrarModificar(false);
        });
        
        itemNuevo.addActionListener((ActionEvent e) -> {
            mostrarNuevo(true);
        });
        
        itemModificar.addActionListener((ActionEvent e) -> {
            mostrarModificar(true);
        });
        
        itemMostrarRegistros.addActionListener((ActionEvent e) -> {
            mostrarMostrarRegistros(true);
        });
        
        fieldAltaNombre.addActionListener((ActionEvent e) -> {
           if(!fieldAltaNombre.getText().equals(""))
           {
               fieldAltaNombre.transferFocus();
           }
        });
        
        fieldAltaApellidos.addActionListener((ActionEvent e) -> {
           if(!fieldAltaNombre.getText().equals(""))
           {
               fieldAltaApellidos.transferFocus();
           }
        });
        
        fieldAltaTelefono.addActionListener((ActionEvent e) -> {
           if(!fieldAltaTelefono.getText().equals(""))
           {
               fieldAltaTelefono.transferFocus();
           }
        });
        
        fieldAltaAvenida.addActionListener((ActionEvent e) -> {
           if(!fieldAltaAvenida.getText().equals(""))
           {
               fieldAltaAvenida.transferFocus();
           }
        });
        
        fieldAltaNumero.addActionListener((ActionEvent e) -> {
           if(!fieldAltaNumero.getText().equals(""))
           {
               fieldAltaNumero.transferFocus();
           }
        });
        
        fieldAltaColonia.addActionListener((ActionEvent e) -> {
           if(!fieldAltaColonia.getText().equals(""))
           {
               fieldAltaColonia.transferFocus();
           }
        });
        
        fieldAltaCodigoPostal.addActionListener((ActionEvent e) -> {
           if(!fieldAltaCodigoPostal.getText().equals(""))
           {
               fieldAltaCodigoPostal.transferFocus();
           }
        });
    }
    
    public void limpiarCampos(int modulo)
    {
        switch(modulo)
        {
            case _ALTA:
            {
                fieldAltaNombre.setText("");
                fieldAltaApellidos.setText("");
                fieldAltaTelefono.setText("");
                fieldAltaAvenida.setText("");
                fieldAltaNumero.setText("");
                fieldAltaColonia.setText("");
                fieldAltaCodigoPostal.setText("");
            } break;
            
            case _MODIFICAR:
            {
                comboBuscar.setSelectedIndex(0);
                fieldModificarBuscar.setText("");
                dato = "";
                columna = "";
            } break;
            
        }
    }
    
    private void DBG(String text)
    {
        System.out.println(text);
    }
}
