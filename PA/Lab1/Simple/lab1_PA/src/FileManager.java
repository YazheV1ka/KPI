import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    private String _charset;
    private int _writeBufferSize, _readBufferSize;
    private boolean _autoFlush;
    private String _tempDirectory;

    public FileManager( String charset, int writeBufferSize, int readBufferSize, boolean autoFlush, String tempDirectory) {
        _charset = charset;
        _writeBufferSize = writeBufferSize;
        _readBufferSize = readBufferSize;
        _autoFlush = autoFlush;
        _tempDirectory = tempDirectory.isEmpty() ? "" : tempDirectory + File.separator;
    }

    public BufferedWriter createOutputStreamWriter(String fileName) throws Exception {
        return new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get(_tempDirectory + fileName)), _charset), _writeBufferSize);
    }

    public BufferedReader createBufferedReader(String fileName) throws Exception {
        return new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(_tempDirectory + fileName)), _charset), _readBufferSize);
    }

    public void write(BufferedWriter writer, String item) throws IOException {
        writer.write(item);
        if (_autoFlush)
            writer.flush();
    }

    public File getFile(String fileName) {
        return new File(_tempDirectory + fileName);
    }

    public String getDirectory() {
        return _tempDirectory;
    }
}
