import com.github.junrar.rarfile.FileHeader;

public class FileDecorator {

    private String fileName;
    private EXTENSIONS ext;
    private boolean isDirectory;
    private long size;

    FileDecorator(FileHeader header, EXTENSIONS ext){
        fileName = header.getFileNameString();
        size = header.getDataSize();
        this.ext = ext;
        isDirectory = header.isDirectory();
    }

    FileDecorator(String str, Boolean isDirectory, EXTENSIONS ext, long size){
        this.size = size;
        this.ext = ext;
        fileName = str;
        this.isDirectory = isDirectory;
    }

    public long getSize() {return size;};

    public EXTENSIONS getExt() {return ext;};

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getFileName() {
        return fileName;
    }

}
