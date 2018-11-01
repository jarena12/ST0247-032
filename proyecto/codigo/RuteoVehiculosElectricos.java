package com.jarena;

/**
 *
 * @author ljpalaciom, Mauricio Toro
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class RuteoVehiculosElectricos {

    private int n, m, u, breaks;
    private double r, speed, Tmax, Smax, st_customer, Q;
    private Digraph mapa;
    private short tipoEstacion[];
    private float pendienteFuncionCarga[];
    private String filename;
    private Pair<Float, Float>[] coordenadas;
    private ArrayList<ArrayList<Pair<Integer, Float>>> rutas;
    private long tiempoSolucion;

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
                System.out.println("No se pudo leer respuesta");
            }
                
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String toString() {
        return "RuteoVehiculosElectricos{" + "r=" + r + ", speed=" + speed + ", Tmax=" + Tmax + ", Smax=" + Smax + ", st_customer=" + st_customer + ", Q=" + Q + ", tiempoSolucion=" + tiempoSolucion + '}';
    }

    /*
        Valida entrada
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
            //System.out.println(i + " " + carga);
            if (carga < 0) {
                System.out.println("La ruta " + i + " no respeta la restricción de carga");
                return false;
            }

        }
        return true;
    }

    //Imprime solucion.
    private void imprimirSolucion(double distancias[], int n)
    {
        //TODO: Aqui debe imprimir el resultado final
        for (int i=0; i<n; i++)
            System.out.println("Distancias: "+(distancias[i]));
    }

    //Distancia minima entre nodos
    private int minimaDistancia(double distancias[], Boolean procesados[])
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
    //Algoritmo ruta optima y se llama desde main
    public void procesar()
    {
        //Tiempo inicial. Para medir cuanto tiempo gasta

        long startTime = System.currentTimeMillis();
        float tiempo=0;

        double distancias[]=new double[mapa.size()];
        Boolean procesados[]=new Boolean[mapa.size()];

        for (int i=0; i<mapa.size(); i++){
            distancias[i]=Integer.MAX_VALUE;
            procesados[i]=false;
        }

        distancias[0]=0;

        //Recorre grafo marcando los procesados y evaluando la minima distancia entre nodos
        for (int count=0; count<mapa.size(); count++)
        {
            int u=minimaDistancia(distancias, procesados);

            procesados[u]=true;

            for (int v=0; v<mapa.size(); v++) {
                if (!procesados[v] &&
                        mapa.getWeight(u, v) != 0 &&
                        distancias[u] != Integer.MAX_VALUE &&
                        distancias[u] + mapa.getWeight(u, v) < distancias[v]) {
                    //distancias[v] = distancias[u] + (mapa.getWeight(u, v)/speed);

                    if (tipoEstacion[v] >= 0) {
                        tiempo = tiempo + pendienteFuncionCarga[tipoEstacion[v]];
                        //System.out.println(pendienteFuncionCarga[tipoEstacion[v]]);
                    }
                    distancias[v] = (mapa.getWeight(u, v) / speed);
                }
            }
        }

        long stopTime = System.currentTimeMillis();
        tiempoSolucion = stopTime - startTime;

        imprimirSolucion(distancias, mapa.size());

    }
}
