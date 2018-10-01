package com.jarena;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        /*
            Lee en consola los casos de prueba y las rutas de dia y noche hasta que el
            usuario ingrese "0 0 0" o llegue a 100 casos de prueba.
            Almacena en la variable resultados los resultados de cada caso de prueba
         */
        Integer[] resultados = new Integer[100];
        Integer count=0;
        String casoPrueba = "";

        while(!casoPrueba.equals("0 0 0") && count < 100){
            System.out.println("Entre caso de prueba:");
            Scanner scanner = new Scanner(System.in);
            casoPrueba = scanner.nextLine();

            if(!casoPrueba.equals("0 0 0")) {
                System.out.println("Entre duracion rutas dia:");
                String rutasDia = (scanner.nextLine());

                System.out.println("Entre duracion rutas noche:");
                String rutasNoche = (scanner.nextLine());

                //Almacena en la variable resultados los resultados de cada caso de prueba
                resultados[count]  = procesarCasoPrueba(casoPrueba,rutasDia,rutasNoche);
                count++;
            }
        }

        /*
           Imprime en consola el arreglo con los resultados de los cados de prueba
         */
        for(int i=0;i<resultados.length;i++){
            if(resultados[i] != null) {
                System.out.println(resultados[i]);
            }
        }
    }

    /*
        Procesa cada caso de prueba.
        Recibe la cadena caso de prueba y las rutas de dia y de noche.
        Retorna el minimo valor posible de horas extras para el cado de prueba

     */
    private static Integer procesarCasoPrueba(String sCasoPrueba,String sRutasDia,String sRutasNoche)
    {
        /*
           Convierte las cadenas ingresadas en arrays
         */
        String[] casoPrueba = sCasoPrueba.split(" ");
        String[] rutasDia   = sRutasDia.split(" ");
        String[] rutasNoche = sRutasNoche.split(" ");

        Integer n = Integer.parseInt(casoPrueba[0]);
        Integer d = Integer.parseInt(casoPrueba[1]);
        Integer r = Integer.parseInt(casoPrueba[2]);

        Integer minimoValor=0;

        /*
            Para cada n suma los valores de las rutas de dia y noche,
            si el resultado es mayor a minimoValor se asigna como nuevo minimoValor

         */
        for(int i=0;i<n;i++) {
            /*
               if(Integer.parseInt(rutasDia[i]) > 10000 || Integer.parseInt(rutasNoche[i]) > 10000){
                   //TODO: Salir con error
               }
             */
            Integer tmp=Integer.parseInt(rutasDia[i]) + Integer.parseInt(rutasNoche[i]);
            if(minimoValor < tmp){
                minimoValor = tmp;
            }
            System.out.println(minimoValor);
        }

        //Retorna minimoValor que es el acumulado menos d (maximo hras por dia) y lo multiplica por r (valor hora)
        return (minimoValor-d)*r;
    }
}
