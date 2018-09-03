import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class WriteBitMatricToFile {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static BufferedImage toBufferedImage(BitMatrix bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, bm.get(i, j) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeBitMatricToFile(BitMatrix bm, String format,
                                            File file) throws IOException {
        BufferedImage image = toBufferedImage(bm);
        // 设置logo图标 二维码中间logo设置
        // 注意条形码一般中间是不会有图片的、若生成条形码 、下面2行需注释掉
//        LogoConfig logoConfig = new LogoConfig();
//        image = logoConfig.LogoMatrix(image);
        try {
            if (!ImageIO.write(image, format, file)) {
                throw new RuntimeException("Can not write an image to file"
                        + file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        // 设置logo图标 二维码中间logo设置
        // 注意条形码一般中间是不会有图片的、若生成条形码 、下面2行需注释掉
        // LogoConfig logoConfig = new LogoConfig();
        // image = logoConfig.LogoMatrix(image);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format "
                    + format);
        }
    }



}
