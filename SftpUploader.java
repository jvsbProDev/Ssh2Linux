import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.OutputStream;

public class SftpUploader {

    private static final String user = "your_username";
    private static final String host = "your_host_address";

    public static void uploadFile(String filePath) {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            connectSession(session);
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            try (OutputStream out = sftpChannel.put(filePath)) {
                if (filePath.endsWith("runb2f.sh")) {
                    // Write content for runb2f.sh
                    out.write("#!/bin/bash\n".getBytes());
                    out.write("\n".getBytes());
                    out.write("if [ -z \"$1\" ]; then\n".getBytes());
                    out.write(" echo \"Error: Argument not provided. e.g. B2F_FILENAME.txt\"\n".getBytes());
                    out.write(" exit 1\n".getBytes());
                    out.write("fi\n".getBytes());
                    out.write("\n".getBytes());
                    out.write("cd $data/temp && cp /home/testadmin/INTEGRATION/TESTCASE/$1 ./IDAPSIN\n".getBytes());
                    out.write("\n".getBytes());
                    out.write("init.tfx1.sh && init.tfj1.sh && init.tfji.sh && reset.tfsq.sh && tfdr0215 | tee /home/testadmin/INTEGRATION/LOGS/$1.log\n".getBytes());
                    out.write("cd $spool && ls -ltr tfdr* | tail -n 3 | tee -a /home/testadmin/INTEGRATION/LOGS/$1.log\n".getBytes());
                    out.write("cd $spool && head -n 10000 $(ls -tr tfdr* | tail -n 3 | grep 'prt2') | tee -a /home/testadmin/INTEGRATION/LOGS/$1.log\n".getBytes());
                } else if (filePath.endsWith("SANITY.txt")) {
                    // Write content for SANITY.txt
                    out.write("06204622630002281537000 N74397033325000155989325111111110619000000003200036000000001200840Global Blue Tax Free SANTA\n".getBytes());
                    out.write("0621 000000 VCMS TEST AP - 1274162 AUTO PRESENTATION DRAFT 100000000000015TERMID01000000000000 00212\n".getBytes());
                    out.write("0623 CRPP 12345678901234560123456789012345678901234567890123SANITYTST 027 Rodeo Road13\n".getBytes());
                    out.write("06241111111111SD11110000000000000000 00000123400CR00000000000000000000000000000000000000000001234560055550000\n".getBytes());
                    out.write("062530927413218672210000028600003600 0000 000000000000 00 000000000000000 000000000000000\n".getBytes());
                    out.write("910077777763584000000000000816000000000002000001000000000007000000 000000003000000000000000000000000000001050000000\n".getBytes());
                    out.write("920077777763584000000000070001000000000008000002000000000029000000 000000011000000000000000000000000000034091000000\n".getBytes());
                } else {
                    throw new IllegalArgumentException("Invalid file path: " + filePath);
                }
            }
            channel.disconnect();
            sftpChannel.disconnect();
            session.disconnect();
        } catch (JSchException | SftpException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void connectSession(Session session) throws JSchException {
        // Add any session configuration if needed
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword("your_password");
        session.connect();
    }

    public static void main(String[] args) {
        uploadFile("/home/testadmin/INTEGRATION/runb2f.sh");
        uploadFile("/home/testadmin/INTEGRATION/TESTCASE/SANITY.txt");
    }
}