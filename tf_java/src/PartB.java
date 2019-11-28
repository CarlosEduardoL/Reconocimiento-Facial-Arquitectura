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
        List<Integer> index = Arrays.asList(0,1,2,3);
        try(FileWriter fw = new FileWriter("funcionamiento.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println("Replica,Tipo de algoritmo,Grupo,Respuesta,Acerto");
            for (int replica = 1; replica <= 10; replica++) {
                String r = replica+",";
                List<Model> examples = new ArrayList<>();
                Detector detector = new Detector();
                fillDetector(index,examples,detector,nelson,carlos,santiago,nicolas);
                for (Model face: examples) {
                    String answer = detector.DetectLBP8_1(face);
                    out.println(r + "8_1,"+face.getTag()+","+answer+","+face.getTag().equals(answer));

                    answer = detector.DetectLBP8_2(face);
                    out.println(r + "8_2,"+face.getTag()+","+answer+","+face.getTag().equals(answer));

                    answer = detector.DetectLBP16_2(face);
                    out.println(r + "16_2,"+face.getTag()+","+answer+","+face.getTag().equals(answer));
                }
                System.out.println("Replica #"+replica);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillDetector(List<Integer> index, List<Model> examples, Detector detector, Model[]... models_) {
        for (Model[] models : models_) {
            Collections.shuffle(index);
            examples.addAll(Arrays.asList(models[index.get(0)], models[index.get(1)]));
            detector.addModel(models[index.get(2)], models[index.get(3)]);
        }
    }

    private static Model[] getModels(String name, String route) {
        return Stream.of(0, 1, 2, 3).map(i -> new Model(name, route + i + (name.equals("Nelson")?".jpeg":".jpg"))).toArray(Model[]::new);
    }

}

class Detector {

    private List<Model> models;

    Detector(){
        models = new ArrayList<>();
    }

    void addModel(Model... models){
        this.models.addAll(Arrays.asList(models));
    }

    String DetectLBP8_1(Model face){
        double[] pesos = new double[models.size()];
        double[] vectorFace = LBP.lbpHistogram8_1y2(face.getLbp().LBPImage_8_1_local());
        int min = 0;
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = chiSquare(LBP.lbpHistogram8_1y2(models.get(i).getLbp().LBPImage_8_1_local()),vectorFace);
            if (pesos[i] < pesos[min]) min = i;
        }
        return models.get(min).getTag();
    }

    String DetectLBP8_2(Model face){
        double[] pesos = new double[models.size()];
        double[] vectorFace = LBP.lbpHistogram8_1y2(face.getLbp().LBPImage_8_2_local());
        int min = 0;
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = chiSquare(LBP.lbpHistogram8_1y2(models.get(i).getLbp().LBPImage_8_2_local()),vectorFace);
            if (pesos[i] < pesos[min]) min = i;
        }
        return models.get(min).getTag();
    }

    String DetectLBP16_2(Model face){
        double[] pesos = new double[models.size()];
        double[] vectorFace = LBP.lbpHistogram16_2(face.getLbp().LBPImage_16_2_local());
        int min = 0;
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] = chiSquare(LBP.lbpHistogram16_2(models.get(i).getLbp().LBPImage_16_2_local()),vectorFace);
            if (pesos[i] < pesos[min]) min = i;
        }
        return models.get(min).getTag();
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
    private LBP lbp;

    Model(String tag, String image){
        this.tag = tag;
        try {
            lbp = new LBP(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LBP getLbp() {
        return lbp;
    }

    public void setLbp(LBP lbp) {
        this.lbp = lbp;
    }
}
