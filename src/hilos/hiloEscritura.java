/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

// <editor-fold defaultstate="collapsed" desc="Objetos y librerias importadas como dependencias del programa">
import static java.lang.Thread.sleep;

import buffers_pilas.spool_impresora;
// </editor-fold>">
 
public class hiloEscritura extends Thread  {
 
/// <editor-fold defaultstate="collapsed" desc="Variables Privadas del programa ">
 private spool_impresora coladeImpresion;
 private String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 // </editor-fold>

 

    public hiloEscritura( spool_impresora t ) {
        // Mantiene una copia propia del objeto compartido
        coladeImpresion = t;
        }

    public void run() {
        char dato;
        
        while(coladeImpresion.numero_impresion< 100){
        
            dato = alfabeto.charAt( (int)(Math.random()*26 ) );
            coladeImpresion.insertar_dato( dato);
            // Imprime un registro con lo añadido
            if(coladeImpresion.numero_impresion>6){
                System.out.println("------------CAMBIO DE ESCRITURA AL BUFFER SECUNDARIO------------- ");
                System.out.println( "----------> Entrada a BUFFER SECUNDARIO de HILO DE ESCRITURA '"+dato+"'" );
            }
            
            System.out.println( "----------> Entrada a BUFFER PRINCIPAL de HILO DE ESCRITURA '"+dato+"'" );
            // Espera un poco antes de añadir más letras
            
            try {
                coladeImpresion.numero_impresion++;
                sleep( (int)(Math.random() * 100 ) );
            } catch( InterruptedException e ) {;}
            
        }
    }
}
