package switch2021.project.utils;

public class Utils {

    private Utils() {
    }

    public static boolean isPerfectSquare(int x) {
        int square = (int) Math.sqrt(x);
        return (square * square == x);
    }

    public static boolean isFibonacci(int n) {
        return isPerfectSquare(5*n*n + 4) || isPerfectSquare(5*n*n-4);
    }
}
