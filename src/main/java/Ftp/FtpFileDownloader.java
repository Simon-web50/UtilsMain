package Ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FtpFileDownloader {

    private static final String SERVER = "ftp.example.com";
    private static final int PORT = 21;
    private static final String USER = "username";
    private static final String PASS = "password";
    private static final String REMOTE_DIR = "/path/to/remote/directory";
    private static final String LOCAL_DIR = "/path/to/local/directory";

    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(SERVER, PORT);
            ftpClient.login(USER, PASS);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            // 切换到远程目录  
            ftpClient.changeWorkingDirectory(REMOTE_DIR);

            // 遍历远程目录  
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile file : files) {
                String remoteFileName = fixFileNameEncoding(file.getName()); // 处理乱码（如果需要）  
                String localFilePath = Paths.get(LOCAL_DIR, remoteFileName).toString();

                // 如果是文件并且不在本地存在，则下载  
                if (file.isFile() && !Files.exists(Paths.get(localFilePath))) {
                    System.out.println("Downloading: " + remoteFileName);
                    OutputStream outputStream = new FileOutputStream(localFilePath);
                    boolean success = ftpClient.retrieveFile(remoteFileName, outputStream);
                    outputStream.close();

                    if (success) {
                        System.out.println("Downloaded successfully: " + remoteFileName);
                    } else {
                        System.out.println("Failed to download: " + remoteFileName);
                    }
                }

                // 如果是文件夹，则递归处理  
                if (file.isDirectory()) {
                    String subdir = file.getName();
                    String localSubdirPath = Paths.get(LOCAL_DIR, subdir).toString();
                    // 创建本地文件夹（如果需要）  
                    File localSubdir = new File(localSubdirPath);
                    if (!localSubdir.exists()) {
                        localSubdir.mkdirs();
                    }
                    // 递归处理子文件夹  
                    downloadFtpDirectory(ftpClient, file.getName(), localSubdirPath);
                }
            }

            ftpClient.logout();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // 处理文件名乱码的函数（这里只是示例，你需要根据你的情况来实现）  
    private static String fixFileNameEncoding(String fileName) {
        // 假设FTP服务器使用GBK编码，我们需要转换为UTF-8  
        return new String(fileName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    // 递归下载FTP文件夹的函数  
    private static void downloadFtpDirectory(FTPClient ftpClient, String remoteDirPath, String localDirPath) throws IOException {
        ftpClient.changeWorkingDirectory(remoteDirPath);
        FTPFile[] files = ftpClient.listFiles();
        for (FTPFile file : files) {
            String remoteFileName = fixFileNameEncoding(file.getName());
            String localFilePath = Paths.get(localDirPath, remoteFileName).toString();

            if (file.isFile()) {
                // ... 类似于上面的文件下载逻辑  
            } else if (file.isDirectory()) {
                // 递归处理子文件夹  
                String subdir = file.getName();
                String localSubdirPath = Paths.get(localDirPath, subdir).toString();
                // ... 类似于上面的文件夹处理逻辑  
                downloadFtpDirectory(ftpClient, subdir, localSubdirPath);
            }
        }
    }
}