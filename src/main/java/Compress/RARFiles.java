package Compress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RARFiles {
    public static void main(String[] args) {
        // 要压缩的文件或文件夹
        String sourceFile = "F:\\压缩测试";
        // 压缩后的rar文件名
        String rarFileName = "test.rar";

        try {
            // 构建命令行
            String[] command = {"cmd", "/c", "rar", "a", "-r", rarFileName, sourceFile};

            // 创建进程并执行命令
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process= processBuilder.start();

            // 读取进程输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待进程执行完毕
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("文件已成功打包:" + rarFileName);
            } else {
                System.out.println("打包失败");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
