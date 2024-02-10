public class Main {
    // Вычисление нормы вектора
    public static double norm(double[] v) {
        double sum = 0;
        for (double x : v) {
            sum += x * x;
        }
        return Math.sqrt(sum);
    }

    // Вычисление скалярного произведения двух векторов
    public static double dot(double[] u, double[] v) {
        double sum = 0;
        for (int i = 0; i < u.length; i++) {
            sum += u[i] * v[i];
        }
        return sum;
    }

    // Вычисление произведения матрицы и вектора
    public static double[] multiply(double[][] A, double[] x) {
        int n = x.length;
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            y[i] = dot(A[i], x);
        }
        return y;
    }

    // Метод для вычитания двух векторов
    public static double[] subtract(double[] u, double[] v) {
        int n = u.length;
        double[] w = new double[n];
        for (int i = 0; i < n; i++) {
            w[i] = u[i] - v[i];
        }
        return w;
    }

    // Умножение вектора на скаляр
    public static double[] scale(double[] v, double a) {
        int n = v.length;
        double[] w = new double[n];
        for (int i = 0; i < n; i++) {
            w[i] = v[i] * a;
        }
        return w;
    }

    // Решение системы линейных уравнений с помощью метода наискорейшего спуска
    public static double[] solve(double[][] A, double[] b, double[] x0, double eps) {
        int k = 0;                                                                      // счётчик итераций
        double[] x = x0;                                                                // текущее приближение
        double[] g = subtract(multiply(A, x), b);                                       // градиент функции в текущей точке
        while (norm(g) > eps) {                                                         // считаем до точности
            double alpha = dot(g, g) / dot(g, multiply(A, g));                          // длина шага
            x = subtract(x, scale(g, alpha));                                           // обновление приближения
            g = subtract(multiply(A, x), b);                                            // обновление градиента
            k++;
        }
        System.out.println("Количество итераций: " + k);
        return x; // возвращаем решение
    }

    public static void printAnswers(double[] x) {
        int n = x.length;
        for (int i = 0; i < n; i++) {
            System.out.printf("x%d = %.10f\n", i+1, x[i]);
        }
    }

    public static void main(String[] args) {

        // Матрица коэффициентов
        double[][] A = {{1.00, 0.42, 0.54, 0.66},
                        {0.42, 1.00, 0.32, 0.44},
                        {0.54, 0.32, 1.00, 0.22},
                        {0.66, 0.44, 0.22, 1.00}};

        // Вектор правых полей
        double[] b =    {0.3,
                        0.5,
                        0.7,
                        0.9};

        double[] x0 = {0, 0, 0, 0};     // начальное приближение
        double eps = 0.000000000001;              //точность

        System.out.println("Решение системы: ");
        printAnswers(solve(A, b, x0, eps));
    }
}