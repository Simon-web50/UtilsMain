package UnCompress;

import java.io.*;
import java.util.zip.GZIPInputStream;

public class Gunzipper {
    public static void gunzip(String gzipFilePath, String destFilePath) throws IOException {
        try (GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(gzipFilePath));
             FileOutputStream out = new FileOutputStream(destFilePath)) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        }
    }
}