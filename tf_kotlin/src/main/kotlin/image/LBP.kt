package image

import java.io.File
import javax.imageio.ImageIO
import kotlin.math.roundToInt


class LBP(private val image_route: String) {

    private val image: Array<IntArray>
    private val lbpImage: Array<IntArray>

    init {
        var f = File(image_route)
        val bImage = ImageIO.read(f)
        val width = bImage.width
        val height = bImage.height
        image = Array(width){ IntArray(height) }
        lbpImage = Array(width){ IntArray(height) }


        for (i in 0 until width) {
            for (j in 0 until height) {
                val p = bImage.getRGB(i, j)
                val r = p shr 16 and 0xff
                val g = p shr 8 and 0xff
                val b = p and 0xff
                val avg = (r.toDouble()*0.2989+g.toDouble()*0.5870+b.toDouble()*0.1140).roundToInt()
                image[i][j] = avg
                bImage.setRGB(i,j,(avg shl 16) or (avg shl 8) or avg)
            }
        }
        f = File("Output.jpg")
        ImageIO.write(bImage, "jpg", f)

        for (i in 1 until width-1) {
            for (j in 1 until height-1) {
                val temp = slice3(image,i,j)
                val avg = evaluate(temp[1][1],
                    intArrayOf(temp[0][1], temp[0][ 0], temp[1][ 0], temp[2][ 0],
                        temp[2][ 1],temp[2][2], temp[1][ 2], temp[0][2]))
                lbpImage[i][j] = avg
                bImage.setRGB(i,j,(255 shl 24) or (avg shl 16) or (avg shl 8) or avg)
            }
        }

        f = File("Output2.png")
        ImageIO.write(bImage, "png", f)

    }

    private fun slice3(param:Array<IntArray>, i: Int, j: Int):Array<IntArray>{
        val arr = Array(3){IntArray(3)}
        for (x in 0 until 3){
            for (y in 0 until 3){
                arr[x][y] = param[i+x-1][y+j-1]
            }
        }
        return arr
    }

    private fun evaluate(num: Int, neighbors: IntArray):Int{
        return neighbors.map { cp(it, num) }.mapIndexed { index, n -> n*(index shl (8-index)) }.sum()
    }

    private fun cp(num1:Int,num2:Int) = if (num2 < num1) 1 else 0

}

fun main() {
    LBP("src/main/resources/images/img1.jpg")
}