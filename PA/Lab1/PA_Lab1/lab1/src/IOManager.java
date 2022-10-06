import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class IOManager {
    public static int GZIPPED = 1;
    public static int NORMAL = 0;

    private int _type;
    private String _charset;
    private int _writeBufferSize, _readBufferSize;
    private boolean _autoFlush;
    private String _tempDirectory;

    public IOManager(int type, String charset, int writeBufferSize, int readBufferSize, boolean autoFlush, String tempDirectory) {
        _type = type;
        _charset = charset;
        _writeBufferSize = writeBufferSize;
        _readBufferSize = readBufferSize;
        _autoFlush = autoFlush;
        _tempDirectory = tempDirectory.isEmpty() ? "" : tempDirectory + File.separator;
    }

    public BufferedWriter createOutputStreamWriter(String fname) throws Exception {
        if (_type == 0)
            return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(_tempDirectory + fname)), _charset), _writeBufferSize);
        else
            return new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(Files.newOutputStream(Paths.get(_tempDirectory + fname)), _writeBufferSize, false), _charset), _writeBufferSize);
    }

    public BufferedReader createBufferedReader(String fname) throws Exception {
        if (_type == 0)
            return new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(_tempDirectory + fname)), _charset), _readBufferSize);
        else
            return new BufferedReader(new InputStreamReader(new GZIPInputStream(Files.newInputStream(Paths.get(_tempDirectory + fname)), _readBufferSize), _charset), _readBufferSize);
    }

    public void write(BufferedWriter writer, String item) throws IOException {
        writer.write(item);
        if (_autoFlush)
            writer.flush();
    }

    public File getFile(String fname) {
        return new File(_tempDirectory + fname);
    }

    public int getType() {
        return _type;
    }

    public String getDirectory() {
        return _tempDirectory;
    }
}
