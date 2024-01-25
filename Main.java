import java.io.*;

public class Main{
    public static void main(String args[]){
        FicheroFutbol pd = new FicheroFutbol();
        int opcion = 0;
        String[] informacion;
        System.out.println("Bienvenido a la Liga de Futbol");
        
        do{
            System.out.println("1. Añadir Equipo");
            System.out.println("2. Mostrar datos");
            System.out.println("3. Ordenar datos");
            System.out.println("4. Buscar un equipo");
            System.out.println("5. Borrar un equipo");
            System.out.println("6. Modificar un equipo");
            System.out.println("7. Salir del programa");

            opcion = Integer.parseInt(System.console().readLine());

            switch(opcion) {
                case 1:
                    System.out.println("Añade un equipo y sus datos a continuacion!");
                    addTeam();
                    break;
                case 2:
                    System.out.println("La tabla de la liga es la siguiente:\n");
                    
                    informacion = pd.ShowTable();

                    System.out.print("Nombre del equipo     Jugados                  Ganados                  Empatados                Perdidos                  Puntos\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");

                    for (int i = 0; i < informacion.length; i++) {
                        // Verifica si el índice actual es el quinto elemento en la línea
                        if (i % 6 == 0 && informacion[i] != null && i != 0) {
                            System.out.println();
                            System.out.print("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");  // Salto de línea después de cada quinto elemento
                        }

                        if (informacion[i] != null) {
                            System.out.printf("%-25s", informacion[i]);
                    

                        }
                    }

                    System.out.println("\n");

                    break;
                case 3:
                    System.out.println("Tabla ordenada por puntos:");
                    break;
                case 4:
                    System.out.println("Escribe el nombre del equipo que quieres buscar:");
                    break;
                case 5:
                    System.out.println("Elige el equipo que quieras borrar");
                    break;
                case 6:
                    System.out.println("Elige el equipo que quieras y modificalo!");
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elige un número del 1 al 7.");
                    break;
            }

        } while(opcion != 7);

    }

    public static void addTeam(){
        FicheroFutbol pd = new FicheroFutbol();

        System.out.println("Escribe el nombre del equipo:");
            String nombreEquipo = System.console().readLine();
        
        System.out.println("Escribe el numero de partidos jugados:");
            int partidosJugados = Integer.parseInt(System.console().readLine());
        
        System.out.println("Escribe el numero de partidos ganados:");
            int partidosGanados = Integer.parseInt(System.console().readLine());
        
        System.out.println("Escribe el numero de partidos empatados:");
            int partidosEmpatados = Integer.parseInt(System.console().readLine());
        
        System.out.println("Escribe el numero de partidos perdidos:");
            int partidosPerdidos = Integer.parseInt(System.console().readLine());

            int puntos = partidosGanados * 3 + partidosEmpatados;
            int resultado = pd.guardar(nombreEquipo, partidosJugados, partidosGanados, partidosEmpatados, partidosPerdidos, puntos);

        if(resultado == -1){
            System.out.println("Error al guardar el equipo");
        } else {
            System.out.println("Equipo guardado correctamente");
        }            
    };

}