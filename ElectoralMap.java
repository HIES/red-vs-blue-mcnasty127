import java.lang.Exception;
import java.io.File;
import java.util.Scanner;
import static java.lang.System.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.ArrayList;
public class ElectoralMap
{
    static HashMap<String, ArrayList<Subr>> regions = new HashMap<>();
    static class Subr{
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
        public double[] getxs(){
            return xCors;
        }
        public double[] getys(){
            return yCors;
        }
        public void addVotes(int[] rdi){
            votes = rdi;
            if(rdi[2] < rdi[0] && rdi[2] < rdi[1]){
                int dc = rdi[0] - rdi[1];
                if(dc > 0){
                    color = Color.RED;
                }
                else{
                    color = Color.BLUE;
                }
            }
            else{
                color = Color.GRAY;
            }
        }
        public Color getColor(){
            return color;
        }
    }
    public static void sampleMethod(String region, int year) throws Exception
    {
        File inputFile = new File("input/"+region+".txt");
        Scanner inputObject = new Scanner(inputFile);
        double xmin = inputObject.nextDouble();
        double ymin = inputObject.nextDouble();
        inputObject.nextLine();
        double xmax = inputObject.nextDouble();
        double ymax = inputObject.nextDouble();
        inputObject.nextLine();
        int n = inputObject.nextInt();
        inputObject.nextLine(); //empty line
        StdDraw.setCanvasSize((((int)xmax-(int)xmin)*512)/((int)ymax-(int)ymin),512);
        StdDraw.setXscale(xmin,xmax);
        StdDraw.setYscale(ymin,ymax);
        StdDraw.setPenColor(0,0,0);
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
        for(int i = 0; i < n; i++){
            ArrayList<Subr> subs = new ArrayList<Subr>();
            inputObject.nextLine(); //empty line
            String subname = inputObject.nextLine();
            String supname = inputObject.nextLine();
            int x = inputObject.nextInt();
            double[] xs = new double[x];
            double[] ys = new double[x];
            for(int y = 0; y < x; y++){
                xs[y] = inputObject.nextDouble();
                ys[y] = inputObject.nextDouble();
                inputObject.nextLine();
            }
            Subr s = new Subr(xs, ys, subname);
            if(regions.containsKey(subname)){
                regions.get(subname).add(s);
            }
            else{
                subs.add(s);
                regions.put(subname, subs);
            }
        }
        inputObject.close();
        File iF = new File("input/"+region+year+".txt");
        Scanner iO = new Scanner(iF);
        iO.nextLine(); //gets rid of first line thats useless
        for(int d = 0; d < regions.size(); d++){
            String line = iO.nextLine();
            String[] vd = line.split(","); //array of everything on a line of voting data
            int[] vs = new int[3]; //gonna fill this with the votes for each party
            vs[0] = Integer.parseInt(vd[1]);
            vs[1] = Integer.parseInt(vd[2]);
            vs[2] = Integer.parseInt(vd[3]);
            regions.get(vd[0]).get(0).addVotes(vs);
        }
        for(String key : regions.keySet()){
            for(Subr r : regions.get(key)){
                StdDraw.setPenColor(regions.get(key).get(0).getColor());
                StdDraw.filledPolygon(r.getxs(), r.getys());
            }
        }
        StdDraw.show();
    }
}
