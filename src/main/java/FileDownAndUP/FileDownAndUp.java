package FileDownAndUP;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

public class FileDownAndUp {
    public static void main(String[] args) {
        String sourceHost = "10.163.7.50";
        String destHost = "10.163.7.51";
        String user = "administrator";
        String sourcePassword = "h3c.com.cn50";
        String deskPassword = "h3c.com.cn51";
        String sourceDir = "D:/mrd";
        String destDir = "D:/mrd";

        Session sourceSession = null;
        Session destSession = null;
        Channel sourceChannel = null;
        Channel destChannel = null;

        try {
            // Set up source Session
            JSch jSch = new JSch();
            sourceSession = jSch.getSession(user, sourceHost, 22);
            sourceSession.setPassword(sourcePassword);
            sourceSession.setConfig("StrictHostKeyChecking", "no");
            sourceSession.connect();
            // Set up destination session
            destSession = jSch.getSession(user, destHost, 22);
            destSession.setPassword(deskPassword);
            destSession.setConfig("StrictHostKeyChecking", "no");
            destSession.connect();

            // Open SFTP channels
            sourceChannel = sourceSession.openChannel("sftp");
            sourceChannel.connect();

            destChannel = destSession.openChannel("sftp");
            destChannel.connect();

            // List files in source directory
            Vector<ChannelSftp.LsEntry> files = ((ChannelSftp) sourceChannel).ls(sourceDir);
            for (ChannelSftp.LsEntry file : files) {
                if (!file.getFilename().equals(".") && !file.getFilename().equals("..")) {
                    String sourceFile = sourceDir + file.getFilename();
                    String destFile = destDir + file.getFilename();

                    if (file.getAttrs().isDir()) {
                        // Recursively copy directories
                        copyDirectory((ChannelSftp) sourceChannel, (ChannelSftp) destChannel, sourceFile, destFile);
                    } else {
                        // Copy files
                        copyFile((ChannelSftp) sourceChannel, (ChannelSftp) destChannel, sourceFile, destFile);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sourceChannel != null && sourceChannel.isConnected()) {
                sourceChannel.disconnect();
            }
            if (destChannel != null && destChannel.isConnected()) {
                destChannel.disconnect();
            }
            if (sourceSession != null && sourceSession.isConnected()) {
                sourceSession.disconnect();
            }
            if (destSession != null && destSession.isConnected()) {
                destSession.disconnect();
            }
        }
    }
    private static void copyFile(ChannelSftp sourceChannel, ChannelSftp destChannel, String sourceFile, String destFile) throws SftpException, IOException {
        try (InputStream in = sourceChannel.get(sourceFile);
             OutputStream out = destChannel.put(destFile)){
            byte[] buffer = new byte[1024];
            int readCount;
            while ((readCount = in.read(buffer)) > 0) {
                out.write(buffer, 0, readCount);
            }
        }
    }
    private static void copyDirectory(ChannelSftp sourceChannel, ChannelSftp destChannel, String sourceDir, String destDir) throws SftpException {
        try {
            destChannel.mkdir(destDir);
            Vector<ChannelSftp.LsEntry> files = sourceChannel.ls(sourceDir);

            for (ChannelSftp.LsEntry file : files) {
                if (!file.getFilename().equals(".") && !file.getFilename().equals("..")) {
                    String sourceFile = sourceDir + "/" + file.getFilename();
                    String destFile = destDir + "/" + file.getFilename();

                    if (file.getAttrs().isDir()) {
                        copyDirectory(sourceChannel, destChannel, sourceFile, destFile);
                    } else {
                        copyFile(sourceChannel, destChannel, sourceFile, destFile);
                    }
                }
            }
        } catch (Exception e) {
            throw new SftpException(ChannelSftp.SSH_FX_FAILURE, "Failed to copy directory: " + e.getMessage());
        }
    }
}
