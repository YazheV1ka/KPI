import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    final static int sizes = 10000;
    static Random rand = new Random();

    public static void main(String[] args) {
        int runSize = sizes;
        int numFiles = 10;
        String tempDir = "",
                outputFileName = "output.txt",
                inputFileName = "input.txt";

        inputFileFilling(inputFileName);

        String[] inputArray = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFileName));
            inputArray = readStreamTillEnd(br);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("total lines: " + inputArray.length);
        System.out.println("run size: " + runSize + "\nNum files: " + numFiles + "\nin file: " + inputFileName + "\nout file: " + outputFileName);

        Sorter s = new Sorter(runSize, numFiles, tempDir);
        s.sort(inputArray, outputFileName);
    }

    public static void inputFileFilling(String inputFileName) {
        int[] inputArray = new int[10000];

        for (int i = 0; i < 10000; i++) {
            //inputArray[i] = (int) (9999+Math.random() *10001);
            inputArray[i] = rand.nextInt(10000);
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
