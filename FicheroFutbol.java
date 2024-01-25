import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class FicheroFutbol {

    public int guardar(String nombreEquipo,int partJugados, int partGanados, int partEmpatados, int partPerdidos,int puntos){
        int resultado;
        try{    
            FileWriter f = new FileWriter("liga.dat",true);
            // El true es para que no sobreescriba el fichero
            // El caracter ; es para separar los datos
            f.write(nombreEquipo + ';' + partJugados + ';' + partGanados + ';' + partEmpatados + ';' + partPerdidos + ';' + puntos + ';' + "\n");
            f.close();
            resultado = 0;
        }
        catch(Exception e){
            resultado = -1;
            e.printStackTrace();
        }
        return resultado;
    }
    
    public String[] ShowTable() {
        String[] informacion = new String[120];
        int i = 0;
        try {
            FileReader f = new FileReader("liga.dat");
            BufferedReader b = new BufferedReader(f);

            // Leemos el fichero y lo mostramos por pantalla
            String linea;
            String palabra = "";
            while ((linea = b.readLine()) != null) {
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
            
            b.close();

        } catch (Exception e) {
            System.out.println("Error al leer en el fichero");
        }

        return informacion;
    }
}