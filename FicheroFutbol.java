import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FicheroFutbol {
    // Con esto podremos visualizar la tabla
    private String[] informacion = new String[120];
    // Esta matriz es para ordenar las puntuaciones
    private int[][] puntuacion = new int[20][2];
    // El contador es para llevar la cuenta de cuantas filas hay en el fichero
    private int contador = 0;

    public int guardar(String nombreEquipo,int partJugados, int partGanados, int partEmpatados, int partPerdidos,int puntos){
        int resultado;
        showTable();
        try{    
            FileWriter f = new FileWriter("liga.dat",true);

            if(contador >= 20) resultado = -2;
            else {
                // El caracter ; es para separar los datos
                f.write(nombreEquipo + ';' + partJugados + ';' + partGanados + ';' + partEmpatados + ';' + partPerdidos + ';' + puntos + ';' + "\n");
                f.close();
                resultado = 0;
            }
        }
        catch(Exception e){
            resultado = -1;
            e.printStackTrace();
        }
        return resultado;
    }
    
    public String[] showTable() {
        int i = 0;
        try {
            FileReader f = new FileReader("liga.dat");
            BufferedReader b = new BufferedReader(f);

            // Leemos el fichero y lo mostramos por pantalla
            String linea;
            String palabra = "";
            contador = 0;
            while ((linea = b.readLine()) != null) {
                //Sirve para saber cuantas filas hay en el fichero
                contador++;
                for(int j = 0; j < linea.length(); j++){
                    // Guardo caracter por caracter la palabra hasta que encuentra un ;
                    if(linea.charAt(j) != ';') palabra += linea.charAt(j);

                    // Cuando encuentre un ; se guarda la palabra en el array y se vacia la palabra
                    if(linea.charAt(j) == ';'){ 
                    informacion[i] = palabra;
                    palabra = "";
                    i++;
                    }
                }
            } 

            // En este bucle guardamos la posicion de los puntos en la matriz
            for(int k = 0, punt = 5; k < contador; k++, punt += 6){
                for(int j = 0; j < 1; j++){
                    if(informacion[punt] != null) puntuacion[k][0] = k;
                    if(informacion[punt] != null) puntuacion[k][1] = Integer.parseInt(informacion[punt]);
                }
            }
            
            b.close();

        } catch (Exception e) {
        
        }

        return informacion;
    }

    public String[] ordenar() {
        showTable();
        String[] ordenado = new String[120];
        
        // Ordenar el array puntuacion de mayor a menor para cada fila
        for (int i = 0; i < contador; i++) {
            for (int j = 0; j < contador - i - 1; j++) {
                if (puntuacion[j][1] < puntuacion[j + 1][1]) {
                    // El metodo de ordenamiento sera el de la burbuja el ordenamiento ocurre en el bucle de dentro
                    // ordenaremos la posicion de los indices de los equipos dependiendo de la puntuacion que tenga cada uno
                    int indice = puntuacion[j][0];
                    int equiPuntuacion = puntuacion[j][1];
                    puntuacion[j][0] = puntuacion[j + 1][0];
                    puntuacion[j][1] = puntuacion[j + 1][1];
                    puntuacion[j + 1][0] = indice;
                    puntuacion[j + 1][1] = equiPuntuacion;
                }
            }
        }
        
        for (int i = 0; i < contador; i++) {
            for (int j = 0; j < contador; j++) {
                // Con este bucle lo que se consigue es meter en el array ordenado la informacion de cada equipo, pero claro estando ordenado
                if (puntuacion[i][0] == j && informacion[j * 6] != null) {
                    // Nombre del equipo
                    ordenado[i * 6] = informacion[j * 6];
                    // Partidos jugados
                    ordenado[i * 6 + 1] = informacion[j * 6 + 1];
                    // Partidos ganados
                    ordenado[i * 6 + 2] = informacion[j * 6 + 2];
                    // Partidos empatados
                    ordenado[i * 6 + 3] = informacion[j * 6 + 3];
                    // Partidos perdidos
                    ordenado[i * 6 + 4] = informacion[j * 6 + 4];
                    // Puntos
                    ordenado[i * 6 + 5] = informacion[j * 6 + 5];
                }
            }
        }

        return ordenado;
    }

    public String[] buscarEquipo(String nomEquipo){
        showTable();
        String[] equipo = new String[6];

        // Lo que se hace es simplemente recibir el string y cuando la posicion del nombre sea el mismo que el string recibido
        // se meten los demas datos en el array equipo
        for(int i = 0; i < informacion.length; i++){
            if(informacion[i] != null && informacion[i].equals(nomEquipo)){
                for(int j = 0; j < 6; j++){
                    equipo[j] = informacion[i + j];
                }
                i = informacion.length + 1;
            }
        }

        return equipo;
    }

    public int borrarEquipo(String nombreEquipo) {
        int resultado = 0;

        try {
            // El funcionamiento es sencillo haremos un archivo temporal que lea y transfiera la informacion del archivo original
            // para que cuando se llegue a la linea donde esta el equipo que queremos borrar no lo copie y cuando termine de leer
            // el archivo original se borra y se renombra el archivo temporal con el nombre del archivo original
            File archivoOriginal = new File("liga.dat");
            File archivoTemporal = new File("temporal.txt");

            BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
            FileWriter fw = new FileWriter(archivoTemporal);

            String linea;
            boolean equipoEncontrado = false;

            while ((linea = br.readLine()) != null) {
                if (linea.contains(nombreEquipo)) {
                    equipoEncontrado = true;
                } else {
                    fw.write(linea + "\n");
                }
            }

            br.close();
            fw.close();

            if (equipoEncontrado) {
                if (archivoTemporal.renameTo(archivoOriginal)) {
                    resultado = 0;
                } else {
                    resultado = -1;
                }
                } else {
                    resultado = 1;
                }
                archivoTemporal.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public void borrarTabla() {
        try {
            File archivo = new File("liga.dat");
            archivo.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
