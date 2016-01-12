package directorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    
    public Conexion()
    {

    }
    
    public Connection createDatabaseConnection() throws ClassNotFoundException, SQLException
    {
        try
        {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            DBG("Class.forName(driver) Success!!!");
        }
        catch(ClassNotFoundException e)
        {
            DBG("Error al buscar clase");
        }
        try
        {   
            String url = "jdbc:mysql://localhost:3306/basededatos?"+"user=root&password=";
            Connection c = DriverManager.getConnection(url);
            DBG("getConnection(url) Success!!!");
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
    
    public boolean validarRegistroNuevo(String nombre, String apellidos, String telefono, String avenida, String numero, String colonia, String codigoPostal)
    {        
        if(nombre == "" || apellidos == "" || telefono == "" || avenida == "" 
                || numero == "" || colonia == "" || codigoPostal == "")
            return false;
        return true;
    }
    
    public void DBG(String text)
    {
        System.out.println(text);
    }
}
