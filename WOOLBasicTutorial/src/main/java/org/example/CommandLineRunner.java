package org.example;

import nl.rrd.wool.model.WoolDialogue;
import nl.rrd.wool.parser.WoolParser;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CommandLineRunner {

    public CommandLineRunner() {
        WoolDialogue wd = null;
    }

    public static void main (String[] args) {
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        //  Ask for a .wool script
        System.out.println("Path to WOOL Script: ");

        // Get the input as a String
        String woolScriptFile = scanner.next();

        // Initialize the CommandLineRunner and call analyzeWoolScript()
        CommandLineRunner clr = new CommandLineRunner();
        clr.analyzeWoolScript(woolScriptFile);
    }

    private void analyzeWoolScript(String fileName) {
        System.out.println("There should be a WOOL Script here: " + fileName);

        // First, create a File object from our fileName String
        File file = new File(fileName);
        if (!file.exists()) {
            System.err.println("ERROR: File not found: " + fileName);
            System.exit(1);
            return;
        }

        // Initialize a ReadResult where the results of the parse will be stored
        WoolParser.ReadResult readResult;

        try {
            // Create a new WoolParser for the given file
            WoolParser parser = new WoolParser(file);

            // Parse the WOOL script and store the results in readResult
            readResult = parser.readDialogue();

            // Retrieve the WoolDialogue representation from the readResult
            WoolDialogue woolDialogue = readResult.getDialogue();

            // Output some basic information about the WOOL script
            System.out.println(woolDialogue.toString());
        } catch (IOException ex) {
            System.err.println("ERROR: Can't read file: " +
                    file.getAbsolutePath() + ": " + ex.getMessage());
            System.exit(1);
            return;
        }

    }
}
