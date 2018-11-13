package com.jarena;

public class Nodo
{
    private int nodeNum;
    private boolean visitado;
    private String tipo;
    private short tipoEstacion;
    private float funcionCarga;
    private double carga;

    public Nodo(int nodeNum,short tipoEstacion,double carga,float funcionCarga)
    {
        this.nodeNum=nodeNum;
        this.tipo=tipo;
        this.visitado=false;
        this.tipoEstacion=tipoEstacion;
        this.funcionCarga=funcionCarga;
        this.carga = carga;
    }

    public int nodoMasCercano(Digraph mapa,boolean visitados[])
    {
        double distancia=Integer.MAX_VALUE;
        int nodo=0;
        for(int i=0;i<mapa.size;i++){
            if(mapa.getWeight(this.nodeNum,i) < distancia && !visitados[i]){
                distancia=mapa.getWeight(this.nodeNum,i);
                nodo=i;
            }
        }
        return nodo;
    }


}
