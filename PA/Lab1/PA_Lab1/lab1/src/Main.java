import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    final static int sizes = 1000000;

    public static void main(String[] args) {
        int runSize = sizes;
        int numFiles = 10;
        String tempDir = "",
                outputFileName = "output.txt",
                inputFileName = "input.txt";
        boolean gzip = false;

        inputFileFilling(inputFileName);

        String[] inputArray = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFileName));
            inputArray = readStreamTillEnd(br);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("total lines: " + inputArray.length);
        System.err.println("run size: " + runSize + "\nNum files: " + numFiles + "\nin file: " + inputFileName + "\nout file: " + outputFileName + "\nstdinput: \n");

        Sorter s = new Sorter(runSize, numFiles, tempDir, gzip);
        s.sort(inputArray, outputFileName);
    }

    public static void inputFileFilling(String inputFileName) {
        //sizes
        int[] inputArray = new int[sizes];

        // sizes
        for (int i = 0; i < sizes; i++) {
            inputArray[i] = (int) (9999+Math.random() *10001);
        }

        String str = Arrays.stream(inputArray)
                .mapToObj(String::valueOf)
                .reduce((x, y) -> x + "\n" + y)
                .get();

        try {
            FileWriter fileWriter = new FileWriter(inputFileName);
            fileWriter.write(str);
            fileWriter.close();
            System.out.println("Successfully wrote to the file.\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String[] readStreamTillEnd(BufferedReader br) {
        LinkedList<String> linesList = new LinkedList<String>();
        try {
            String line = br.readLine();
            while (line != null) {
                linesList.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linesList.toArray(new String[0]);
    }
}
