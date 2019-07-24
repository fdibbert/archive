import com.github.junrar.Archive;
import com.github.junrar.impl.FileVolumeManager;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class rarArchive extends AbstractArchive {

    rarArchive() {

    }

    @Override
    public List<FileDecorator> createDecorator(File file, EXTENSIONS ext) {
        List<FileDecorator> list = new ArrayList<>();
        try (Archive archive = new Archive(new FileVolumeManager(file))) {
            if (archive != null) {
                FileHeader fh = archive.nextFileHeader();
                while (fh != null) {
                        list.add(new FileDecorator(fh, ext));
                        fh = archive.nextFileHeader();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }
}