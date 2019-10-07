import buffers_pilas.*;
import hilos.hiloEscritura;
import hilos.hiloImpresion;
public class Init {
   public static void main( String args[] ) {
       
        spool_impresora spool = new spool_impresora(); //SPOOLER DE IMPRESORA CON DOBLE SISTEMA DE BUFFER 
                                                       //DOS AREGLOS UNO LIMITADO Y OTRO ILIMITADO 
        hiloEscritura escritura = new hiloEscritura(spool);
        hiloImpresion impresion = new hiloImpresion(spool);

        escritura.start();
        impresion.start();
        } 
}
