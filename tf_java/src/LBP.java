import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LBP {

    private short[][] image;
    private short[][] lbpImage;
    private int width;
    private int height;

    public LBP(String image_route) throws IOException {

        File f = new File(image_route);
        BufferedImage bImage = ImageIO.read(f);
        width = bImage.getWidth();
        height = bImage.getHeight();
        image = new short[width][height];
        lbpImage = new short[width-4][height-4];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int p = bImage.getRGB(i, j);
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                short avg = (short) (r*0.2989+g*0.5870+b*0.1140);
                image[i][j] = avg;
            }
        }
    }

    public short[][] LBPImage_8_1_local(){
        for (int i = 2; i < width-2; i++) {
            for (int j = 2; j < height-2; j++) {
                short mask = 0;
                short pixel = image[i][j];
                mask |= (image[i - 1][ j - 1] < pixel? 1 : 0) << 7;
                mask |= (image[i][j - 1] < pixel? 1 : 0) << 6;
                mask |= (image[i + 1][ j - 1] < pixel? 1 : 0) << 5;
                mask |= (image[i + 1][ j] < pixel? 1 : 0) << 4;
                mask |= (image[i + 1][ j + 1] < pixel? 1 : 0) << 3;
                mask |= (image[i][ j + 1] < pixel? 1 : 0) << 2;
                mask |= (image[i - 1][ j + 1] < pixel? 1 : 0) << 1;
                mask |= (image[i - 1][j] < pixel ? 1 : 0);
                lbpImage[i - 2][ j - 2] = mask;
            }
        }
        return lbpImage;
    }

    public short[][] LBPImage_8_1_noLocal(){
        for (int j = 2; j < height-2; j++) {
            for (int i = 2; i < width-2; i++) {
                short mask = 0;
                short pixel = image[i][j];
                mask |= (image[i - 1][ j - 1] < pixel? 1 : 0) << 7;
                mask |= (image[i][j - 1] < pixel? 1 : 0) << 6;
                mask |= (image[i + 1][ j - 1] < pixel? 1 : 0) << 5;
                mask |= (image[i + 1][ j] < pixel? 1 : 0) << 4;
                mask |= (image[i + 1][ j + 1] < pixel? 1 : 0) << 3;
                mask |= (image[i][ j + 1] < pixel? 1 : 0) << 2;
                mask |= (image[i - 1][ j + 1] < pixel? 1 : 0) << 1;
                mask |= (image[i - 1][j] < pixel ? 1 : 0);
                lbpImage[i - 2][ j - 2] = mask;
            }
        }
        return lbpImage;
    }

    public short[][] LBPImage_8_2_local(){
        for (int i = 2; i < width-2; i++) {
            for (int j = 2; j < height-2; j++) {
                short mask = 0;
                short pixel = image[i][j];
                mask |= (image[i - 2][ j - 2] < pixel? 1 : 0) << 7;
                mask |= (image[i][j - 2] < pixel? 1 : 0) << 6;
                mask |= (image[i + 2][ j - 2] < pixel? 1 : 0) << 5;
                mask |= (image[i + 1][ j] < pixel? 1 : 0) << 4;
                mask |= (image[i + 2][ j + 2] < pixel? 1 : 0) << 3;
                mask |= (image[i][ j + 2] < pixel? 1 : 0) << 2;
                mask |= (image[i - 2][ j + 2] < pixel? 1 : 0) << 1;
                mask |= (image[i - 2][j] < pixel ? 1 : 0);
                lbpImage[i - 2][ j - 2] = mask;
            }
        }
        return lbpImage;
    }

    public short[][] LBPImage_8_2_noLocal(){
        for (int j = 2; j < height-2; j++) {
            for (int i = 2; i < width-2; i++) {
                short mask = 0;
                short pixel = image[i][j];
                mask |= (image[i - 2][ j - 2] < pixel? 1 : 0) << 7;
                mask |= (image[i][j - 2] < pixel? 1 : 0) << 6;
                mask |= (image[i + 2][ j - 2] < pixel? 1 : 0) << 5;
                mask |= (image[i + 2][j] < pixel? 1 : 0) << 4;
                mask |= (image[i + 2][ j + 2] < pixel? 1 : 0) << 3;
                mask |= (image[i][ j + 2] < pixel? 1 : 0) << 2;
                mask |= (image[i - 2][j + 2] < pixel? 1 : 0) << 1;
                mask |= (image[i - 2][j] < pixel ? 1 : 0);
                lbpImage[i - 2][ j - 2] = mask;
            }
        }
        return lbpImage;
    }

    public short[][] LBPImage_16_2_local(){
        for (int i = 2; i < width-2; i++) {
            for (int j = 2; j < height-2; j++) {
                short mask = 0;
                short pixel = image[i][j];
                mask |= (image[i - 2][ j - 1] < pixel? 1 : 0) << 15;
                mask |= (image[i - 2][ j - 2] < pixel? 1 : 0) << 14;
                mask |= (image[i - 1][ j - 2] < pixel? 1 : 0) << 13;
                mask |= (image[i][ j - 2] < pixel? 1 : 0) << 12;
                mask |= (image[i + 1][ j - 2] < pixel? 1 : 0) << 11;
                mask |= (image[i + 2][ j - 2] < pixel? 1 : 0) << 10;
                mask |= (image[i + 2][ j - 1] < pixel? 1 : 0) << 9;
                mask |= (image[i + 2][ j] < pixel? 1 : 0) << 8;
                mask |= (image[i + 2][ j + 1] < pixel? 1 : 0) << 7;
                mask |= (image[i + 2][ j + 2] < pixel? 1 : 0) << 6;
                mask |= (image[i + 1][ j + 2] < pixel? 1 : 0) << 5;
                mask |= (image[i][ j + 2] < pixel? 1 : 0) << 4;
                mask |= (image[i - 1][ j + 2] < pixel? 1 : 0) << 3;
                mask |= (image[i - 2][ j + 2] < pixel? 1 : 0) << 2;
                mask |= (image[i - 2][ j + 1] < pixel? 1 : 0) << 1;
                mask |= (image[i - 2][ j] < pixel)? 1 : 0;
                lbpImage[i - 2][ j - 2] = mask;
            }
        }
        return lbpImage;
    }

    public short[][] LBPImage_16_2_noLocal(){
        for (int j = 2; j < height-2; j++) {
            for (int i = 2; i < width-2; i++) {
                short mask = 0;
                short pixel = image[i][j];
                mask |= (image[i - 2][ j - 1] < pixel? 1 : 0) << 15;
                mask |= (image[i - 2][ j - 2] < pixel? 1 : 0) << 14;
                mask |= (image[i - 1][ j - 2] < pixel? 1 : 0) << 13;
                mask |= (image[i][ j - 2] < pixel? 1 : 0) << 12;
                mask |= (image[i + 1][ j - 2] < pixel? 1 : 0) << 11;
                mask |= (image[i + 2][ j - 2] < pixel? 1 : 0) << 10;
                mask |= (image[i + 2][ j - 1] < pixel? 1 : 0) << 9;
                mask |= (image[i + 2][ j] < pixel? 1 : 0) << 8;
                mask |= (image[i + 2][ j + 1] < pixel? 1 : 0) << 7;
                mask |= (image[i + 2][ j + 2] < pixel? 1 : 0) << 6;
                mask |= (image[i + 1][ j + 2] < pixel? 1 : 0) << 5;
                mask |= (image[i][ j + 2] < pixel? 1 : 0) << 4;
                mask |= (image[i - 1][ j + 2] < pixel? 1 : 0) << 3;
                mask |= (image[i - 2][ j + 2] < pixel? 1 : 0) << 2;
                mask |= (image[i - 2][ j + 1] < pixel? 1 : 0) << 1;
                mask |= (image[i - 2][ j] < pixel)? 1 : 0;
                lbpImage[i - 2][ j - 2] = mask;
            }
        }
        return lbpImage;
    }

    public static double[] lbpHistogram8_1y2(short[][] lbpImage){
        double[] lbpHistogram = new double[256 * 16];
        int width = lbpImage.length/4;
        int height = lbpImage[0].length/4;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                lbpHistogram[lbpImage[i][j]]++;
                lbpHistogram[256 + lbpImage[i + width][j]]++;
                lbpHistogram[256 * 2 + lbpImage[i + 2 * width][j]]++;
                lbpHistogram[256 * 3 + lbpImage[i + 3 * width][j]]++;

                lbpHistogram[256 * 4 + lbpImage[i][j + height]]++;
                lbpHistogram[256 * 5 + lbpImage[i + width][j + height]]++;
                lbpHistogram[256 * 6 + lbpImage[i + 2 * width][j + height]]++;
                lbpHistogram[256 * 7 + lbpImage[i + 3 * width][j + height]]++;

                lbpHistogram[256 * 8 + lbpImage[i][j + 2 * height]]++;
                lbpHistogram[256 * 9 + lbpImage[i + width][j + 2 * height]]++;
                lbpHistogram[256 * 10 + lbpImage[i + 2 * width][j + 2 * height]]++;
                lbpHistogram[256 * 11 + lbpImage[i + 3 * width][j + 2 * height]]++;
                
                lbpHistogram[256 * 12 + lbpImage[i][j + 3 * height]]++;
                lbpHistogram[256 * 13 + lbpImage[i + width][j + 3 * height]]++;
                lbpHistogram[256 * 14 + lbpImage[i + 2 * width][j + 3 * height]]++;
                lbpHistogram[256 * 15 + lbpImage[i + 3 * width][j + 3 * height]]++;
            }
        }
        return Arrays.stream(lbpHistogram).map(i -> i/(width * height)).toArray();
    }

    public static double[] lbpHistogram16_2(short[][] lbpImage){
        int max = (1<<16);
        double[] lbpHistogram = new double[max * 16];
        int width = lbpImage.length/4;
        int height = lbpImage[0].length/4;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                lbpHistogram[(int)(char)lbpImage[i][j]]++;
                lbpHistogram[max + (int)(char)lbpImage[i + width][j]]++;
                lbpHistogram[max * 2 + (int)(char)lbpImage[i + 2 * width][j]]++;
                lbpHistogram[max * 3 + (int)(char)lbpImage[i + 3 * width][j]]++;

                lbpHistogram[max * 4 + (int)(char)lbpImage[i][j + height]]++;
                lbpHistogram[max * 5 + (int)(char)lbpImage[i + width][j + height]]++;
                lbpHistogram[max * 6 + (int)(char)lbpImage[i + 2 * width][j + height]]++;
                lbpHistogram[max * 7 + (int)(char)lbpImage[i + 3 * width][j + height]]++;

                lbpHistogram[max * 8 + (int)(char)lbpImage[i][j + 2 * height]]++;
                lbpHistogram[max * 9 + (int)(char)lbpImage[i + width][j + 2 * height]]++;
                lbpHistogram[max * 10 + (int)(char)lbpImage[i + 2 * width][j + 2 * height]]++;
                lbpHistogram[max * 11 + (int)(char)lbpImage[i + 3 * width][j + 2 * height]]++;

                lbpHistogram[max * 12 + (int)(char)lbpImage[i][j + 3 * height]]++;
                lbpHistogram[max * 13 + (int)(char)lbpImage[i + width][j + 3 * height]]++;
                lbpHistogram[max * 14 + (int)(char)lbpImage[i + 2 * width][j + 3 * height]]++;
                lbpHistogram[max * 15 + (int)(char)lbpImage[i + 3 * width][j + 3 * height]]++;
            }
        }
        return Arrays.stream(lbpHistogram).map(i -> i/(width * height)).toArray();
    }

    public static void main(String[] args) {
        try(FileWriter fw = new FileWriter("data.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println("replica,tamanho,algoritmo,localidad,tiempo");
            ArrayList<Integer> levels = new ArrayList<>();
            levels.add(0);levels.add(1);levels.add(2);levels.add(3);levels.add(4);
            for (int replica = 0; replica <= 100; replica++) {
                Collections.shuffle(levels);
                for (int nivel: levels) {
                    String run_str = replica + "," + nivel;

                    LBP lbp = new LBP("images/level"+nivel+".png");
                    long t = System.nanoTime();
                    lbp.LBPImage_8_1_local();
                    out.println(run_str + ",8_1,si," + (System.nanoTime() - t) );

                    lbp = new LBP("images/level"+nivel+".png");
                    t = System.nanoTime();
                    lbp.LBPImage_8_2_local();
                    out.println(run_str + ",8_2,si," + (System.nanoTime() - t) );

                    lbp = new LBP("images/level"+nivel+".png");
                    t = System.nanoTime();
                    lbp.LBPImage_16_2_local();
                    out.println(run_str + ",16_2,si," + (System.nanoTime() - t) );

                    lbp = new LBP("images/level"+nivel+".png");
                    t = System.nanoTime();
                    lbp.LBPImage_8_1_noLocal();
                    out.println(run_str + ",8_1,no," + (System.nanoTime() - t) );

                    lbp = new LBP("images/level"+nivel+".png");
                    t = System.nanoTime();
                    lbp.LBPImage_8_2_noLocal();
                    out.println(run_str + ",8_2,no," + (System.nanoTime() - t) );

                    lbp = new LBP("images/level"+nivel+".png");
                    t = System.nanoTime();
                    lbp.LBPImage_16_2_noLocal();
                    out.println(run_str + ",16_2,no," + (System.nanoTime() - t));
                    
                }
                System.out.println("replica N° "+replica);
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

}
