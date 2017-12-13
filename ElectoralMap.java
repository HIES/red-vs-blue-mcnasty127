import java.lang.Exception;
import java.io.File;
import java.util.Scanner;
import static java.lang.System.*;
import java.awt.*;
public class ElectoralMap
{
    public class Subr{
        private String name;
        private int[] votes;
        private double[] xCors;
        private double[] yCors;
        private Color color;
       public Subr(double[] xs, double[] ys, String subrname){
            xCors = xs;
            yCors = ys;
            name = subrname;
        }
        public void addVotes(int[] rdi){
            if(rdi[2] < rdi[0] && rdi[2] < rdi[1]){
                int dc = rdi[1] - rdi[2];
                if(dc > 0){
                    //color = RED;
                }
                else{
                    //color = BLUE;
                }
            }
            else{
                //color = GRAY;
            }
        }
    }
    public static void sampleMethod(String votingdata) throws Exception
    {
        String fileName = votingdata;
        String extension = ".txt";
        File inputFile = new File("input/"+fileName+extension);
        Scanner inputObject = new Scanner(inputFile);
        double xmin = inputObject.nextDouble();
        double ymin = inputObject.nextDouble();
        inputObject.nextLine();
        double xmax = inputObject.nextDouble();
        double ymax = inputObject.nextDouble();
        inputObject.nextLine();
        int n = inputObject.nextInt();
        inputObject.nextLine();
        StdDraw.setCanvasSize((((int)xmax-(int)xmin)*512)/((int)ymax-(int)ymin),512);
        StdDraw.setXscale(xmin,xmax);
        StdDraw.setYscale(ymin,ymax);
        StdDraw.setPenColor(0,0,0);
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
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
        StdDraw.show();
        inputObject.close();
    }
}
