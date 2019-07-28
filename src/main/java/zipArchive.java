import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class zipArchive extends AbstractArchive {

    zipArchive(){

    }

    @Override
    public List<FileDecorator> createDecorator(File file, EXTENSIONS ext) {
        List<FileDecorator> list = new ArrayList<>();
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(file)))
        {
            ZipEntry entry;
            while((entry=zin.getNextEntry())!=null){
                list.add(new FileDecorator(entry.getName(), entry.isDirectory(), ext, entry.getCompressedSize()));
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        return list;
    }

}
