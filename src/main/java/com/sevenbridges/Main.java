package com.sevenbridges;

import org.apache.commons.cli.*;

public class Main {

    public static String CGC_URL = "https://cgc-api.sbgenomics.com/";


    public static void main(String[] args) {

        CGCClient cgcClient = new CGCClient(CGC_URL);
        //System.out.println(cgcClient.listProjects("d7c8e207e2b04c268a5dee109ad2548f"));
        //System.out.println(cgcClient.listFiles("d7c8e207e2b04c268a5dee109ad2548f", "milanbojovic/copy-of-personal-genome-project-uk-pgp-uk"));
        //System.out.println(cgcClient.listFileDetails("d7c8e207e2b04c268a5dee109ad2548f", "5d36d1a7e4b07db62607e3ac"));
        cgcClient.downloadFile("d7c8e207e2b04c268a5dee109ad2548f", "5d36d1a7e4b07db62607e544", "/tmp/file1.txt");


/*        Options options = new Options();

        Option token = Option.builder("t")
                .longOpt("token")
                .required()
                .hasArg()
                .desc("Token used for authentication")
                .argName("token")
                .build();

        Option listProjects = Option.builder("pl")
                .longOpt("projects list")
                .hasArg(false)
                .desc("List projects")
                .build();

        Option listFiles = Option.builder("fl")
                .longOpt("files list")
                .hasArg()
                .desc("List files in project")
                .argName("project")
                .build();

        Option listFileDetails = Option.builder("fs")
                .longOpt("files stat")
                .hasArg()
                .desc("List files in project")
                .argName("file_id")
                .build();

        Option updateFile = Option.builder("u")
                .longOpt("files update")
                .hasArg()
                .desc("Update file details")
                .argName("file_id")
                .build();

        Option file = Option.builder()
                .longOpt("file")
                .hasArg()
                .desc("File name")
                .argName("file_id")
                .build();

        Option downloadFile = Option.builder()
                .longOpt("files download")
                .hasArg()
                .desc("Files download")
                .argName("file_id")
                .build();

        Option destinationFile = Option.builder()
                .longOpt("dest")
                .hasArg()
                .desc("Destination file path")
                .argName("file_path")
                .build();

        options.addOption(token);
        options.addOption(listProjects);
        options.addOption(listFiles);
        options.addOption(listFileDetails);
        options.addOption(updateFile);
        options.addOption(downloadFile);
        options.addOption(destinationFile);
        options.addOption(file);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse( options, args);
        } catch (ParseException pe) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "cgccli", options );
            System.exit(1);
        }

        if (cmd.hasOption("t")) {
            System.out.println("Detected token");

        }

        if (cmd.hasOption("pl")) {
            System.out.println("Listing projects");

        }

        if (cmd.hasOption("fl")) {
            System.out.println("Files list: ");
        }

        if (cmd.hasOption("fs")) {
            System.out.println("Files details");
        }

        if (cmd.hasOption("u")) {
            System.out.println("Update file details");
        }

        if (cmd.hasOption("files download")) {
            System.out.println("Files download");

        }

        if (cmd.hasOption("file")) {
            System.out.println("Detected file directive");

        }

        if (cmd.hasOption("dest")) {
            System.out.println("Destination file directive");

        }*/


    }
}
