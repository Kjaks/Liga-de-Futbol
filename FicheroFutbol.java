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
        ShowTable();
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
    
    public String[] ShowTable() {
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
                    if(puntuacion[k][j] == 0 && informacion[punt] != null) puntuacion[k][j] = Integer.parseInt(informacion[punt]);
                }
            }
            
            b.close();

        } catch (Exception e) {
                System.err.println("Error al leer en el fichero: " + e.getMessage());
                // Realizar acciones de manejo de errores adicionales aquí
        }

        return informacion;
    }

    public void ordenar() {
        ShowTable();
        
        for(int i = 0; i < contador; i++){
            for(int j = 0; j < 1; j++){
                if(puntuacion[i][0] != 0)System.out.println(puntuacion[i][0]);
                }
        }
    }
        
        
}
