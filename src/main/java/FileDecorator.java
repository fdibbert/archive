import com.github.junrar.rarfile.FileHeader;

public class FileDecorator {

    private String fileName;
    private EXTENSIONS ext;
    private boolean isDirectory;

    FileDecorator(FileHeader header, EXTENSIONS ext){
        fileName = header.getFileNameString();
        this.ext = ext;
        isDirectory = header.isDirectory();
    }

    FileDecorator(String str, Boolean isDirectory, EXTENSIONS ext){
        this.ext = ext;
        fileName = str;
        this.isDirectory = isDirectory;
    }

    public EXTENSIONS getExt() {return ext;};

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getFileName() {
        return fileName;
    }

}
