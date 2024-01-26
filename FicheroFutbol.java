import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class FicheroFutbol {
    private String[] informacion = new String[120];
    int[][] puntuacion = new int[20][2];
    int contador = 0;

    public int guardar(String nombreEquipo,int partJugados, int partGanados, int partEmpatados, int partPerdidos,int puntos){
        int resultado;
        contador = 0;
        showTable();
        try{    
            FileWriter f = new FileWriter("liga.dat",true);

            if(contador == 20) resultado = -2;
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

            for(int k = 0, punt = 5; k < contador; k++, punt += 6){
                for(int j = 0; j < 1; j++){
                    if(informacion[punt] != null) puntuacion[k][0] = k;
                    if(informacion[punt] != null) puntuacion[k][1] = Integer.parseInt(informacion[punt]);
                }
            }
            
            b.close();

        } catch (Exception e) {
                System.err.println("Error al leer en el fichero: " + e.getMessage());
                // Realizar acciones de manejo de errores adicionales aquí
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

        for(int i = 0; i < informacion.length; i++){
            if(informacion[i] != null && informacion[i].equals(nomEquipo)){
                for(int j = 0; j < 6; j++){
                    equipo[j] = informacion[i + j];
                }
            }
        }

        return equipo;
    }
}