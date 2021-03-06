package directorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Conexion implements Configuracion{
    
    ResultSetMetaData metaDatos;
    DefaultTableModel modelo;
    JTable tabla;
    
    //Variables provisionales para pruebas
    DefaultTableModel modelo_prueba;
    JTable tabla_prueba;
    JScrollPane scroll_prueba;
    //Terminan variables provisionales para pruebas
    
    public Conexion()
    {
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
    }
    
    public Connection createDatabaseConnection() throws ClassNotFoundException, SQLException
    {
        if(_DEBUG_MODE)
            DBG("Llamando método createDatabaseConnection() en Conexion.java");
        
        try
        {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            
            if(_DEBUG_MODE)
                DBG("Llamada a método Class.forName(driver) exitosa");
        }
        catch(ClassNotFoundException e)
        {
            DBG("Error al buscar clase");
        }
        try
        {   
            String url = "jdbc:mysql://localhost:3306/basededatos?"+"user=root&password=";
            Connection c = DriverManager.getConnection(url);
            DBG("Llamada a método DriverManager.getConnection(url) exitosa");
            return c;
        }
        catch(SQLException e)
        {
            DBG("Error al conectar a base de datos");
        }
        return null;
    }
    
    public boolean crearRegistro(String nombre, String apellidos, String telefono, String avenida, String numero, String colonia, String codigoPostal) throws ClassNotFoundException, SQLException
    {
        if(_DEBUG_MODE)
            DBG("Llamando método crearRegistro() en Conexion.java");
        
        if(validarRegistroNuevo(nombre, apellidos, telefono, avenida, numero, colonia, codigoPostal))
        {
            //CAMBIAR A PREPARESTATEMENT
            Statement st = createDatabaseConnection().createStatement();
            try{
                st.executeUpdate("INSERT INTO USUARIOS VALUES ('"+nombre+"','"+apellidos+"','"+telefono+"','"+avenida+"','"+numero+"','"+colonia+"','"+codigoPostal+"')");
                return true;
            }
            catch(Throwable e)
            {
                DBG("Se ha fallado en la insercion del registro");
                DBG(""+e);
                JOptionPane.showMessageDialog(null, "Hubo un error en la conexion a la base de datos", "Error", 2);
            }
            return false;
        }
        else
        {
           JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados", "Error", 2);
           return false;
        }
        
    }
    
    //Valida la alta de un registro siempre y cuando todos los campos contenga datos.
    public boolean validarRegistroNuevo(String nombre, String apellidos, String telefono, String avenida, String numero, String colonia, String codigoPostal)
    {
        if(_DEBUG_MODE)
            DBG("Llamando método validarRegistroNuevo() en Conexion.java");
        
        return !(nombre.equals("") || apellidos.equals("") || telefono.equals("") || avenida.equals("") 
                || numero.equals("") || colonia.equals("") || codigoPostal.equals(""));
    }
    
    //Por ahora muestra todos los registros, se necesita agregar el codigo para busqueda.
    public boolean buscarRegistros(String dato) throws SQLException
    {
        if(_DEBUG_MODE)
            DBG("Llamando método buscarRegistros() en Conexion.java");
        
        Statement st;
        try {
            st = createDatabaseConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM USUARIOS");
            
            return true;
            
        } catch (ClassNotFoundException ex) {
            DBG("Se ha fallado en la busqueda en la base de datos");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public ResultSet consultar(String columna, String dato)
    {
        
        if(_DEBUG_MODE)
            DBG("Llamando método consultar() en Conexion.java");
        
        ResultSet resultado = null;
        String sql = "SELECT 'columna' FROM USUARIOS WHERE 'columna' LIKE 'dato'";
        try{
            Statement st = createDatabaseConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = st.executeQuery(sql);
            DBG("Se encontro resultado en metodo consultar()");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en consulta de base de datos");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return resultado;
    }
    
    //Metodo para mostrar todos los registros de la base de datos
    public JScrollPane mostrarConsulta() throws ClassNotFoundException, SQLException
    {
        if(_DEBUG_MODE)
            DBG("Llamando método mostrarConsulta() en Conexion.java");
        
        modelo_prueba = new DefaultTableModel();
        tabla_prueba = new JTable(modelo_prueba);
        scroll_prueba = new JScrollPane();
        scroll_prueba.setViewportView(tabla_prueba);
        
        //Se obtiene la conexion a la base de datos
        Statement st = createDatabaseConnection().createStatement();
        
        //Se realiza la consulta y se obtiene un result set
        ResultSet rs = st.executeQuery("SELECT * FROM USUARIOS");
        
        //Se obtiene informacion sobre el numero de columnas y el nombre de los campos
        ResultSetMetaData metaDatos = rs.getMetaData();
        
        //Se obtiene el numero de columnas
        int numeroColumnas = metaDatos.getColumnCount();
        
        //Se crea un array de etiquetas para rellenar
        Object[] etiquetas = new Object[numeroColumnas];
        
        //Se obtiene cada una de las etiquetas para cada columna
        for(int i = 0; i < numeroColumnas; i++)
        {
            //Para ResultSetMetadata la primera columna es 1 en vez de 0
            etiquetas[i] = metaDatos.getColumnLabel(i + 1);
        }
        
        modelo_prueba.setColumnIdentifiers(etiquetas);
        
        while(rs.next())
        {
            // Se crea un array que será una de las filas de la tabla.
            Object[] fila = new Object[numeroColumnas];
            
            // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
            for(int i = 0; i < numeroColumnas; i++)
            {
                fila[i] = rs.getObject(i + 1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
            }
            
            modelo_prueba.addRow(fila);
        }
        
        return scroll_prueba;
    }
    
    
    public void DBG(String text)
    {
        System.out.println(text);
    }
}
