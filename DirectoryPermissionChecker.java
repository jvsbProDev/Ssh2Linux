import java.io.IOException;

public class DirectoryPermissionChecker {

    private static final String userHome = "/home/testadmin";
    private static final String integrationDir = "/home/testadmin/INTEGRATION";
    private static final String testcaseDir = "/home/testadmin/INTEGRATION/TESTCASE";
    private static final String logsDir = "/home/testadmin/INTEGRATION/LOGS";

    public void checkAndSetDirectoriesAndPermissions() {
        checkAndSetDirectory(integrationDir);
        checkAndSetDirectory(testcaseDir);
        checkAndSetDirectory(logsDir);

        checkAndSetPermissions(userHome);
        checkAndSetPermissions(integrationDir);
        checkAndSetPermissions(testcaseDir);
        checkAndSetPermissions(logsDir);
    }

    private void checkAndSetDirectory(String directory) {
        try {
            String output = runCommandOnlyOutput("sudo bash -c \"if [ -d " + directory + " ]; then echo 'EXIST'; else echo 'NOT_EXIST'; fi\"");
            if (output.contains("NOT_EXIST")) {
                runCommandOnlyOutput("sudo bash -c \"mkdir -p " + directory + "\"");
                System.out.println("\033[1;32mDirectory " + directory + " has been created.\033[0m");
            } else {
                System.out.println("\033[1;34mDirectory " + directory + " exists.\033[0m");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkAndSetPermissions(String path) {
        try {
            String permission = runCommandOnlyOutput("sudo bash -c \"ls -la " + path + " | awk '{print substr($0, 2, 9)}'\"");
            if (permission.equals("rwxrwxrwx")) {
                System.out.println("\033[1;34mPermissions for " + path + " are set.\033[0m");
            } else {
                runCommandOnlyOutput("sudo bash -c \"chmod 777 " + path + "\"");
                System.out.println("\033[1;32mPermissions for " + path + " have been set.\033[0m");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String runCommandOnlyOutput(String command) throws IOException, InterruptedException {
        // Implement your command execution logic here and return the output
        return ""; // Placeholder for the actual implementation
    }

    public static void main(String[] args) {
        DirectoryPermissionChecker checker = new DirectoryPermissionChecker();
        checker.checkAndSetDirectoriesAndPermissions();
    }
}
