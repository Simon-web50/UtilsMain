package UnCompress;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
// ????
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

public class Untar {
    public static void untar(String tarFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        try (InputStream fis = new FileInputStream(tarFilePath);
             TarArchiveInputStream tais = new TarArchiveInputStream(fis)) {

            TarArchiveEntry entry = tais.getNextTarEntry();
            while (entry != null) {
                File currentFile = new File(destDir, entry.getName());

                if (entry.isDirectory()) {
                    currentFile.mkdirs();
                } else {
                    currentFile.getParentFile().mkdirs();

                    try (OutputStream fos = new FileOutputStream(currentFile)) {
                        IOUtils.copy(tais, fos);
                    }
                }

                entry = tais.getNextTarEntry();
            }
        }
    }
}
