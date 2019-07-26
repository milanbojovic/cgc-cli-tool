package com.sevenbridges.cli;

import com.sevenbridges.http.json.UpdateFileRequest;
import org.apache.commons.cli.*;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CommandLineOptions {
    Options options;

    public CommandLineOptions() {
        this.options = createOptions();
    }

    public Options getOptions() {
        return options;
    }

    Option token = Option.builder("t")
            .longOpt("token")
            .required()
            .hasArg()
            .desc("Token used for authentication")
            .argName("token")
            .build();

    Option listProjects = Option.builder()
            .longOpt("projects list")
            .hasArg(false)
            .desc("List projects")
            .build();

    Option listFiles = Option.builder()
            .longOpt("files list")
            .hasArg(false)
            .desc("List files in project")
            .build();

    Option listFileDetails = Option.builder()
            .longOpt("files stat")
            .hasArg(false)
            .desc("List file details")
            .build();

    Option updateFile = Option.builder()
            .longOpt("files update")
            .desc("Update file details")
            .hasArg(false)
            .build();

    Option downloadFile = Option.builder()
            .longOpt("files download")
            .desc("Update file details")
            .build();

    Option destinationFile = Option.builder()
            .longOpt("dest")
            .hasArg()
            .desc("Destination file path")
            .argName("file_path")
            .build();

    Option file = Option.builder()
            .longOpt("file")
            .hasArg()
            .desc("File id")
            .argName("file_id")
            .build();

    Option project = Option.builder()
            .longOpt("project")
            .hasArg()
            .desc("Project name")
            .argName("project")
            .build();

    private Options createOptions() {
        options = new Options();
        OptionGroup actions = new OptionGroup();
        actions.addOption(listProjects);
        actions.addOption(listFiles);
        actions.addOption(listFileDetails);
        actions.addOption(updateFile);
        actions.addOption(downloadFile);
        actions.setRequired(true);

        options.addOption(token);
        options.addOptionGroup(actions);
        options.addOption(destinationFile);
        options.addOption(file);
        options.addOption(project);
        return options;
    }


    public void printHelp(final int width, final String cmdLineSyntax,
                                 final String header, final String footer, final int leftPad, final int descPad, final boolean autoUsage,
                                 final OutputStream out) {
        PrintWriter writer = new PrintWriter(out);
        final HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.setOptionComparator(null);
        helpFormatter.printHelp(writer, width, cmdLineSyntax, header, options, leftPad, descPad, footer, autoUsage);
        writer.flush();
    }

    public void checkAdditionalOption(CommandLine cmd, String option, String msg) {
        if (!cmd.hasOption(option)) {
            System.out.println(msg);
            System.exit(1);
        }
    }

    public UpdateFileRequest createUpdateFileBody(List<String> args) {
        UpdateFileRequest updateFileRequest = new UpdateFileRequest();
        ArrayList<String> tags = new ArrayList<String>();

        for (String arg : args) {
            String argName = arg.split("=")[0];
            String val = arg.split("=")[1];

            // CGC api does not support file renaming
            /*if (argName.equals("name")){
                updateFileRequest.setName(val);
            }*/

            // CGC api does not support metadata updating
            /*if (argName.contains("metadata")) {
            }*/

            if (argName.equals("tag")){
                tags.add(val);
            }
        }

        updateFileRequest.setTags(tags.toArray(new String[0]));
        return updateFileRequest;
    }
}
