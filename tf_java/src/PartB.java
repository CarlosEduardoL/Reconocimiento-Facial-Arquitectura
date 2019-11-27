public class PartB {

    public static double chiSquare(short[] x, short[] y){
        double chi = 0;
        for (int i = 0; i < x.length; i++) {
           chi += Math.pow(x[i]-y[i],2)/((double)x[i]+y[i]);
        }
        return chi;
    }

    public static void main(String[] args) {

    }

}
