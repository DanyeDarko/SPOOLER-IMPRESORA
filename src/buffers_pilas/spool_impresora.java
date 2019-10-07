
package buffers_pilas;


public class spool_impresora implements Interfaces.impresora{
    private char buffer_secundario[] = new char[100]; //este es el buffer 'ilimitado' **PODRIA SER UN ARRAYLIST ,ESCRIBE AQUI 
                                                      //ESCRIBE AQUI SOLO SI ESTA LLENO EL BUFFER PRINCIPAL Y EL HILO DE ESCITURA 
                                                      //NO ESTA ESCRIBIENDO EN OTRO HILO 
    private char buffer_primario[] = new char[6]; //BUFFER PRIMARIO CON CAPACIDAD LIMITADA,UNAVES QUE SE LLENA SE GUARDAN LOS DATOS
                                                  //EN EL BUFFER SECUNDARIO QUE SON ALMACENADOS EN DISCO 
    private int siguienteCaracter = 0; //APUNTADOR QUE PERMITE REDIRIGIR EL DATO DE ESCRITURA EN EL ESPACIO EN MEMORIA DE EL BUFFER PRIMARIO
    
//SEMAFOROS QUE NOS PERMITEN CONTROLAR EL FLUJO DE LOS HILOS UNA VES QUE SE EVALUEN COMO VERDADEROS O FALSOS
//EL FLJO DEL HILO SE CONTROLA CON WAIT Y NOTIFY 
    private boolean bufferPrincipalLleno = false; //EL BUFFERPRINCIPAL NO ESTA LLENO AL COMENSAR EL PROGRAMA 
    private boolean bufferPrincipalVacio = true; //POR LO TANTO SE ENCUENTRA VACIO
    public int numero_impresion = 0; // Y AUN NO SE IMRPIME NADA POR ESO ESTA EN CERO EL NUMERO DE IMPRESION
    
    
    public synchronized char imprimir() { //METODO QUE IMPRIME LAS LETRAS DE LOS BUFFERS 
        //NO ES POSIBLE CONSUMIR DATOS SI EL BUFFER ESTA VACIO ES DECIR LA OCNDICION ES VERDADERA POR LO TANTO EL HILO DDE IMPRESION
        //ESPERA
        while( bufferPrincipalVacio == true )
            {
            try {
                 System.out.println("***HILO DE IMPRESION DETENIDO..................");
                wait(); // EL HILO DE IMPRESION ESPERA A QUE LA BANDERA DEL BUFFER VACIO SEA FALSA ES DECIR EL BUFFER TENGA DATOS
                 
            } catch( InterruptedException e) {
                ;
                }
            }
        //SI EL BUFFER TIENE DATOS ENTONCES 
        // Decrementa la cuenta, ya que va a consumir una letra
        siguienteCaracter--;
        // Comprueba si se retiró la última letra
        if( siguienteCaracter == 0 ) //SI ES 0 YA RETIRO LA LETRA 
            bufferPrincipalVacio = true; //EL HILO SE ENCUENTRA VACIO OTRA VES ,POR LO TANTO ESPERA NUEVAMENTE 
        // El buffer no puede estar lleno, porque acabamos
        // de consumir
        bufferPrincipalLleno = false; //EL BUFFER YA ESTA VACIO PUESTO QUE ACABAMOS DE IMPRIMIR
        notify(); //NOTIFICA EL NUEVO ESTATUS DE LAS BANDERAS <--------ESTO ALTERA LOS HILOS Y LA ESCRITURA EN CADA BUFFER

        //DEVUELVE LA LETRA AL HILO DE IMPRESION 
        return( buffer_primario[siguienteCaracter] );
        }

    // Método para añadir letras al buffer
    public synchronized void insertar_dato( char c ) {
       //MIENTRAS EL BUFFER PRINCIPAL ESTE LLENO 
        while( bufferPrincipalLleno == true )
            {
            try {
                System.out.println("***HILO ECRITURA BUFFER PRINCIPAL DETENIDO..................");
                wait(); // EL HILO DE ESCRITURA SE DETIENE POR QUE ESTA IMPRIMIENDO O ESTA LLENO 
                  
            } catch( InterruptedException e ) {
                ;
                }
            }
        // Añade una letra en el primer lugar disponible
        buffer_primario[siguienteCaracter] = c;
        // Cambia al siguiente lugar disponible
        siguienteCaracter++;
        // Comprueba si el buffer está lleno
        if( siguienteCaracter == 6 )
            bufferPrincipalLleno = true;
        bufferPrincipalVacio = false;
        notify();
        }
    }
   
   
