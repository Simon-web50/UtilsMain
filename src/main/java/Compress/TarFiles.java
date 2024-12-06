package Compress;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TarFiles {
    public static void main(String[] args) {
        // 要打包的文件或文件夹
        String sourceFile = "F:\\压缩测试";
        // 打包后的tar文件名
        String tarFileName = "tar.tar";

        try {
            FileOutputStream fos = new FileOutputStream(tarFileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            TarArchiveOutputStream tarArchive = new TarArchiveOutputStream(bos);

            addToTarArchive(sourceFile, tarArchive);

            tarArchive.finish();
            tarArchive.close();
            System.out.println("文件已打包成:" + tarFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void addToTarArchive(String filePath, TarArchiveOutputStream tarArchive) throws IOException {
        Path sourcePath = Paths.get(filePath);
        String baseDir = sourcePath.getFileName().toString();

        Files.walk(sourcePath)
                .filter(path -> !Files.isDirectory(path))
                .forEach(path -> {
                    try {
                        String entryName = baseDir + File.separator + sourcePath.relativize(path).toString();
                        TarArchiveEntry tarEntry = new TarArchiveEntry(path.toFile(), entryName);
                        tarArchive.putArchiveEntry(tarEntry);

                        FileInputStream fis = new FileInputStream(path.toFile());
                        IOUtils.copy(fis, tarArchive);
                        fis.close();

                        tarArchive.closeArchiveEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
