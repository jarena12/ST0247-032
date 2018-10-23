importar numpy como np
        importar GraphAm como gm

        def  toBinary ( pos , tamaño ):
        binario = []
        mientras que  int (pos) >  0 :
        binary.append ( int (pos) % 2 )
        pos =  int (pos / 2 )
        temp = [ 0 ] * (tamaño - len (binario) - 1 )
        temp = binary + temp
        # temp.reverse ()
        return [ 0 ] + temp

        def  toDecimal ( sqnc ):
        val =  0
        sqnc2 = sqnc [ 1 :]
        j =  0
        para i en  rango ( 0 , len (sqnc2)):
        si sqnc2 [i] ==  1 :
        val + =  2 ** i
        j + = 1
        volver val

        def  Holdkarpy ( g ):
        hkmatriz = []
        tempMat = [ - 1 ] * ( 2 ** (g.size - 1 ))
        para l en  rango (g.size):
        hkmatriz.append (tempMat [:])
        hkdaddy = [[ - 1  para _ en  rango ( 2 ** (g.size - 1 ))]] * g.size
        para i en el  rango ( 0 , ( 2 ** (g.size - 1 ))):
        subconjunto = toBinary (i, g.size)
        para j en  rango ( 0 , g.size):
        # imprimir (i)
        hkmatriz [j] [i] = ruta más corta (g, subconjunto, j, hkmatriz)
        para k en hkmatriz:
        imprimir (k)
        imprimir ( " --------------- " )
        volver hkmatriz [ 0 ] [ - 1 ]




        def  sqsize ( sqnc ):
        i =  0
        para j en sqnc:
        si j ==  1 :
        i + = 1
        volver i


        def  shortestPath ( g , sqnc , extremo , matriz ):
        sols = []
        si sqsize (sqnc) ==  0 :
        devuelve g.getWeight ( 0 , final)
        para i en el  rango ( 0 , len (sqnc)):
        si sqnc [i] == 0 :
        continuar
        temp = sqnc [:]
        temp [i] = 0
        # print ("sqnc", sqnc)
        # print ("temp", temp)
        # print ("fin", fin)
        # print ("todecimal", toDecimal (temp))
        cand = g.getWeight (i, end) + matrix [i] [toDecimal (temp)]
        sols.append (cand)
        # imprimir (soles)
        champ =  min (soles)
        campeón de vuelta

        g1 = gm.GraphAm ( 4 )
        g1.addArc ( 0 , 1 , 1 )
        g1.addArc ( 0 , 2 , 15 )
        g1.addArc ( 0 , 3 , 6 )
        g1.addArc ( 1 , 0 , 2 )
        g1.addArc ( 1 , 2 , 7 )
        g1.addArc ( 1 , 3 , 3 )
        g1.addArc ( 2 , 0 , 9 )
        g1.addArc ( 2 , 1 , 6 )
        g1.addArc ( 2 , 3 , 12 )
        g1.addArc ( 3 , 0 , 10 )
        g1.addArc ( 3 , 1 , 4 )
        g1.addArc ( 3 , 2 , 8 )

        imprimir (heldkarpy (g1))