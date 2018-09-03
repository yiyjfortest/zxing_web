import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class DecodeTest {

    public static void main(String[] args) throws Exception {

        // 这是二维码图片
//        BufferedImage bi = ImageIO.read(new FileInputStream(new File("D:" + File.separator + "qr_code.png")));

        // 这是条形码图片
         BufferedImage bi = ImageIO.read(new FileInputStream(new File("/Users/yijea/Desktop/" + "rtc.png")));

        if (bi != null) {
            Map<DecodeHintType, String> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            LuminanceSource source = new BufferedImageLuminanceSource(bi);
            // 这里还可以是
            //BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
            Result res = new MultiFormatReader().decode(bitmap, hints);
            System.out.println(res);
        }
    }
}
