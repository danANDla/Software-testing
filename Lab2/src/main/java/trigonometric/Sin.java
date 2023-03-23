package trigonometric;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.IOException;
import java.io.Writer;

public class Sin {
    public double sin(double x, double eps){
        if(x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY){
            return Double.NaN;
        }
        double fact = 1, result_l = 1, result = 0;
        int sign = 1, i = 1;

        if (x >= 0) {
            while (x > Math.PI * 2) {
                x -= Math.PI * 2;
            }
        } else if (x < 0) {
            while (x < Math.PI * 2) {
                x += Math.PI * 2;
            }
        }

        while (Math.abs(result_l - result) > eps) {
            fact = fact / i;
            result_l = result;
            result += sign * Math.pow(x, i) * fact;
            sign = -sign;
            fact = fact / (i + 1);
            i += 2;
        }
        if (Math.abs(result) > 1) return Double.NaN;
        if (Math.abs(result) < eps) return 0;
        return result;
    }
    public double writeSinToCSV(double x, double eps, Writer out) {
        double res = sin(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
        return res;
    }
}