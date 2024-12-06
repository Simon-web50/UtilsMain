package Compress;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.*;

public class TarGz {
    public static void main(String[] args) {
        // 要压缩的文件或文件夹
        String sourceFile = "F:\\压缩测试";
        // 压缩后的tar.gz文件名
        String tarGzFileName = "output.tar.gz";
        try {
            FileOutputStream fos = new FileOutputStream(tarGzFileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            GzipCompressorOutputStream gzcos = new GzipCompressorOutputStream(bos);
            TarArchiveOutputStream tarArchive = new TarArchiveOutputStream(gzcos);

            File file = new File(sourceFile);
            addToTarArchive(file, tarArchive);
            tarArchive.finish();
            tarArchive.close();
            System.out.println("文件已成功打包成:" + tarGzFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addToTarArchive(File file, TarArchiveOutputStream tarArchive) throws IOException {
        String entryName = file.getName();
        TarArchiveEntry tarEntry = new TarArchiveEntry(file, entryName);

        tarArchive.putArchiveEntry(tarEntry);

        if (file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    tarArchive.write(buffer, 0, len);
                }
                tarArchive.closeArchiveEntry();
            }
        } else if (file.isDirectory()) {
            tarArchive.closeArchiveEntry();
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children){
                    addToTarArchive(child, tarArchive);
                }
            }
        }
    }
}
