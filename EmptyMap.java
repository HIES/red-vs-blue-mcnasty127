import java.lang.Exception;
import java.io.File;
import java.util.Scanner;
import static java.lang.System.*;

public class EmptyMap
{
    public void visualize(String region) throws Exception
    {
        String fileName = region;
        String extension = ".txt";
        File inputFile = new File(fileName+extension);
        Scanner inputObject = new Scanner(inputFile);
        double xmin = inputObject.nextDouble();
        double ymin = inputObject.nextDouble();
        inputObject.nextLine();
        double xmax = inputObject.nextDouble();
        double ymax = inputObject.nextDouble();
        inputObject.nextLine();
        int n = inputObject.nextInt();
        inputObject.nextLine();
        for(int i = 0; i < n; i++){
            for(int m = 0; m < 3; m++){
                inputObject.nextLine();
            }
            int x = inputObject.nextInt();
            double[] xs = new double[x];
            double[] ys = new double[x];
            for(int y = 0; y < x; y++){
                xs[y] = inputObject.nextDouble();
                ys[y] = inputObject.nextDouble();
                inputObject.nextLine();
            }
            StdDraw.polygon(xs,ys);
        }
    }
}
