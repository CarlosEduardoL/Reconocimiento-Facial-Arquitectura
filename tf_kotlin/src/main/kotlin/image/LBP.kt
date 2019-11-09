package image

import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO


class LBP(val image_route: String) {

    val image: Array<IntArray>

    init {
        var f = File(image_route)
        val bImage = ImageIO.read(f)
        val width = bImage.width
        val height = bImage.height
        image = Array(width){ IntArray(height) }


        for (i in 0 until width) {
            for (j in 0 until height) {
                val p = bImage.getRGB(i, j)
                val r = p shr 16 and 0xff
                val g = p shr 8 and 0xff
                val b = p and 0xff
                val avg = (r.toDouble()*0.2989+g.toDouble()*0.5870+b.toDouble()*0.1140).toInt()
                image[i][j] = avg
                bImage.setRGB(i,j,(avg shl 16) or (avg shl 8) or avg)
            }
        }
        f = File("Output.jpg")
        ImageIO.write(bImage, "jpg", f)
    }

}

fun main() {
    LBP("src/main/resources/images/img1.jpg")
}