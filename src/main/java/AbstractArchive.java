import java.io.File;
import java.util.List;

abstract public class AbstractArchive {
    public abstract List<FileDecorator> createDecorator(File file, EXTENSIONS ext);
}
