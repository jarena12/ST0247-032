clase  pública Punto1_1 {

public  static  int  levenshtein ( String  a , String  b ) {
        // Matriz
        int [] [] ma =  new  int [a . longitud () +  1 ] [b . longitud () +  1 ];

        // Inicializa la primera fila y la primera columna de la matriz.
        para ( int i =  0 ; i < ma . length; i ++ )
        ma [i] [ 0 ] = i;

        para ( int j =  0 ; j < ma [ 0 ] . longitud; j ++ )
        ma [ 0 ] [j] = j;

        int aux =  0 ;
        // Iteriza sobre la matriz y llena
        para ( int i =  1 ; i < ma . length; i ++ ) {
        para ( int j =  1 ; j < ma [ 0 ] . longitud; j ++ ) {
        aux = a . carácter (i -  1 ) == b . charAt (j -  1 ) ?  0  :  1 ;
        // aux = a.charAt (i - 1) == b.charAt (j - 1)? 0: 2;
        ma [i] [j] =  Matemáticas . min ( Matem . min (ma [i -  1 ] [j] +  1 , ma [i] [j -  1 ] +  1 ), ma [i -  1 ] [j -  1 ] + aux));
        }
        }
        devuelve ma [a . longitud ()] [b . longitud()];
        }

        }