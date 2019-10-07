
package hilos;


import buffers_pilas.spool_impresora;
import static java.lang.Thread.sleep;

public class hiloImpresion extends Thread{
    private spool_impresora colaImpresion;

    public hiloImpresion( spool_impresora t ) {
        // Almacena una instancia de un buffer(Primario) en memoria (limitado)
        colaImpresion = t;
        }

    public void run() {
        char datoImprimir; //

        // Consume n letras del buffer Principal en memoria 
       //donde el valor de 10 representa n 
     
      while(colaImpresion.numero_impresion< 100){
            datoImprimir = colaImpresion.imprimir();
            // Imprime las letras retiradas
            System.out.println("\t HILO DE IMPRESION ::: " +datoImprimir +"<-----------\n" );
            // Espera un poco antes de coger más letras
            try {
                sleep( 500);
               
                colaImpresion.numero_impresion++;
                        } catch( InterruptedException e ) {;}
           }
        }
    }

