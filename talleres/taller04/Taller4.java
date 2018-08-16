import java.util.ArrayList;
import java.util.Stack;

public class Taller4 {

    public static boolean hayCaminoDFS(Digraph g, int v, int w) {
        boolean[] visitados = new boolean[g.size()];
        return hayCaminoDFS(g, v, w, visitados);
    }

    public static boolean hayCaminoDFS(Digraph g, int v, int w, boolean[] visitados) {

        if(v == w) return true;

        if(visitados[v]) return false;

        visitados[v] = true;

        ArrayList<Integer> successors = g.getSuccessors(v);

        if(successors == null) return false;

        for(Integer i: successors) {
            if(hayCaminoDFS(g, i, w, visitados)) return true;
        }
        return false;
    }


    public static int costoMinimo(Digraph g, int inicio, int fin) {

        int numVertices = g.size();

        boolean [] q = new boolean[numVertices];

        int [] dist = new int[numVertices];

        int [] prev = new int[numVertices];

        for(int i = 0; i < numVertices; i++) {
            dist[i] = Integer.MAX_VALUE;        // infinito
            prev[i] = -1;                       // no definido
            q[i] = true;                        // no visitado
        }

        dist[inicio] = 0;

        while(qNotEmpty(q)) {
            int u = vertexWithMinDistance(q, dist);
            q[u] = false;
            if(u == fin) {
                imprimirRecorrido(prev, fin);
                break;
            }
            ArrayList<Integer> successors = g.getSuccessors(u);
            if(successors == null) continue;
            for(Integer v: successors) {
                int alt = dist[u] + g.getWeight(u, v);
                if(alt < dist[v]){
                    dist[v] = alt;
                    prev[v] = u;
                }
            }
        }

        return dist[fin];
    }

    private static boolean qNotEmpty(boolean [] q) {
        for(int i = 0; i < q.length; i++) {
            if(q[i]) return true;
        }
        return false;
    }

    private static int vertexWithMinDistance(boolean [] q, int [] dist) {
        int vert = -1;
        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < q.length; i++) {
            if(q[i] && dist[i] <= minDist) {
                vert = i;
                minDist = dist[i];
            }
        }
        return vert;
    }

    public static void imprimirRecorrido(int [] prev, int fin) {
        System.out.println("Recorrido: ");
        Stack s = new Stack();
        s.push(fin);
        int prevNode = prev[fin];
        while(prevNode != -1) {
            s.push(prevNode);
            prevNode = prev[prevNode];
        }
        while(!s.empty()) {
            System.out.println(s.pop());
        }
    }

    public static int recorrido(Digraph g) {
        return 0;
    }

    private static int recorrido(Digraph g, int v, int[] unvisited) {

        return 0;
    }


    private static void dfs(Digraph g, int v, int[] costo) {

    }

    public static void probarHayCaminoDFS() {
        DigraphAM matriz = new DigraphAM(7);
        matriz.addArc(0, 1, 1);
        matriz.addArc(1, 2, 1);
        matriz.addArc(1, 3, 1);
        matriz.addArc(2, 1, 1);
        matriz.addArc(2, 4, 1);
        matriz.addArc(3, 6, 1);
        matriz.addArc(4, 5, 1);
        matriz.addArc(4, 6, 1);
        System.out.println(hayCaminoDFS(matriz, 1, 6));
    }

    public static void probarCostoMinimo() {
        DigraphAM matriz = new DigraphAM(7);
        matriz.addArc(0, 1, 10);
        matriz.addArc(1, 2, 10);
        matriz.addArc(1, 3, 20);
        matriz.addArc(2, 1, 30);
        matriz.addArc(2, 4, 10);
        matriz.addArc(3, 6, 40);
        matriz.addArc(4, 5, 30);
        matriz.addArc(4, 6, 10);
        System.out.println("Costo es: " + costoMinimo(matriz, 1, 6));
    }
}