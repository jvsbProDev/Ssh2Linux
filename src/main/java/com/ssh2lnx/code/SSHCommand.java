package com.ssh2lnx.code;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;

import static com.ssh2lnx.code.SSHConstants.*;

/**
 * Title: Simple SSH Connection to Linux Server
 * Author: Jovi Van Shannon Belnas
 */

public class SSHCommand {

    public static void main(String[] args) throws Throwable {
        System.out.println("Connecting to LINUX server...");
	    String output = runCommandOutput("uname");
        System.out.print(output);
        output = runCommandOutput("whoami");
        System.out.println(output);
    }

    static String runCommandOutput(String command) throws Throwable {
        String output = "NO OUTPUT RETURNED";
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            connectSession(session);
            output = commandSessionOutput(session, command);
            session.disconnect();
            //System.out.println("Disconnected...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    static Session connectSession(Session session) throws Throwable {
        try {
            session.setPassword(password);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
	        //config.put("PreferredAuthentications", password);
            session.setConfig(config);
            session.connect();
            //System.out.println("Connected to Host: " + host);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }

    static String commandSessionOutput(Session session, String command) throws JSchException, IOException {
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);
        InputStream in = channel.getInputStream();
        StringBuilder outputBuilder = new StringBuilder();
        channel.connect();

        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) break;
                //System.out.print(new String(tmp, 0, i));
                outputBuilder.append(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                //System.out.println("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            channel.disconnect();
        }
        return outputBuilder.toString();
    }
}
