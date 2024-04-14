package com.example.tk_etlproc.api;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class FtpConnectionTests {
    @Test
    void testConnectionFtp() throws IOException {
        FTPClient ftp = new FTPClient();

        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        ftp.connect("eu-central-1.sftpcloud.io", 21);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }

        ftp.login("359c4274d076459488551ef3d4c9809b", "7ksj2SXMsQnEVIqHYAKFlzdFRzhuZORy");
        FTPFile[] files = ftp.listFiles("");


    }
    @Test
    void test(){
        assert 1==1;
    }


}
