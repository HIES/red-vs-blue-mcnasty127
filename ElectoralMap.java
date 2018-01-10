import java.lang.Exception;
import java.io.File;
import java.util.Scanner;
import static java.lang.System.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.ArrayList;
public class ElectoralMap
{
   static HashMap<String, HashMap<String, ArrayList<Subr>>> regions = new HashMap<>();
   static double xmin;
   static double ymin;
   static double xmax;
   static double ymax;
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
        public String getName(){
            return name;
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
                color = Color.GREEN;
            }
        }
        public Color getColor(){
            return color;
        }
    }
   public static void geoData(String region) throws Exception{
        File inputFile = new File("input/"+region+".txt");
        Scanner inputObject = new Scanner(inputFile);
        xmin = inputObject.nextDouble();
        ymin = inputObject.nextDouble();
        inputObject.nextLine();
        xmax = inputObject.nextDouble();
        ymax = inputObject.nextDouble();
        inputObject.nextLine();
        int n = inputObject.nextInt();
        inputObject.nextLine();
        for(int i = 0; i < n; i++){
            ArrayList<Subr> subs = new ArrayList<Subr>();
            inputObject.nextLine(); //empty line
            String subname = inputObject.nextLine();
            String supname = inputObject.nextLine();
            HashMap<String, ArrayList<Subr>> inner = new HashMap<>();
            if(!regions.containsKey(supname)){
                regions.put(supname, inner);
            }
            else{}
            int x = inputObject.nextInt();
            double[] xs = new double[x];
            double[] ys = new double[x];
            for(int y = 0; y < x; y++){
                xs[y] = inputObject.nextDouble();
                ys[y] = inputObject.nextDouble();
                inputObject.nextLine();
            }
            Subr s = new Subr(xs, ys, subname);
            if(regions.get(supname).containsKey(subname)){
                regions.get(supname).get(subname).add(s);
            }
            else{
                subs.add(s);
                regions.get(supname).put(subname,subs);
            }
        }
        inputObject.close();
   }
   public static void votingData(String region, int year)throws Exception{
    File iF = new File("input/"+region+year+".txt");
    Scanner iO = new Scanner(iF);
    iO.nextLine();
    while(iO.hasNextLine()){
            String line = iO.nextLine();
            String[] vd = line.split(","); //array of everything on a line of voting data
            int[] vs = new int[3]; //gonna fill this with the votes for each party
            vs[0] = Integer.parseInt(vd[1]);
            vs[1] = Integer.parseInt(vd[2]);
            vs[2] = Integer.parseInt(vd[3]);
            boolean flag;
            for(String supkey : regions.keySet()){
                for(String subkey : regions.get(supkey).keySet()){
                    if (subkey.equals(vd[0])){
                        flag = true;
                    }
                    else{
                        flag = false;
                    }
                    if(!regions.get(supkey).containsKey(vd[0]) || flag){}
                    else{
                        for(Subr c : regions.get(supkey).get(vd[0])){
                            c.addVotes(vs);
                        }
                    }
                }
            }
    }
    iO.close();
   }
   public static void visualize(){
       StdDraw.setCanvasSize((((int)xmax-(int)xmin)*512)/((int)ymax-(int)ymin),512);
       StdDraw.setXscale(xmin,xmax);
       StdDraw.setYscale(ymin,ymax);
       StdDraw.enableDoubleBuffering();
       StdDraw.show();
       for(String supkey : regions.keySet()){
           HashMap<String, ArrayList<Subr>> inner = regions.get(supkey);
           for(String subkey : inner.keySet()){
               for(Subr r : inner.get(subkey)){
                   try{
                       StdDraw.setPenColor(r.getColor());
                       StdDraw.filledPolygon(r.getxs(), r.getys());
                    }
                    catch(Exception e){}
                }
            }
            StdDraw.show();
        }
    }
}
