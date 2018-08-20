#include <stdio.h>
#include <stdlib.h>
#define false 0
#define true 1

int numero_reinas=6;
int arreglo[6];
int  puede_ubicar(int x2, int y2);
void ubica_reina(int x, int size);
int main(int argv,char **argc);

int main(int argv,char **argc)
{
	int n = numero_reinas;
	printf("Orden del las %d reinas\n",n);
	ubica_reina(0, n);
}

/***********************************************************
	Avanza sobre el arreglo
	Evalua si se puede ubicar la reina
	Si
	   Imprime el arreglo y se vuelve a llamar recursivamente
	No 
	   Avanza a proxima posicion y repite el proceso
	Asi hasta que llegue al final del arreglo
*********************************************************************/
   
void ubica_reina(int x, int size) 
{
	int i;
	for (i = 0; i < size; i++) {
		if (puede_ubicar(x, i)) {
			arreglo[x] = i; // Ubica la reina en esta posición
			if (x == size - 1) {
				printf("[%d,%d,%d,%d %d %d]\n",arreglo[0], arreglo[1], arreglo[2], arreglo[3],arreglo[4], arreglo[5]);
			}
			ubica_reina(x + 1, size);
		}
	}
}

/*********************************************************************
	Esta funcion cheque si la reina puede ser ubicada en (x,y)
	verificando si hay una reina en la misma fila columna o en diagonal
**********************************************************************/
int  puede_ubicar(int x, int y) 
{
	int i;
	for (i = 0; i < x; i++) {
		if ((arreglo[i] == y) || (abs(i - x) == abs(arreglo[i] - y))) 
		{
			return false;
		}
	}
	return true;
}


