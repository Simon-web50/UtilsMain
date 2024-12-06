package Compress;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 生成若干个txt并打包到zip中
 */
public class GenerateTxtFilesAndZip {
    public static void main(String[] args) {
        String basePath = "F:\\压缩测试"; // 更换为你想要保存文件的文件夹路径
        int fileCount = 10; // 要生成的文件数量

        try {
            // 创建文件夹,如果文件夹不存在
            File directory = new File(basePath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 生产txt文件并写入内容
            for (int i = 1; i < fileCount; i++) {
                String fileName = "file" + i + ".txt";
                String filePath = basePath + File.separator + fileName;
                String fileContent = "This is the content of" + fileName;

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    writer.write(fileContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 打包成zip文件
            String zipFileName = "output.zip";
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            File dir = new File(basePath);
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".txt")) {
                        FileInputStream fis = new FileInputStream(file);
                        zos.putNextEntry(new ZipEntry(file.getName()));

                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                        zos.closeEntry();
                        zos.close();
                    }
                }
            }
            zos.close();
            System.out.println("文件已成功打包:" + zipFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
