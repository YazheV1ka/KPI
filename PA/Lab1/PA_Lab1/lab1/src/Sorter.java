

import java.io.*;
import java.util.Arrays;


// Class to perform sorting
public class Sorter {
    private boolean DEBUG = true;
    private StringHeap _heap;
    private int _runs;
    private int _maxFiles;
    private IOManager _IOMan;
    private BufferedWriter _currentStream;
    private BufferedWriter[] writers;
    private boolean[] writeIsClosed;
    private int[] _fibSequence;
    private int[] _currentRuns;
    private int _totalRuns = 1;
    private int _totalPasses = 0;
    private String _outputFile;

    public Sorter(int bufferSize, int maxFiles, String tempDirectory, boolean gzip) {
        _heap = new StringHeap(bufferSize);
        _maxFiles = maxFiles;

        _IOMan = new IOManager((gzip ? IOManager.GZIPPED : IOManager.NORMAL), "UTF-8", 8192, 8192, false, tempDirectory);
    }

    public void sort(String[] data, String outputFile) {

        _outputFile = outputFile;
        _fibSequence = fibSequence(data.length / (_heap.capacity() * 2) + 1, _maxFiles);

        _currentRuns = new int[_maxFiles - 1];
        _currentRuns[0] = 1;

        writers = new BufferedWriter[_maxFiles];
        writeIsClosed = new boolean[_maxFiles];

        int i = 0, capacity = _heap.capacity();
        for (; i < capacity && i < data.length; i++) {
            _heap.insert(data[i]);
        }

        String biggestInRun;

        for (; i < data.length; i++) {

            biggestInRun = _heap.peek();

            int compare = data[i].compareTo(biggestInRun);
            if (compare > 0) {
                putStreamRuns(_heap.replace(data[i]));
            } else if (compare < 0) {
                putStreamRuns(_heap.get());
                _heap.place(data[i]);
            } else {
                putStreamRuns(data[i]);
            }
        }

        _heap = new StringHeap(_heap.getBase());
        if (_heap.size() > 0) {
            _runs = runFunction();

            if (writers[_runs] != null)
                putStreamRuns("");

            while (_heap.size() > 0) {
                putStreamRuns(_heap.get());
            }
        }
        log("Total Runs: " + _totalRuns);

        int backup = _runs;

        while ((_runs = runFunctionDummy()) != -1) {
            putStreamRuns("");
        }
        _runs = backup;

        log("Incl. Dummy: " + _totalRuns);

        for (int g = 0; g < _maxFiles - 1; g++) {
            if (writers[g] != null) {
                try {
                    writers[g].close();
                    writeIsClosed[g] = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        merge();
    }

    private void deleteFile(String fname) {
        try {
            File f = _IOMan.getFile(fname);
            if (f.delete()) {
                debugLog(f.getName() + " is deleted!");
            } else {
                debugLog("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int numReaders(BufferedReader[] fr) {
        int out = 0;
        for (int i = 0; i < fr.length; i++) {
            if (fr[i] != null)
                out++;
        }
        return out;
    }


    private void merge() {
        KVHeap mergeHeap = new KVHeap(_maxFiles - 1);

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
                    if (i == _runs || !_IOMan.getFile("run" + i).exists())
                        continue;

                    if (fileReaders[i] == null)
                        fileReaders[i] = _IOMan.createBufferedReader("run" + i);

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

                    deleteFile("run" + i);
                    fileReaders[i] = null;

                    _totalPasses++;
                } else if (!inLine.isEmpty()) mergeHeap.insert(new KVPair<Integer, String>(i, inLine));

            }

            if (_currentStream != null)
                putStreamMerge("");

            while (mergeHeap.size() > 0) {
                KVPair<Integer, String> out = mergeHeap.peek();
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

                    deleteFile("run" + key);
                    fileReaders[key] = null;

                    _totalPasses++;
                } else {
                    if (inLine.isEmpty())
                        mergeHeap.get();
                    else
                        mergeHeap.replace(new KVPair<Integer, String>(key, inLine));
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

        log("Total Passes: " + (_totalPasses - (_maxFiles - 2)));

        if (_outputFile != null && new File(_outputFile).exists())
            deleteFile(_outputFile);

        if (_IOMan.getType() == IOManager.GZIPPED) {
            deleteFile("run" + (finalRun));
        } else {
            if (_outputFile == null) {
                try {
                    FileInputStream br = new FileInputStream(_IOMan.getDirectory() + "run" + (finalRun));

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
                File old = _IOMan.getFile("run" + finalRun);
                File output = _IOMan.getFile(_outputFile);

                if (old.renameTo(output))
                    debugLog("FINISHED");
                else
                    debugLog("Error in renaming final output");
            }

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
                writers[_runs] = _IOMan.createOutputStreamWriter("run" + _runs);
            } catch (Exception e) {
                debugLog(e.getMessage());
            }
        }
        try {
            _IOMan.write(writers[_runs], item + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putStreamMerge(String item) {
        if (_currentStream == null) {
            try {
                _currentStream = _IOMan.createOutputStreamWriter("run" + _runs);
            } catch (Exception e) {
                debugLog(e.getMessage());
            }
        }
        try {
            _IOMan.write(_currentStream, item + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int[] fibNext(int[] previousSequence) {
        int output = -1;
        for (int i = 0; i < previousSequence.length; i++) {
            if (previousSequence[i] == 0)
                output = i;
        }
        if (output == -1)
            throw new Error("No zeros in sequence? Make sure its a fibonacci sequence");

        for (int i = 0; i < _maxFiles; i++) {
            previousSequence[i] += previousSequence[previousSequence.length - 1];
        }

        previousSequence[previousSequence.length - 1] = 0;

        {
            Arrays.sort(previousSequence);
            return (previousSequence);
        }

    }

    private int[] fibSequence(int dataLength, int maxFiles) {
        int[] returnArray = new int[maxFiles];
        returnArray[maxFiles - 1] = 1;
        int output = maxFiles - 1;
        int total = 1;

        printRuns(returnArray, maxFiles, total);
        while (true) {
            for (int i = 0; i < maxFiles; i++) {
                if (i != output) {
                    returnArray[i] += returnArray[output];
                    total += returnArray[i];
                }
            }
            returnArray[output] = 0;
            printRuns(returnArray, maxFiles, total);
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

    private void printRuns(int[] runsArray, int files, int total) {
        for (int i = 0; i < files; i++) {
            debugLog(runsArray[i] + " ");
        }
        debugLog("total: " + total + " \n");
    }

    private void debugLog(String output, Object... args) {
        if (DEBUG)
            System.err.println(String.format(output, args));
    }

    private void log(String output, Object... args) {
        System.err.println(String.format(output, args));
    }
}
