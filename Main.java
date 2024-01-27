import java.io.*;
import java.util.Scanner;

public class Main{
    public static void main(String args[]){
        FicheroFutbol pd = new FicheroFutbol();
        int opcion = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido a la Liga de Futbol");
        
        do{
            System.out.println("1. Añadir Equipo");
            System.out.println("2. Mostrar datos");
            System.out.println("3. Ordenar datos");
            System.out.println("4. Buscar un equipo");
            System.out.println("5. Borrar un equipo");
            System.out.println("6. Modificar un equipo");
            System.out.println("7. Salir del programa");

            opcion = sc.nextInt();

            switch(opcion) {
                case 1:
                    System.out.println("Añade un equipo y sus datos a continuacion!\n");
                    // Llamamos al método addTeam de la clase Main para añadir un equipo
                    addTeam();
                    break;
                case 2:
                System.out.println("La tabla de la liga es la siguiente:\n");
                    tablaFormateada(pd.showTable());
                    break;
                case 3:
                    System.out.println("Tabla ordenada por puntos:");
                    tablaFormateada(pd.ordenar());
                    break;
                case 4:
                    System.out.println("Escribe el nombre del equipo que quieres buscar:");
                    tablaFormateada(pd.buscarEquipo(sc.next()));
                    break;
                case 5:
                    System.out.println("Elige el equipo que quieras borrar");
                    pd.borrarEquipo(sc.next());
                    break;
                case 6:
                    int eleccion = 0;
                    System.out.println("Escribe el nombre del equipo y modificalo!");
                    String eleccionEquipo = sc.next();

                    String[] equipoElegido = pd.buscarEquipo(eleccionEquipo);
                    int[] numeros = new int[4]; 

                    for(int i = 0, j = 1; i < 5; i++, j++){
                        numeros[i] = Integer.parseInt(equipoElegido[j]);
                    }

                    tablaFormateada(equipoElegido);

                        do{
                        System.out.println("Escribe el numero de lo que quieras modificar:");
                        eleccion = sc.nextInt();
                        System.out.println("1. Nombre del equipo");
                        System.out.println("2. Partidos jugados");
                        System.out.println("3. Partidos ganados");
                        System.out.println("4. Partidos empatados");
                        System.out.println("5. Salir de la modificacion");
                            if(eleccion < 1 || eleccion > 5){
                                System.out.println("Opcion invalida");
                            }
                        } while(eleccion < 1 || eleccion > 5);

                    switch (eleccion) {
                        case 1:
                            System.out.println("Nombre equipo anterior: " + equipoElegido[0]);
                            System.out.println("Escribe el nuevo nombre del equipo:");
                            equipoElegido[0] = sc.next();
                            break;
                        case 2:
                            System.out.println("Partidos jugados anterior: " + numeros[0]);
                            System.out.println("Escribe el nuevo numero de partidos jugados:");

                            numeros[0] = sc.nextInt();
                            numeros[3] = (numeros[1] + numeros[2]) - numeros[0];
                            break;
                        case 3:
                            System.out.println("Partidos ganados anterior: " + numeros[1]);
                            do{
                                numeros[1] = sc.nextInt();
                                if(numeros[1] > numeros[0]){
                                    System.out.println("El numero de partidos ganados no puede ser mayor que el numero de partidos jugados");
                                }
                                if(numeros[1] < 0){
                                    System.out.println("El numero de partidos ganados no puede ser menor que 0");
                                }
                                if((numeros[1] + numeros[2]) > numeros[0]){
                                    System.out.println("La suma de partidos ganados y empatados no puede ser mayor que el numero de partidos jugados");
                                }
                            } while(numeros[0] < numeros[1] || (numeros[1] + numeros[2]) > numeros[0] || numeros[1] < 0);
                            break;
                        case 4:
                            numeros[2] = sc.nextInt();
                            break;
                    }
                    

                    pd.borrarEquipo(eleccionEquipo);



                    pd.guardar(equipoElegido[0], numeros[0], numeros[1], numeros[2], numeros[3], numeros[4]);

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

    public static void addTeam(nombreEquipo, partidosJugados, partidosGanados, partidosEmpatados, partidosPerdidos,int puntos){
        Main m = new Main();
        FicheroFutbol pd = new FicheroFutbol();
        // Los partidosMaximos sirven para saber hasta cuantos partidos podemos empatar sin que se se pasen de los partidos jugados
        int partidosMaximos = 0;
        int partidosGanados = 0;
        int partidosEmpatados = 0;

        System.out.println("Escribe el nombre del equipo:");
            String nombreEquipo = System.console().readLine();
        
        System.out.println("Escribe el numero de partidos jugados:");
            int partidosJugados = Integer.parseInt(System.console().readLine());
        
        System.out.println("Escribe el numero de partidos ganados:");

        do{
            partidosGanados = Integer.parseInt(System.console().readLine());
            
            if(partidosGanados > partidosJugados){
                System.out.println("El numero de partidos ganados no puede ser mayor que el numero de partidos jugados");
            }

            if(partidosGanados < 0){
                System.out.println("El numero de partidos ganados no puede ser menor que 0");
            }

            partidosMaximos = partidosJugados - partidosGanados;

        } while (partidosGanados > partidosJugados || partidosGanados < 0);

        System.out.println("Escribe el numero de partidos empatados:");
        do{
            partidosEmpatados = Integer.parseInt(System.console().readLine());

            if(partidosEmpatados > partidosJugados){
                System.out.println("El numero de partidos empatados no puede ser mayor que el numero de partidos jugados");
            }

            if(partidosEmpatados > partidosMaximos){
                System.out.println("La suma de partidos jugados y empatados no puede ser mayor que el numero de partidos jugados");
            }
            
            if(partidosEmpatados < 0){
                System.out.println("El numero de partidos empatados no puede ser menor que 0");
            }
        } while(partidosEmpatados > partidosMaximos || partidosEmpatados > partidosJugados || partidosEmpatados < 0);

            int partidosPerdidos = partidosJugados - (partidosGanados + partidosEmpatados);

            int puntos = partidosGanados * 3 + partidosEmpatados;

            int resultado = pd.guardar(nombreEquipo, partidosJugados, partidosGanados, partidosEmpatados, partidosPerdidos, puntos);
        
        switch(resultado){
            case -1:
                System.out.println("Error al guardar el equipo");
                break;
            case -2:
                System.out.println("No se pueden guardar mas equipos");
                break;
            case 0:
                System.out.println("Equipo guardado correctamente");
                break;
        }
    }

    public static void tablaFormateada(String[] info){
        System.out.print("Nombre del equipo     Jugados                  Ganados                  Empatados                Perdidos                  Puntos\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");

        for (int i = 0; i < info.length; i++) {
            // En cada fila sabemos que hay como maximo 5 elementos, por lo que si i es multiplo de 6, significa que hemos llegado al final de la fila y hacemos un salto de linea
            // y un separador de lineas
            if (i % 6 == 0 && info[i] != null && i != 0) {
                System.out.println();
                System.out.print("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");  // Salto de línea después de cada quinto elemento
            }
            // Si no es null el elemento lo imprimeros, ocurre que los elementos visualmente quedarian descolocados, por lo que le daremos un formato
            // con printf para que queden alineados
            if (info[i] != null) {
                System.out.printf("%-25s", info[i]);
        

            }
        }

        System.out.println("\n");
    }
}