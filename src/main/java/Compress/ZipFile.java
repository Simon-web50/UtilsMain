package Compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {
    public static void main(String[] args) {
        // 要压缩的文件或文件夹
        String sourceFile = "F:\\压缩测试";
        // 压缩后的文件名
        String zipFileName = "zip.zip";
        // 创建一个输出流将数据写入ZIP文件
        try (FileOutputStream fos = new FileOutputStream(zipFileName);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            // 调用递归方法压缩文件或文件夹
            addToZipFile(sourceFile, sourceFile, zos);
            System.out.println("文件已成功打包成:" + zipFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void addToZipFile(String path, String sourceFile, ZipOutputStream zos) throws IOException {
        File file = new File(sourceFile);

        // 如果是文件夹,则获取其内容并递归调用此方法.
        if (file.isDirectory()) {
            String[] fileList = file.list();
            if (fileList != null) {
                for (String fileName : fileList) {
                    addToZipFile(path, sourceFile + File.separator + fileName, zos);
                }
            }
            return;
        }
        // 如果是文件则将其添加到ZIP文件中
        try (FileInputStream fis = new FileInputStream(sourceFile)) {
            String entryName = sourceFile.substring(path.length() + 1); // 获取ZIP中的条目名
            ZipEntry zipEntry = new ZipEntry(entryName);
            zos.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
        }
    }
}
