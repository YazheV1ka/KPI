import java.io.*;
import java.util.Arrays;

public class Sorter {
    private StringHeap _heap;
    private int _runs;
    private int _maxFiles;
    private FileManager fileManage;
    private BufferedWriter _currentStream;
    private BufferedWriter[] writers;
    private boolean[] writeIsClosed;
    private int[] _fibSequence;
    private int[] _currentRuns;
    private int _totalRuns = 1;
    private int _totalPasses = 0;
    private String _outputFile;

    public Sorter(int bufferSize, int maxFiles, String tempDirectory) {
        _heap = new StringHeap(bufferSize);
        _maxFiles = maxFiles;

        fileManage = new FileManager("UTF-8", 8192, 8192, false, tempDirectory);
    }

    public void sort(String[] data, String outputFile) {

        _outputFile = outputFile;
        _fibSequence = fibSequence(data.length / (_heap.capacity() * 2) + 1, _maxFiles);

        _currentRuns = new int[_maxFiles - 1];
        _currentRuns[0] = 1;

        writers = new BufferedWriter[_maxFiles];
        writeIsClosed = new boolean[_maxFiles];

        int i = 0, j = 0, capacity = _heap.capacity();

        int[] numArr = Arrays.stream(data).mapToInt(Integer::parseInt).toArray();

        int tmp;
        for (; i < numArr.length - 1; i++) {
            for (j = 0; j < numArr.length - i - 1; j++) {
                if (numArr[j] > numArr[j + 1]) {
                    tmp = numArr[j];
                    numArr[j] = numArr[j + 1];
                    numArr[j + 1] = tmp;
                }
            }
        }

        for (i = 0; i < capacity && i < data.length; i++) {
            _heap.insert(String.valueOf(numArr[i]));
        }

        if (_heap.size() > 0) {
            _runs = runFunction();

            if (writers[_runs] != null) putStreamRuns("");

            while (j != numArr.length) {
                putStreamRuns(String.valueOf(numArr[j]));
                j++;
            }
        }

        int backup = _runs;

        while ((_runs = runFunctionDummy()) != -1) {
            putStreamRuns("");
        }
        _runs = backup;

        System.out.println("\nTotal runs: " + _totalRuns);

        for (int k = 0; k < _maxFiles - 1; k++) {
            if (writers[k] != null) {
                try {
                    writers[k].close();
                    writeIsClosed[k] = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        merge();
    }

    private void deleteFile(String fileName) {
        try {
            File file = fileManage.getFile(fileName);
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int numReaders(BufferedReader[] fr) {
        int out = 0;
        for (BufferedReader bufferedReader : fr) {
            if (bufferedReader != null)
                out++;
        }
        return out;
    }

    private void merge() {
        Heap mergeHeap = new Heap(_maxFiles - 1);

        BufferedReader[] fileReaders = new BufferedReader[_maxFiles];

        int finalRun = -1;

        boolean first = true;

        _runs = _maxFiles - 1;

        while (numReaders(fileReaders) > 1 || first) {
            first = false;

            int tempruns = -1;

            for (int i = 0; i < _maxFiles; i++) {
                String inLine = null;
                try {
                    if (i == _runs || !fileManage.getFile("file" + i).exists())
                        continue;

                    if (fileReaders[i] == null)
                        fileReaders[i] = fileManage.createBufferedReader("file" + i);

                    inLine = fileReaders[i].readLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (inLine == null) {
                    tempruns = i;
                    try {
                        fileReaders[i].close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    deleteFile("file" + i);
                    fileReaders[i] = null;

                    _totalPasses++;
                } else if (!inLine.isEmpty()) mergeHeap.insert(new Pair<Integer, String>(i, inLine));

            }

            if (_currentStream != null)
                putStreamMerge("");

            while (mergeHeap.size() > 0) {
                Pair<Integer, String> out = mergeHeap.peek();
                int key = out.key;

                String inLine = null;
                try {
                    inLine = fileReaders[key].readLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (inLine == null) {
                    mergeHeap.get();
                    tempruns = key;
                    try {
                        fileReaders[key].close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    deleteFile("file" + key);
                    fileReaders[key] = null;

                    _totalPasses++;
                } else {
                    if (inLine.isEmpty())
                        mergeHeap.get();
                    else
                        mergeHeap.replace(new Pair<Integer, String>(key, inLine));
                }

                putStreamMerge(out.value);
            }

            if (tempruns != -1) {
                finalRun = _runs;
                _runs = tempruns;
                try {
                    _currentStream.close();
                    _currentStream = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("\nTotal Passes: " + (_totalPasses - (_maxFiles - 2)));

        if (_outputFile != null && new File(_outputFile).exists())
            deleteFile(_outputFile);

        if (_outputFile == null) {
            try {
                FileInputStream br = new FileInputStream(fileManage.getDirectory() + "file" + (finalRun));

                byte[] buffer = new byte[1024];
                int length;
                while ((length = br.read(buffer)) > 0) {
                    System.out.write(buffer, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.flush();
        } else {
            File old = fileManage.getFile("file" + finalRun);
            File output = fileManage.getFile(_outputFile);

            if (old.renameTo(output))
                System.out.println("FINISHED\n");
            else
                System.out.println("Error in renaming final output\n");
        }
    }

    private int runFunction() {
        _totalRuns++;
        int puttable = (_runs + 1) % (_maxFiles - 1);
        int firstOneChecked = puttable;

        while (_currentRuns[puttable] >= _fibSequence[puttable + 1]) {
            puttable = (puttable + 1) % (_maxFiles - 1);
            if (puttable == firstOneChecked) {
                fibNext(_fibSequence);
            }
        }
        _currentRuns[puttable] += 1;
        return puttable;
    }

    private int runFunctionDummy() {
        _totalRuns++;
        int puttable = (_runs + 1) % (_maxFiles - 1);
        int firstOneChecked = puttable;

        while (_currentRuns[puttable] >= _fibSequence[puttable + 1]) {
            puttable = (puttable + 1) % (_maxFiles - 1);
            if (puttable == firstOneChecked)
                return -1;
        }

        _currentRuns[puttable] += 1;
        return puttable;
    }

    private void putStreamRuns(String item) {
        if (writers[_runs] == null || writeIsClosed[_runs]) {
            writeIsClosed[_runs] = false;
            try {
                writers[_runs] = fileManage.createOutputStreamWriter("file" + _runs);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            fileManage.write(writers[_runs], item + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putStreamMerge(String item) {
        if (_currentStream == null) {
            try {
                _currentStream = fileManage.createOutputStreamWriter("file" + _runs);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            fileManage.write(_currentStream, item + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fibNext(int[] previousSequence) {
        int output = -1;
        for (int i = 0; i < previousSequence.length; i++) {
            if (previousSequence[i] == 0)
                output = i;
        }
        if (output == -1)
            throw new Error("No zeros");

        for (int i = 0; i < _maxFiles; i++) {
            previousSequence[i] += previousSequence[previousSequence.length - 1];
        }

        previousSequence[previousSequence.length - 1] = 0;
        {
            Arrays.sort(previousSequence);
        }
    }

    private int[] fibSequence(int dataLength, int maxFiles) {
        int[] returnArray = new int[maxFiles];
        returnArray[maxFiles - 1] = 1;
        int output = maxFiles - 1;
        int total = 1;

        while (true) {
            for (int i = 0; i < maxFiles; i++) {
                if (i != output) {
                    returnArray[i] += returnArray[output];
                    total += returnArray[i];
                }
            }
            returnArray[output] = 0;
            if (total >= dataLength) {
                Arrays.sort(returnArray);
                return (returnArray);
            }
            total = 0;
            output--;

            if (output < 0) {
                output += (maxFiles);
            }
        }
    }
}
