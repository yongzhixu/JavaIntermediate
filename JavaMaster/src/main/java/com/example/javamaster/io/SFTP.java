package com.example.javamaster.io;

import com.jcraft.jsch.*;

public class SFTP {
//https://www.baeldung.com/java-file-sftp
    private String remoteHost = "1.117.178.130";
    private String username = "clsftp";
    private String password = "m0dc8jncWzhW@JRB";
    public ChannelSftp setupJsch() throws JSchException {
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        JSch jsch = new JSch();
//        jsch.setKnownHosts("/Users/john/.ssh/known_hosts");
        Session jschSession = jsch.getSession(username, remoteHost);
        jschSession.setConfig(config);
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }


}
