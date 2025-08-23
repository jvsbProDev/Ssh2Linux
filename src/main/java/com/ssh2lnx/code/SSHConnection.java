package com.ssh2lnx.code;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

// Interface segregation: Interfaces created for abstraction and flexibility
interface SSHConnection {
    Session connectSession() throws JSchException;
    void disconnectSession(Session session);
}