package msimsek;

import com.google.common.base.CaseFormat;
import org.apache.commons.cli.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ReferenceTemplateGenerator {

    private static String className;
    private static String instanceName;
    private static String packageName;
    private static String tableName;

    private static String packageTag = "#package#";
    private static String classTag = "#class#";
    private static String instanceTag = "#instance#";
    private static String tableTag = "#table#";

    private static String rootPath = "C:/tams/tams-reference-manager";
    private static Boolean recreateRootPath = false;

    private static List<String> yesAnswers = new ArrayList<>(List.of("yes", "y", "Yes", "Y", "Evet", "evet", "E", "e", "true"));

    private static Scanner scanner = new Scanner(System.in);

    private static File file = new File("cookie");
    private static Properties prop = new Properties();

    public static void main(String[] args) throws IOException {

        readRootPath();


        Options options = prepareOptions();

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine commandLine = parser.parse(prepareOptions(), args);

            className = commandLine.getOptionValue("class");
            instanceName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, className);
            tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, className);
            packageName = commandLine.getOptionValue("package");
            if (packageName == null) {
                packageName = "";
                packageTag = ".#package#";
            }

            String recreateRootPathAnswer = commandLine.getOptionValue("specify-root-path");
            if (yesAnswers.contains(recreateRootPathAnswer))
                recreateRootPath = true;
            else
                recreateRootPath = false;


        } catch (Exception e) {
            new HelpFormatter().printHelp("Reference Template Generator", options);
            return;
        }


        List<String> yesAnswers = new ArrayList<>(List.of("yes", "y", "Yes", "Y", "Evet", "evet", "E", "e", "true"));

        if(recreateRootPath){
            setReferenceRootFolder();
        }

        System.out.println("Do you want to create Controller ? (Y/N) ");
        String answerController = scanner.next();
        System.out.println("Do you want to create DTO ? (Y/N) ");
        String answerDto = scanner.next();

        if (yesAnswers.contains(answerController))
            createFile("app", "Controller.java");
        if (yesAnswers.contains(answerDto)) {
            createFile("app", "DTO.java");
            createFile("app", "DTOMapper.java");
        }

        createFile("app", "Service.java");

        createFile("dal", "Adapter.java");
        createFile("dal", "Domain.java");
        createFile("dal", "Filter.java");

        createFile("dal-rdms", "AdapterImpl.java");
        createFile("dal-rdms", "Entity.java");
        createFile("dal-rdms", "FilterSpecification.java");
        createFile("dal-rdms", "Mapper.java");
        createFile("dal-rdms", "Repository.java");

    }

    private static void readRootPath() throws IOException {



        if (!file.exists()) {
            file = new File("cookie");
        } else {
            loadProperties(prop);
        }

        Object value = prop.get("root-path");

        if (value != null) {
            rootPath = value.toString();
        } else {
            setReferenceRootFolder();
        }

    }

    static void saveProperties(Properties p) throws IOException {
        FileOutputStream fr = new FileOutputStream(file);
        p.store(fr, "Properties");
        fr.close();
    }

    static void loadProperties(Properties p) throws IOException {
        FileInputStream fi = new FileInputStream(file);
        p.load(fi);
        fi.close();
    }

    private static void writeRootPath() throws IOException {

        prop.setProperty("root-path", rootPath);
        saveProperties(prop);


    }

    private static void setReferenceRootFolder() throws IOException {

        System.out.println("Specify TAMS REFERENCE MANAGER Root Folder Path ");
        System.out.println("*** Ex => C:/tams/tams-reference-manager ***");
        String path = scanner.next();
        System.out.println("\n");

        String[] pathnames;
        File f = new File(path);

        pathnames = f.list();


        if (pathnames != null) {
            System.out.println("/////////////ROOT FOLDER////////////////");
            for (String pathname : pathnames) {
                System.out.println("--- " + pathname);
            }
            System.out.println("/////////////ROOT FOLDER////////////////");
            System.out.println("\n");
        } else {
            System.out.println("not found root folder");
            System.out.println("Please rerun ReferenceTemplateGenerator!");
            System.exit(0);
        }


        System.out.println("Is this path correct? Y/N");
        String answerPath = scanner.next();
        System.out.println("\n");

        if (!yesAnswers.contains(answerPath)) {
            System.out.println("Please rerun ReferenceTemplateGenerator!");
            System.exit(0);
        }

        rootPath = path;
        writeRootPath();
    }

    private static void createFile(String layer, String fileName) throws IOException {

        String path = rootPath;
        File file = new File(ReferenceTemplateGenerator.class.getClassLoader().getResource(layer + "/" + fileName).getFile());

        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        String fileContent = new String(data, "UTF-8");
        fileContent = fileContent.replaceAll(packageTag, packageName);
        fileContent = fileContent.replaceAll(classTag, className);
        fileContent = fileContent.replaceAll(instanceTag, instanceName);
        fileContent = fileContent.replaceAll(tableTag, tableName);

        String[] packageText = fileContent.split(System.lineSeparator(), 2);
        String[] packagePath = packageText[0].split(" ");

        if (layer.equals("app")) {
            path += "/tams-reference-manager-app/src/main/java/";
            path += packagePath[1].replace(".", "/");
        }
        if (layer.equals("dal")) {
            path += "/tams-reference-manager-dal-api/src/main/java/";
            path += packagePath[1].replace(".", "/");
        }
        if (layer.equals("dal-rdms")) {
            path += "/tams-reference-manager-dal-rdbms/src/main/java/";
            path += packagePath[1].replace(".", "/");
        }
        path = path.substring(0, path.length() - 1);

        Path dir = Path.of(path);
        if (!Files.exists(dir))
            Files.createDirectories(dir);

        if (fileName.equals("Domain.java"))
            fileName = ".java";

        String filePath = path + "/" + className + fileName;
        File tempFile = new File(filePath);
        boolean exists = tempFile.exists();

        if (!exists) {
            Files.write(Paths.get(filePath), fileContent.getBytes());
            System.out.println(filePath + " CREATED...");
        } else {
            System.out.println(filePath + " ALREADY EXISTS...");
        }
    }


    private static Options prepareOptions() {
        Options options = new Options();

        Option className = Option.builder()
                .longOpt("class")
                .hasArg()
                .build();
        className.setRequired(true);
        className.setDescription("Required. Class Name");

        Option packageName = Option.builder().required()
                .longOpt("package")
                .hasArg()
                .build();
        packageName.setRequired(false);
        packageName.setDescription("Required. Package Name");

        Option recreateRootPath = Option.builder().required()
                .longOpt("specify-root-path")
                .hasArg()
                .build();
        recreateRootPath.setRequired(false);
        recreateRootPath.setDescription("Optional. Specify Root Folder (Y/N)");


        options.addOption(className)
                .addOption(packageName)
                .addOption(recreateRootPath);

        return options;
    }

}
