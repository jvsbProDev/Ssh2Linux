public void checkDirectoryAndSetPermission(String directoryPath, String directoryName) {
    String checkDirectoryCommand = "sudo bash -c \"if [ -d " + directoryPath + " ]; then echo \\\"" + directoryName + " DIRECTORY EXIST\\\"; else echo \\\"NO " + directoryName + " DIRECTORY FOUND\\\"; fi\"";
    String directoryPermissionCommand = "sudo bash -c \"echo $(ls -la " + directoryPath + " | awk '/^d.*" + directoryName + "/' | awk '{print substr($0, 2, 9)}')\"";

    String output = runCommandOnlyOutput(checkDirectoryCommand).replace("\n", "");
    if (output.contains(directoryName + " DIRECTORY EXIST")) {
        System.out.println("\033[1;34m------------------------ " + directoryName.toUpperCase() + " DIRECTORY FOUND ------------------------\033[0m");
        output = runCommandOnlyOutput(directoryPermissionCommand).replace("\n", "");
        if (!output.contentEquals("rwxrwxrwx")) {
            String setPermissionCommand = "sudo bash -c \"chmod 777 " + directoryPath + "\"";
            runCommandOnlyOutput(setPermissionCommand);
            System.out.println("\033[1;32m------------------------ " + directoryName.toUpperCase() + " PERMISSION HAS BEEN SET ------------------------\033[0m");
        }
    } else {
        System.out.println("\033[1:31m------------------------ NO " + directoryName.toUpperCase() + " DIRECTORY FOUND ------------------------\033[0m");
        String createDirectoryCommand = "sudo bash -c \"mkdir " + directoryPath + "\"";
        runCommandOnlyOutput(createDirectoryCommand);
        System.out.println("\033[1;32m------------------------ " + directoryName.toUpperCase() + " HAS BEEN CREATED ------------------------\033[0m");
        String setPermissionCommand = "sudo bash -c \"chmod 777 " + directoryPath + "\"";
        runCommandOnlyOutput(setPermissionCommand);
        System.out.println("\033[1;32m------------------------ " + directoryName.toUpperCase() + " PERMISSION HAS BEEN SET ------------------------\033[0m");
    }
}

public void checkFileAndSetPermission(String filePath, String fileName) {
    String checkFileCommand = "sudo bash -c \"if [ -f " + filePath + " ]; then echo \\\"" + fileName + " FILE EXIST\\\"; else echo \\\"NO " + fileName + " FILE FOUND\\\"; fi\"";
    String filePermissionCommand = "sudo bash -c \"echo $(ls -la " + filePath + " | awk '/^.*" + fileName + "/' | awk '{print substr($0, 2, 9)}')\"";

    String output = runCommandOnlyOutput(checkFileCommand).replace("\n", "");
    if (output.contains(fileName + " FILE EXIST")) {
        System.out.println("\033[1;34m------------------------ " + fileName.toUpperCase() + " FILE FOUND ------------------------\033[0m");
        output = runCommandOnlyOutput(filePermissionCommand).replace("\n", "");
        if (!output.contentEquals("rwxrwxrwx")) {
            String setPermissionCommand = "sudo bash -c \"chmod 777 " + filePath + "\"";
            runCommandOnlyOutput(setPermissionCommand);
            System.out.println("\033[1;32m------------------------ " + fileName.toUpperCase() + " PERMISSION HAS BEEN SET ------------------------\033[0m");
        }
    } else {
        System.out.println("\033[1:31m------------------------ NO " + fileName.toUpperCase() + " FILE FOUND ------------------------\033[0m");
    }
}

public void adirectoryIsExistingAndPermissionIsAllSet() {
    checkDirectoryAndSetPermission("/home/testadmin/INTEGRATION", "INTEGRATION");
    checkDirectoryAndSetPermission("/home/testadmin/INTEGRATION/TESTCASE", "TESTCASE");
    checkDirectoryAndSetPermission("/home/testadmin/INTEGRATION/LOGS", "LOGS");
    checkFileAndSetPermission("/home/testadmin/INTEGRATION/runb2f.sh", "RUN SCRIPT");
}