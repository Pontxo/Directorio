package directorio;

import java.sql.SQLException;

public class Directorio {
        
    Interfaz iInterfaz;
   
    public Directorio() throws ClassNotFoundException, SQLException
    {        
        iInterfaz = new Interfaz();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        new Directorio();
    }
    
}
