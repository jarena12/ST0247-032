package com.jarenas;



public class Main {

    public static void main(String[] args) {
        String files[] = {
        "tc2c320s24cf0.txt",
        "tc2c320s24cf1.txt",
        "tc2c320s24cf4.txt",
        "tc2c320s24ct0.txt",
        "tc2c320s24ct1.txt",
        "tc2c320s24ct4.txt",
        "tc2c320s38cf0.txt",
        "tc2c320s38cf4.txt",
        "tc2c320s38ct0.txt",
        "tc2c320s38ct1.txt",
        "tc2c320s38ct4.txt"};
        long tiempoSolucion;


        for(int i=0;i< files.length;i++) {
            long startTime = System.currentTimeMillis();

            com.jarenas.RuteoVehiculosElectricos problema1 = new com.jarenas.RuteoVehiculosElectricos(files[i]); //tc2c320s24cf0.txt
            if (problema1.validar()) {
                problema1.procesar();


            }
            long stopTime = System.currentTimeMillis();
            tiempoSolucion = stopTime - startTime;
            System.out.println(problema1.toString(tiempoSolucion));

        }
    }
}

