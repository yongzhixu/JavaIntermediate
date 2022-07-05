package com.example.javamaster.io;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SFTPTest {


    @Test
    public void whenUploadFileUsingJsch_thenSuccess() throws JSchException, SftpException {
        SFTP sftp = new SFTP();
        ChannelSftp channelSftp = sftp.setupJsch();
        channelSftp.connect();
        String localFile = "src/main/resources/sample.txt";
        String remoteDir = "upload/bosch_demo/";
        channelSftp.put(localFile, remoteDir + "jschFile.txt");
        channelSftp.exit();
    }
}