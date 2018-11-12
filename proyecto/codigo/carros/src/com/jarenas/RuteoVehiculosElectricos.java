package com.jarenas;

/**
 *
 * @author ljpalaciom, Mauricio Toro
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Clase Ruteo de vehiculos
 * @author Juan Camilo Arenas F
 */
public class RuteoVehiculosElectricos {

    private int n, m, u, breaks;
    private double r, speed, Tmax, Smax, st_customer, Q;
    private Digraph mapa;
    private short tipoEstacion[];
    private float pendienteFuncionCarga[];
    private boolean visitados[];
    private String filename;
    private Pair<Float, Float>[] coordenadas;
    private ArrayList<ArrayList<Pair<Integer, Float>>> rutas;

    /**
     * Constructor
     * @param filename
     */
    public RuteoVehiculosElectricos(String filename)
    {
        this.filename = filename;
        BufferedReader lector;
        String linea;
        String lineaPartida[];
        try {
            lector = new BufferedReader(new FileReader("../DataSets/"+filename));
            double[] valores = new double[10];
            for (int i = 0; i < 10; i++) {
                linea = lector.readLine();
                lineaPartida = linea.split(" ");
                valores[i] = Float.parseFloat(lineaPartida[2]);
            }

            n = (int) valores[0];
            m = (int) valores[1];
            u = (int) valores[2];
            breaks = (int) valores[3];
            r = valores[4];
            speed = valores[5];
            Tmax = valores[6];
            Smax = valores[7];
            st_customer = valores[8];
            Q = valores[9];

            lector.readLine();
            lector.readLine();
            lector.readLine();

            coordenadas = new Pair[n];
            tipoEstacion = new short[n];
            mapa = new DigraphAM(n);
            for (int i = 0; i <= m; i++) {
                linea = lector.readLine();
                lineaPartida = linea.split(" ");
                coordenadas[Integer.parseInt(lineaPartida[0])] = new Pair(Float.parseFloat(lineaPartida[2]), Float.parseFloat(lineaPartida[3]));
                tipoEstacion[Integer.parseInt(lineaPartida[0])] = -1; //-1 significa cliente
            }

            for (int i = 0; i < u; i++) {
                linea = lector.readLine();
                lineaPartida = linea.split(" ");
                //System.out.println(Integer.parseInt(lineaPartida[0]));
                coordenadas[Integer.parseInt(lineaPartida[0])] = new Pair(Float.parseFloat(lineaPartida[2]), Float.parseFloat(lineaPartida[3]));
                tipoEstacion[Integer.parseInt(lineaPartida[0])] = Short.parseShort(lineaPartida[5]);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    mapa.addArc(i, j,
                        Math.sqrt(
                            Math.pow(coordenadas[i].first  - coordenadas[j].first,  2) +
                            Math.pow(coordenadas[i].second - coordenadas[j].second, 2)
                        )
                    );
                }
            }

            pendienteFuncionCarga = new float[3];
            lector.readLine();
            lector.readLine();
            lector.readLine();
            for (int i = 0; i < 3; ++i) {
                linea = lector.readLine();
                lineaPartida = linea.split(" ");
                pendienteFuncionCarga[i] = Float.parseFloat(lineaPartida[3]);
            }
            lector.readLine();
            lector.readLine();
            lector.readLine();
            for (int i = 0; i < 3; ++i) {
                linea = lector.readLine();
                lineaPartida = linea.split(" ");
                pendienteFuncionCarga[i] = Float.parseFloat(lineaPartida[3]) / pendienteFuncionCarga[i];
            }
            
            // Leer respuesta

            try {
                rutas = new ArrayList();
                lector = new BufferedReader(new FileReader("../DataSets/respuesta-" + filename));
                String respuesta = lector.lines().collect(Collectors.joining());
                respuesta = respuesta.substring(1, respuesta.length() - 1);
                String[] rutasRespuesta = respuesta.split("],");
                for (String ruta : rutasRespuesta) {
                    ArrayList<Pair<Integer, Float>> rutaArrayList = new ArrayList();
                    ruta = ruta.substring(1);
                    String parejas[] = ruta.split("}, ");
                    for (String pareja : parejas) {
                        pareja = pareja.substring(1);
                        String[] elementos = pareja.split(",");
                        elementos[0] = elementos[0].replace("{", "");
                        elementos[1] = elementos[1].replace("}", "");
                        elementos[1] = elementos[1].replace("]", "");
                        Pair<Integer, Float> parejaPair = new Pair(Integer.parseInt(elementos[0]), Float.parseFloat(elementos[1]));
                        rutaArrayList.add(parejaPair);
                    }
                    rutas.add(rutaArrayList);
                }
            }
            catch(Exception ex)
            {
                //System.out.println("No se pudo leer respuesta");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     *
     * @param tiempoSolucion
     * @return String con datos de entrada y tiempo de solucion
     */
    //@Override
    public String toString(long tiempoSolucion) {
        return "RuteoVehiculosElectricos{" + "r=" + r + ", speed=" + speed + ", Tmax=" + Tmax + ", Smax=" + Smax + ", st_customer=" + st_customer + ", Q=" + Q + ", tiempoSolucion=" + tiempoSolucion + '}';
    }

    /**
     *  Validar datos
     * @return
     */
    public boolean validar(){
        
        int i = 0;
        for (ArrayList<Pair<Integer, Float>> ruta : rutas){                    
            float tiempo = 0;
            double carga = (double)Q;
            for (int j =0; j< ruta.size() - 1; j++){
                Pair<Integer, Float> parejaJ = ruta.get(j);
                Pair<Integer, Float> parejaJPlus1 = ruta.get(j+1);
                carga = carga - mapa.getWeight(parejaJ.first,parejaJPlus1.first)*r;
                if (tipoEstacion[parejaJ.first] >= 0)
                    carga = carga + pendienteFuncionCarga[tipoEstacion[parejaJ.first]] * parejaJ.second;

                tiempo += parejaJ.second;
            }
            if (tiempo > Tmax) {
                System.out.println("La ruta " + i + " supera el Tmax");
                return false;
            }
            ++i;
            if (carga < 0) {
                System.out.println("La ruta " + i + " no respeta la restricción de carga");
                return false;
            }

        }
        return true;
    }


    /**
     * Imprime solucion
     * @param distancias
     * @param n
     */
    private void imprimirSolucion(double distancias[], int n)
    {
        //TODO: Aqui debe imprimir el resultado final
        for (int i=0; i<n; i++)
            System.out.println("Distancias: "+(distancias[i]));
    }

    /**
     * Calcula minima distancia entre nodos
     *
     * @author Juan Camilo Arenas R
     * @param distancias Arreglo con distancias
     * @param procesados Arreglo con nodos procesados
     */
    public int minimaDistancia(double distancias[], Boolean procesados[])
    {
        double min=Integer.MAX_VALUE;
        int min_index=-1;
        for (int v=0; v<n; v++)
            if (procesados[v] == false && distancias[v] <= min){
                min=distancias[v];
                min_index=v;
            }
        return min_index;
    }

    /**
     * Procesa
     */
    public void procesar()
    {

        visitados = new boolean[n];

        double tiempo=0,tiempoRuta=0;
        int clientes=0;
        int rutas=1;
        double carga = Q;
        int currentNode = 0;
        int nextNode=0;
        visitados[0]=true;
        while(clientesPendientes() > 0)
        {
            nextNode = nodoMasCercano(currentNode);

            visitados[nextNode] = true;

            double distancia = mapa.getWeight(currentNode,nextNode);
            tiempo += (distancia/speed);
            tiempoRuta += (distancia/speed);
            carga = carga - (distancia * r);

            currentNode=nextNode;

            if(tipoEstacion[currentNode] >= 0){ // Es estacion de carga
                double tiempoCargando = (Q - carga) / pendienteFuncionCarga[tipoEstacion[currentNode]];
                carga=Q;
                tiempo = tiempo + tiempoCargando;
                tiempoRuta = tiempoRuta + tiempoCargando;
            }else{
                if(currentNode != 0){ // Si no esta en deposito
                    clientes++;
                    tiempo = tiempo + st_customer;
                    tiempoRuta = tiempoRuta + st_customer;
                    if(tiempoRuta >= Tmax){
                        carga = Q;
                        currentNode = 0;
                        rutas++;
                        tiempoRuta=0;
                    }
                }
            }

            if(carga - (mapa.getWeight(currentNode,0) * r) < 0){
                carga = Q;
                currentNode = 0;
                rutas++;
                tiempoRuta=0;
            }
        }

        System.out.println("Clientes visitados:"+ clientes + " Rutas: "+ rutas + " Tiempo:" + tiempo);

        System.out.println(" ");

    }

    /**
     * Retorna numero de clintes pendientes
     * @return pendientes
     */
    public int clientesPendientes()
    {
        int pendientes=0;
        for(int i=1;i<n;i++){
            if(!visitados[i] && tipoEstacion[i] >= 0){
                pendientes++;
            }
        }

        return pendientes;
    }

    /**
     * Encuentra nodo mas cercano desde un nodo
     * @param nodeNum
     * @return Nodo mas cercano
     */

    public int nodoMasCercano(int nodeNum)
    {
        double distancia=Integer.MAX_VALUE;
        int nodo=0;
        for(int i=0;i<mapa.size;i++){
            if(mapa.getWeight(nodeNum,i) < distancia && !visitados[i]){
                distancia=mapa.getWeight(nodeNum,i);
                nodo=i;
            }
        }
        return nodo;
    }


}
