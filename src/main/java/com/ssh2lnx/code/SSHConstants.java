// Package declaration
package com.ssh2lnx.code;

// Separation of concerns: Constants moved to a separate file to adhere to SRP (Single Responsibility Principle)
public class SSHConstants {
    public static final String HOST = "192.168.123.456";
    public static final String USER = "lnxComputer";
    public static final String PASSWORD = "password123";
    public static final String REMOTE_FILE_PATH = "/home/lnxComputer/Desktop/sample.txt";
    public static final String LOCAL_FILE_PATH = "C:\\Users\\winComputer\\Desktop\\sample.txt";
}