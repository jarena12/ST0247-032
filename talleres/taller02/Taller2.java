import java.util.ArrayList;

public class Taller2 {

    public static ArrayList<String> combinations(String s) {
        ArrayList<String> list = new ArrayList<>();
        combinations("", s, list);
        return list;
    }


    private static void combinations(String pre, String pos, ArrayList<String> list) {
        if (pos.length() < 2) {
            list.add(pre + pos);
            list.add(pre);
        } else {
            combinations(pre + pos.charAt(0), pos.substring(1),list);
            combinations(pre, pos.substring(1),list);
        }
    }

    public static ArrayList<String> permutations(String s) {
        ArrayList<String> list = new ArrayList<>();
        permutations("", s, list);
        return list;
    }

    private static void permutations(String pre, String pos, ArrayList<String> list) {
        if (pos.length() < 2) {
            list.add(pre + pos);
        } else {
            for (int i = 0; i < pos.length(); ++i) {
                permutations(pre + pos.charAt(i), pos.substring(0,i) + pos.substring(i+1), list);
            }
        }
    }

    public static void imprimirTablero(int[] tablero) {
        int n = tablero.length;
        System.out.print("    ");
        for (int i = 0; i < n; ++i)
            System.out.print(i + " ");
        System.out.println("\n");
        for (int i = 0; i < n; ++i) {
            System.out.print(i + "   ");
            for (int j = 0; j < n; ++j)
                System.out.print((tablero[i] == j ? "Q" : "#") + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static boolean esValido(int[] tablero) {
        for (int i : tablero) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < tablero.length - 1; ++i) {
            for (int j = i + 1; j < tablero.length; ++j) {
                if (j - i == Math.abs(tablero[i] - tablero[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
