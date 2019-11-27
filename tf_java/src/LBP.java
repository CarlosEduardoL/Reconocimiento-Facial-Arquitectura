import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

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
        lbpImage = new short[width-2][height-2];

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



    public static void main(String[] args) {
        try(FileWriter fw = new FileWriter("data.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println("replica,tamanho,algoritmo,localidad,tiempo");
            for (int replica = 0; replica < 105; replica++) {
                for (int nivel = 1; nivel < 5; nivel++) {
                    String run_str = replica + "," + nivel;
                    LBP lbp = new LBP("images/level"+nivel+".png");
                    lbp.LBPImage_8_1_local();

                    long t = System.nanoTime();
                    lbp.LBPImage_8_1_local();
                    out.println(run_str + ",8_1,si," + (System.nanoTime() - t) );
                    
                    t = System.nanoTime();
                    lbp.LBPImage_8_1_noLocal();
                    out.println(run_str + ",8_2,si," + (System.nanoTime() - t) );
                    
                    t = System.nanoTime();
                    lbp.LBPImage_16_2_local();
                    out.println(run_str + ",16_2,si," + (System.nanoTime() - t) );
                    

                    t = System.nanoTime();
                    lbp.LBPImage_8_1_noLocal();
                    out.println(run_str + ",8_1,no," + (System.nanoTime() - t) );
                    
                    t = System.nanoTime();
                    lbp.LBPImage_8_2_noLocal();
                    out.println(run_str + ",8_2,no," + (System.nanoTime() - t) );
                    
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
