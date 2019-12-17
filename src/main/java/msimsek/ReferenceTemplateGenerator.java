package msimsek;

import com.google.common.base.CaseFormat;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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



    public static void main(String[] args) throws IOException {
        Options options = prepareOptions();

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine commandLine = parser.parse(prepareOptions(), args);

            className = commandLine.getOptionValue("class");
            instanceName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, className);
            tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, className);
            packageName = commandLine.getOptionValue("package");
            if(packageName==null){
                packageName="";
                packageTag=".#package#";
            }


        } catch (Exception e) {
            new HelpFormatter().printHelp("Reference Template Generator", options);
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Boolean controller;
        Boolean dto;
        List<String> yesAnswers = new ArrayList<>(List.of("yes","y","Yes","Y","Evet","evet","E","e","true"));

        System.out.print("Do you want to create Controller ? (Y/N) ");
        String answerController = scanner.next();
        System.out.print("Do you want to create DTO ? (Y/N) ");
        String answerDto = scanner.next();

        if(yesAnswers.contains(answerController))
            createFile("app","Controller.java");
        if(yesAnswers.contains(answerDto))
            createFile("app","DTO.java");

        createFile("app","Service.java");

        createFile("dal","Adapter.java");
        createFile("dal","Domain.java");
        createFile("dal","Filter.java");

        createFile("dal-rdms","AdapterImpl.java");
        createFile("dal-rdms","Entity.java");
        createFile("dal-rdms","FilterSpecification.java");
        createFile("dal-rdms","Mapper.java");
        createFile("dal-rdms","Repository.java");

    }

    private static void createFile(String layer, String fileName) throws IOException {

        String path = "C:/";
        File file = new File(ReferenceTemplateGenerator.class.getClassLoader().getResource(layer + "/" +fileName).getFile());

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

        if(layer.equals("app")){
            path += "tams/tams-reference-manager/tams-reference-manager-app/src/main/java/";
            path+=packagePath[1].replace(".", "/");
        }
        if(layer.equals("dal")){
            path += "tams/tams-reference-manager/tams-reference-manager-dal-api/src/main/java/";
            path+=packagePath[1].replace(".", "/");
        }
        if(layer.equals("dal-rdms")){
            path += "tams/tams-reference-manager/tams-reference-manager-dal-rdbms/src/main/java/";
            path+=packagePath[1].replace(".", "/");
        }
        path = path.substring(0,path.length()-1);

        Path dir = Path.of(path);
        if (!Files.exists(dir))
            Files.createDirectories(dir);

        if(fileName.equals("Domain.java"))
            fileName=".java";

        String filePath = path+"/"+className+fileName;
        File tempFile = new File(filePath);
        boolean exists = tempFile.exists();

        if(!exists) {
            Files.write(Paths.get(filePath), fileContent.getBytes());
            System.out.println(filePath + " CREATED...");
        }
        else{
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

        // Required options
        Option packageName = Option.builder().required()
                .longOpt("package")
                .hasArg()
                .build();
        packageName.setRequired(false);
        packageName.setDescription("Optional. Package Name");




        options.addOption(className)
                .addOption(packageName);

        return options;
    }

}
