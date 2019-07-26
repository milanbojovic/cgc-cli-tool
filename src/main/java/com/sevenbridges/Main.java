package com.sevenbridges;

import com.sevenbridges.cli.CommandLineOptions;
import com.sevenbridges.http.json.MetaData;
import com.sevenbridges.http.json.UpdateFileRequest;
import org.apache.commons.cli.*;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String CGC_URL = "https://cgc-api.sbgenomics.com/";

    public static void main(String[] args) {
        CGCClient cgcClient = new CGCClient(CGC_URL);
        CommandLineOptions cli = new CommandLineOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse( cli.getOptions(), args);
        } catch (ParseException pe) {
            cli.printHelp( 100, "cgccli", "", "", 5, 5, true, System.out);
            System.exit(1);
        }

        String accessToken = cmd.getOptionValue("t");
        if (cmd.hasOption("projects list")) {
            System.out.println("Listing projects:");
            System.out.println(cgcClient.listProjects(accessToken));
        }

        if (cmd.hasOption("files list")) {
            cli.checkAdditionalOption(cmd, "project", "When executing with \"files list\" option, you must specify additional option --project");
            System.out.println("Listing files for project \"" + cmd.getOptionValue("project") + "\"");
            System.out.println(cgcClient.listFiles(accessToken, cmd.getOptionValue("project")));
        }

        if (cmd.hasOption("files stat")) {
            cli.checkAdditionalOption(cmd, "file", "When executing with \"files stat\" option, you must specify additional option --file");
            System.out.println("Listing file details for file [" + cmd.getOptionValue("file") + "]");
            System.out.println(cgcClient.listFileDetails(accessToken, cmd.getOptionValue("file")));
        }

        if (cmd.hasOption("files update")) {
            cli.checkAdditionalOption(cmd, "file", "When executing with \"files update\" option, you must specify additional option --file");
            System.out.println("Updating file details for file [" + cmd.getOptionValue("file") + "]");
            UpdateFileRequest updateFileRequest = cli.createUpdateFileBody(cmd.getArgList());
            cgcClient.updateFileDetails(accessToken, cmd.getOptionValue("file"), updateFileRequest);
        }

        if (cmd.hasOption("files download")) {
            cli.checkAdditionalOption(cmd, "file", "When executing with \"files stat\" option, you must specify additional option --file");
            cli.checkAdditionalOption(cmd, "dest", "When executing with \"files stat\" option, you must specify additional option --dest");
            System.out.println("Downloading file [" + cmd.getOptionValue("file") + "] to " + cmd.getOptionValue("dest"));
            System.out.println();
            cgcClient.downloadFile(accessToken, cmd.getOptionValue("file"), cmd.getOptionValue("dest"));
        }
    }
}
