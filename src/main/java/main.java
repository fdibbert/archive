import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

enum EXTENSIONS {
    RAR,
    ZIP
}

public class main {


    public static EXTENSIONS parseName(File file) throws unknownExtension {
        String str = file.getName();
        if (str.lastIndexOf(".") == -1) throw new unknownExtension(file.getAbsolutePath());
        str = str.substring(str.lastIndexOf('.'));
        if (".rar".equals(str)) return EXTENSIONS.RAR;
        else if (".zip".equals(str)) return EXTENSIONS.ZIP;
        else throw new unknownExtension(file.getName());
    }

    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Incorrect number of parametrs");
            return ;
        }
        File file = new File(args[0]);//args[0]
        List<FileDecorator> list = new ArrayList<>();
        EXTENSIONS ext;
        try {
            ext = parseName(file);
        }
        catch(unknownExtension e){
            return ;
        }
        AbstractArchive archive = null;
        if (ext.equals(EXTENSIONS.RAR)) archive = new rarArchive();
        else archive = new zipArchive();
        list = archive.createDecorator(file, ext);
        MTree tree = new MTree("Maintainer");
        Iterator<FileDecorator> iter = list.listIterator();
        while(iter.hasNext()) {
            FileDecorator check = iter.next();
            tree.add(check, tree);
        }
        tree.printTree(tree, 0);
    }
}
