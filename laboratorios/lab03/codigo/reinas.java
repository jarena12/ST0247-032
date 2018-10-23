público  de clase  Reinas
        {

        privadas  estáticas  int nums;



        booleano estático  privado PonerReina ( int r , int [] tablero ) {
        para ( int i =  0 ; i < r; i ++ ) {
        si (tablero [i] == tablero [r])
        volver  falsa ;
        if ( Math . abs (tablero [i] - tablero [r]) ==  Math . abs (i - r))
        volver  falsa ;
        }
        volver  verdadera ;
        }


        int nReinas públicas estáticas  ( int n ) {
        numS =  0 ;
        int [] tablero =  new  int [n];
        devuelve nReinas ( 0 , n, tablero);
        }



        privadas  estáticas  int  nReinas ( int  r , int  n , int [] tablero ) {
        si (r == n) {

        numS ++ ;
        } else {
        para ( int i =  0 ; i < n; i ++ ) {
        tablero [r] = i;
        if (puedoPonerReina (r, tablero))
        nReinas (r + 1 , n, tablero);
        }
        }
        devuelve numS;
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

public  static  void  main ( String [] args ) {
        largo m =  sistema . currentTimeMillis ();
        nReinas ( 17 );
        Sistema . a cabo . println ( System . currentTimeMillis () - m);

        }
        }