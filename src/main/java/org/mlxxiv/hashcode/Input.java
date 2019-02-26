package org.mlxxiv.hashcode;

import java.io.*;
import java.util.List;

/**
 * Input (and output) files processor
 */
public class Input {
    char[][] grid;
    int low, high;
    final String filename;

    public Input(String fileName) {
        this.filename = fileName;
        readInput(fileName);
    }

    /**
     * Read the data file
     *
     * @param fileName  path to the file with data
     */
    private void readInput(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            String[] initLine = bufferedReader.readLine().trim().split(" ");

            int rows = Integer.parseInt(initLine[0]), cols = Integer.parseInt(initLine[1]);
            low = Integer.parseInt(initLine[2]);
            high = Integer.parseInt(initLine[3]);

            grid = new char[rows][cols];
            for (int y = 0; y < rows; y++) {
                char row[] = bufferedReader.readLine().trim().toCharArray();
                grid[y] = row;
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    /**
     * Read the results file
     */
    public int[][] readResultsFile() {
        try {
            FileReader fileReader = new FileReader(this.getOutFileName(this.filename));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int num = Integer.parseInt(bufferedReader.readLine().trim());

            int[][] records = new int[num][4];
            for (int y = 0; y < num; y++) {
                String[] line  = bufferedReader.readLine().trim().split(" ");
                int[] row = new int[] {
                    Integer.parseInt(line[0]),
                    Integer.parseInt(line[1]),
                    Integer.parseInt(line[2]),
                    Integer.parseInt(line[3])
                };
                records[y] = row;
            }

            bufferedReader.close();
            return records;
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + getOutFileName(filename) + "'");

        } catch (IOException ex) {
            System.out.println("Error reading file '" + getOutFileName(filename) + "'");
        }
        return null;
    }

    /**
     * Write the results to file
     *
     * @param out   file to write data to
     */
    public void writeOutput(List<int[]> out) {
        String outFileName = getOutFileName(this.filename);

        try {
            FileWriter fileWriter = new FileWriter(outFileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.valueOf(out.size()));
            bufferedWriter.newLine();

            for (int[] row : out) {
                bufferedWriter.write(row[0] + " " + row[1] + " " + row[2] + " " + row[3]);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + outFileName + "'");
        }
    }



    /**
     * Get output file name - change input file name from XXX.in to XXX.out
     *
     * @param inFileName
     * @return      file name with the replaced extension (from "in" to "out")
     */
    private String getOutFileName(String inFileName) {
        return inFileName.replaceAll(".in", ".out");
    }
}