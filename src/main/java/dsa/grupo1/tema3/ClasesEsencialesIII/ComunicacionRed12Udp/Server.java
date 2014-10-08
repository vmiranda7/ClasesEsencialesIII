package dsa.grupo1.tema3.ClasesEsencialesIII.ComunicacionRed12Udp;

/**
 * Ejercicio 12. Comunicaciones en red
 * Implementar el cliente y el servidor del protocolo “¿Qué hora es?” utilizando tanto
 * TCP como UDP, ambos en modo no-concurrente. El protocolo funciona de la
 * siguiente manera: el cliente abre una conexión contra el servidor y este le responde
 * con la fecha y hora actual según su reloj en formato “[dia del mes]/[mes]/[año]
 * [hora]:[minutos]:[segundos]” todos en formato numérico (por ejemplo, 21/09/2013
 * 17:07:34). Para la realización de este ejercicio utilizar la clase
 * java.text.SimpleDateFormat
 */

import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        new ServerThread().start();
    }
}