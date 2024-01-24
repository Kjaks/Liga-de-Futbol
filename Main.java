import java.io.*;

public class Main{
    public static void main(String args[]){
        FicheroFutbol pd = new FicheroFutbol();
        int opcion = 0;
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
                    System.out.println("La tabla de la liga es la siguiente:");
                    System.out.println(pd.ShowTable());
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

        } while(opcion < 1 || opcion > 7);

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

        if(pd.guardar(nombreEquipo, partidosJugados, partidosGanados, partidosEmpatados, partidosPerdidos, puntos) == -1){
            System.out.println("Error al guardar el equipo");
        } else {
            pd.guardar(nombreEquipo, partidosJugados, partidosGanados, partidosEmpatados, partidosPerdidos, puntos);
        }            
    };

}