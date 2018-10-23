import  java.util.ArrayList ;
import  java.util.Collections ;


        público  de clase  Taller3 {




        privado  estática  int  nReinas ( int  n , int  col , int [] [] tablero ) {
        int i, j;


        para (i =  0 ; i < col; i ++ )
        si (tablero [fila] [i] ==  1 )
        volver  falsa ;


        para (i = fila, j = col; i > = 0  && j > = 0 ; i - , j - )
        si (tablero [i] [j] ==  1 )
        volver  falsa ;


        para (i = fila, j = col; j > = 0  && i < N ; i ++ , j - )
        si (tablero [i] [j] ==  1 )
        volver  falsa ;

        volver  verdadera ;
        }


        boolean  solveNQUtil ( int  tablero [] [], int  col )
        {

        si (col > = n)
        volver  verdadera ;


        para ( int i =  0 ; i < n; i ++ )
        {

        if (isSafe (tablero, i, col))
        {

        tablero [i] [col] =  1 ;


        if (resolver NQUtil (tablero, col +  1 ) ==  verdadero )
        volver  verdadera ;


        tablero [i] [col] =  0 ; // BACKTRACK
        }
        }


        volver  falsa ;
        }

        vacío estático  público imprimirTablero ( int [] tablero ) {
        int n = tablero . longitud;
        Sistema . a cabo . imprimir ( "     " );
        para ( int i =  0 ; i < n; ++ i)
        Sistema . a cabo . imprimir (i +  "  " );
        Sistema . a cabo . println ( " \ n " );
        para ( int i =  0 ; i < n; ++ i) {
        Sistema . a cabo . imprimir (i +  "    " );
        para ( int j =  0 ; j < n; ++ j)
        Sistema . a cabo . print ((tablero [i] == j ?  " Q "  :  " # " ) +  "  " );
        Sistema . a cabo . println ();
        }
        Sistema . a cabo . println ();
        }

        Lista estática  pública ArrayList < Integer >  camino ( Digraph  g , int  inicio , int  fin ) {
        // completar ...
        }

        // recomendacion
        privadas  estáticas  booleanos  dfs ( Digraph  g , int  nodo , int  objetivo m , booleanos [] visitados , ArrayList < Entero >  lista ) {
        // completar ...
        }

        }