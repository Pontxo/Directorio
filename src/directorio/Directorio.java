package directorio;

import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Directorio {
        
    Interfaz iInterfaz;
   
    public Directorio() throws ClassNotFoundException, SQLException
    {        
        //iInterfaz = new Interfaz_Deprecated();
        iInterfaz = new Interfaz();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

//Look and Feel comentado por que afecta a los JInternalFrames.        
//    try {
//            // Set System L&F
//        UIManager.setLookAndFeel(
//            UIManager.getSystemLookAndFeelClassName());
//    } 
//    catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//       // handle exception
//       System.out.println("Error cargando Look and Feel");
//       System.out.println(""+e);
//    }
        // handle exception
        // handle exception
        // handle exception
        
        new Directorio();
    }
    
}
