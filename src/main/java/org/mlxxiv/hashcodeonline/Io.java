package org.mlxxiv.hashcodeonline;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Input (and output) files processor
 */
public class Io {
    final String filename;
    List<Photo> photos;

    public Io(String fileName) {
        this.filename = fileName;
        readInput(fileName);
    }

    /**
     * Read the data file
     *
     * @param fileName path to the file with data
     */
    private void readInput(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);


            int num = Integer.parseInt(bufferedReader.readLine().trim());
            photos = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                String[] row = bufferedReader.readLine().trim().split(" ");
                String orientation = row[0];
                int tagsNum = Integer.parseInt(row[1]);
                List<String> tags = new ArrayList<>();
                for (int j = 2; j < 2 + tagsNum; j++) {
                    tags.add(row[j]);
                }
                Photo photo = new Photo(i, orientation, tags);
                photos.add(photo);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }


    /**
     * Write the results to file
     *
     * @param out file to write data to
     */
    public void writeOutput(List<Slide> out) {
        try {
            FileWriter fileWriter = new FileWriter(this.filename + ".out");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.valueOf(out.size()));
            bufferedWriter.newLine();

            for (Slide slide : out) {

                bufferedWriter.write( slide.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + this.filename+".out" + "'");
        }
    }

    public void writeline(Slide slide) {
        try {
            FileWriter fileWriter = new FileWriter(this.filename + ".out");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write( slide.toString());
            bufferedWriter.newLine();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + this.filename+".out" + "'");
        }
    }
}