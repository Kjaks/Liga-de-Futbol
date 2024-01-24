import java.io.FileWriter;
import java.nio.Buffer;
import java.io.BufferedReader;
import java.io.FileReader;

public class FicheroFutbol {

    public int guardar(String nombreEquipo,int partJugados, int partGanados, int partEmpatados, int partPerdidos,int puntos){
        int resultado;
        try{    
            FileWriter f = new FileWriter("liga.dat",true);
            f.write(nombreEquipo + ';' + partJugados + ';' + partGanados + ';' + partEmpatados + ';' + partPerdidos + ';' + puntos + "\n");
            f.close();
            resultado = 0;
        }
        catch(Exception e){
            resultado = -1;
            e.printStackTrace();
        }
        return resultado;
    }
    
    public String ShowTable(){
        String informacion = " ";
        informacion += "Nombre del equipo     Jugados     Ganados     Empatados     Perdidos     Puntos\n";
        try {
            FileReader f = new FileReader("liga.dat");
            BufferedReader b = new BufferedReader(f);

            String linea;
            while ((linea = b.readLine()) != null) {
                informacion += linea;
            }    
            
            b.close();

        } catch (Exception e) {
            System.out.println("Error al leer en el fichero");
        }

        for(int i = 0; i < informacion.length(); i++){
                informacion = informacion.replace(";", "     ");
        }

        return informacion;

    }
}
