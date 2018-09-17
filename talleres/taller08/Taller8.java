public class taller8 {
    long T;
    long TI;
    long TF;
    int longitudArr = 20;
    int[] ArrQuick = new int[longitudArr];
    int[] ArrMerg = new int[longitudArr];
    int[] array;
    int[] tempMergArr;
    public void llenarArreglo() {
        TI = System.currentTimeMillis();
        for (int i = 0; i < longitudArr; i++) {
            int cx = (int) (Math.random() * 100) + 1;
            ArrQuick[i] = cx;
            ArrMerg[i] = cx;            
        }
        System.out.print("Arreglo sin ordenar: ");
        imprimir(ArrQuick);
        quicksort(ArrQuick,0,ArrQuick.length - 1);
        TF = System.currentTimeMillis();
        T = TF - TI;
        System.out.print("Arreglo ordenado con QuickSort: ");
        imprimir(ArrQuick);
        System.out.println("Tiempo QuickSort: " + T);
        sort(ArrMerg);
        System.out.print("Arreglo ordenado con MergeSort: ");
        imprimir(ArrMerg);
        TF = System.currentTimeMillis();
        T = TF - TI;
        System.out.println("Tiempo MergeSort: " + T);
    }
    public static void quicksort(int x[], int izq, int der) {
        int pivote = x[izq];
        int i = izq;
        int j = der;
        int aux;
        while (i < j) {
            while (x[i] <= pivote && i < j) {
                i++;
            }
            while (x[j] > pivote) {
                j--;
            }
            if (i < j) {
                aux = x[i];
                x[i] = x[j];
                x[j] = aux;
            }
        }
        x[izq] = x[j];
        x[j] = pivote;
        if (izq < j - 1) {
            quicksort(x, izq, j - 1);
        }
        if (j + 1 < der) {
            quicksort(x, j + 1, der);
        }
    }
    public void sort(int x[]) {
        array = x;
        tempMergArr = new int[longitudArr];
        doMergeSort(0, longitudArr - 1);
    }
 
    private void doMergeSort(int lowerIndex, int higherIndex) {
         
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;

            doMergeSort(lowerIndex, middle);

            doMergeSort(middle + 1, higherIndex);

            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
 
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
 
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
 
    }
    public static void imprimir(int x[]){
        for(int i = 0; i < x.length; i++){
            if(i < 9){
                System.out.print(x[i] + ",");
            } else {
                System.out.println(x[i]);
            }
        }
    }
    public static void main(String[] args){
        taller8 T = new taller8();
        T.llenarArreglo();
    }
}