package com.sevenbridges;

import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {

        Options options = new Options();

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

        }
    }
}
