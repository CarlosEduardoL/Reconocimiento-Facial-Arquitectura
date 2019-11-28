import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class PartB {

    public static void main(String[] args) {
        Model[] nelson = getModels("Nelson", "data_set/0/");
        Model[] carlos = getModels("Carlos", "data_set/1/");
        Model[] santiago = getModels("Santiago", "data_set/2/");
        Model[] nicolas = getModels("Nicolas", "data_set/3/");
        Detector detector = new Detector();
        detector.addModel(nelson);
        detector.addModel(carlos);
        detector.addModel(santiago);
        detector.addModel(nicolas);
        try(FileWriter fw = new FileWriter("funcionamiento.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println("Tipo de algoritmo,Grupo,Sub grupo,Grupo Respuesta,Sub grupo respuesta,distancia chi");
            Model[] samples = new Model[]{new Model("Nelson","test_set/0.jpeg",0),
                                            new Model("Carlos","test_set/1.jpg",0),
                                            new Model("Santiago","test_set/2.jpeg",0),
                                            new Model("Nicolas","test_set/3.jpg",0) };
            for (Model face: samples) {
                String answer = detector.DetectLBP8_1(face);
                out.println("8_1,"+face.getTag()+","+face.getSubTag()+","+answer);

                answer = detector.DetectLBP8_2(face);
                out.println("8_2,"+face.getTag()+","+face.getSubTag()+","+answer);

                answer = detector.DetectLBP16_2(face);
                out.println("16_2,"+face.getTag()+","+face.getSubTag()+","+answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Model[] getModels(String name, String route) {
        return Stream.of(0, 1, 2, 3).map(i -> new Model(name, route + i + (name.equals("Nelson")?".jpeg":".jpg"),i)).toArray(Model[]::new);
    }

}

class Detector {

    private List<Model> models;

    Detector(){
        models = new ArrayList<>();
    }

    void addModel(Model... models){
        this.models.addAll(Arrays.asList(models));
        Collections.shuffle(this.models);
    }

    String DetectLBP8_1(Model face){
        double[] pesos = new double[models.size()];
        double[] vectorFace = face.getHist8_1();
        int min = 0;
        
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = chiSquare(models.get(i).getHist8_1(),vectorFace);
            if (pesos[i] < pesos[min]) min = i;
        }
        return models.get(min).getTag()+","+models.get(min).getSubTag()+","+pesos[min];
    }

    String DetectLBP8_2(Model face){
        double[] pesos = new double[models.size()];
        double[] vectorFace = face.getHist8_2();
        int min = 0;
        
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = chiSquare(models.get(i).getHist8_2(),vectorFace);
            if (pesos[i] < pesos[min]) min = i;
        }
        return models.get(min).getTag()+","+models.get(min).getSubTag()+","+pesos[min];
    }

    String DetectLBP16_2(Model face){
        double[] pesos = new double[models.size()];
        double[] vectorFace = face.getHist16_2();
        int min = 0;
        
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = chiSquare(models.get(i).getHist16_2(),vectorFace);
            if (pesos[i] < pesos[min]) min = i;
            
        }
        return models.get(min).getTag()+","+models.get(min).getSubTag()+","+pesos[min];
    }

    private double chiSquare(double[] x, double[] y){
        double chi = 0;
        for (int i = 0; i < x.length; i++) {
            if (x[i] + y[i] != 0)
                chi += ((x[i]-y[i])*(x[i]-y[i]))/(x[i] + y[i]);
        }
        return chi;
    }

}

class Model{

    private String tag;
    private int subTag;
    private LBP lbp;
    private double[] hist8_1;
    private double[] hist8_2;
    private double[] hist16_2;

    Model(String tag, String image, int subTag){
        this.tag = tag;
        this.subTag = subTag;
        try {
            lbp = new LBP(image);
            hist8_1 = LBP.lbpHistogram8_1y2(lbp.LBPImage_8_1_local());
            hist8_2 = LBP.lbpHistogram8_1y2(lbp.LBPImage_8_2_local());
            hist16_2 = LBP.lbpHistogram16_2(lbp.LBPImage_16_2_local());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    LBP getLbp() {
        return lbp;
    }

    public void setLbp(LBP lbp) {
        this.lbp = lbp;
    }

    public double[] getHist8_1() {
        return hist8_1;
    }

    public double[] getHist8_2() {
        return hist8_2;
    }

    public double[] getHist16_2() {
        return hist16_2;
    }

    public int getSubTag() {
        return subTag;
    }
}
