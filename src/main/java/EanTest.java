import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class EanTest {

    public static void main(String[] args) throws WriterException, IOException {

        EanTest test = new EanTest();
        test.ean_code();
    }

    private void ean_code() throws WriterException, IOException {
        int width = 200;
        int height = 100;
        String s = "010110111111";
        String format = "png";
        HashMap<EncodeHintType, String> hashMap = new HashMap<>();
        hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 条形码的格式是 BarcodeFormat.EAN_13
        BitMatrix bitMatrix = new MultiFormatWriter().encode(s, BarcodeFormat.EAN_13, width, height, hashMap);
        // 生成条形码图片
        File out = new File("/Users/yijea/Desktop/" + "ean.png");
        WriteBitMatricToFile.writeBitMatricToFile(bitMatrix, format, out);
    }


}
