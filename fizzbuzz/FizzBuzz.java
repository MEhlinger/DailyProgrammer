// FizzBuzz in Java
// by Marshall Ehlinger

public class FizzBuzz {

    public static void main (String[] args) {
        // Test Program
        final int MAX = 100;
        fizzBuzz(MAX);
    }

    public static void fizzBuzz(int max) {
        for (int i = 1; i <= max; i++) {
            StringBuilder line = new StringBuilder();
            if (i % 3 == 0) {
                line.append("fizz");
            }
            if (i % 5 == 0) {
                line.append("buzz");
            }
            if (line.length() == 0) {
                line.append(i);
            }
            System.out.println(line);
        }
    }
}