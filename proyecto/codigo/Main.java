package com.jarena;



public class Main {

    public static void main(String[] args) {
        com.jarena.RuteoVehiculosElectricos problema1 = new com.jarena.RuteoVehiculosElectricos("tc2c320s24cf0.txt"); //tc2c320s24cf0.txt
        if(problema1.validar()){
            problema1.procesar();
            System.out.println(problema1.toString());
        }
    }
}

//recore todos los nodos con la mejor ruta optima e imprime las distancias, y los otros resultados son de los archivos que trae