package com.ershouserver.ershou_server.Utils;

import com.jcraft.jsch.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.net.ftp.FtpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author：xsr
 * @name：FtpTransformFile
 * sftp上传文件方法类封装
 * @Date：2023/4/30 20:13
 * @Filename：FtpTransformFile
 */
@Component
public class FtpTransformFile {
    /** convertMultipartFileToFile
    * 传入multipartFile和文件名字，实现转换传入multipartFile为file，以便sftp上传
    * @param multipartFile multipartFile
    * @param name name
    * @return java.io.File
    * @throws Exception
    * @author
    * @date 2023/5/11
    */
    public static File convertMultipartFileToFile(MultipartFile multipartFile,String name) throws IOException {
        File file = new File(name);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }
    /** SftpUploadToServer
    * 通过jsch上传file到img服务端的指定目录下，并对产生的临时文件进行删除
    * @param file file

    * @return void
    * @throws Exception
    * @author
    * @date 2023/5/11
    */
    public static void SftpUploadToServer(File file) throws JSchException, SftpException, IOException {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession("vsftpd_admin", "47.106.211.118", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("xsr2004217");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            channelSftp.cd("/ershou/img_nginx/html/images/");

            FileInputStream inputStream = new FileInputStream(file);
            channelSftp.put(inputStream, file.getName());

            inputStream.close();
            if (file.delete()) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
            channelSftp.disconnect();
        } catch (JSchException | SftpException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }


}
