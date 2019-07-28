import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

enum EXTENSIONS {
    RAR,
    ZIP
}

public class main {

    private static boolean sort;

    public static boolean getSort(){ return sort;}

    public static EXTENSIONS parseName(File file) throws unknownExtension {
        String str = file.getName();
        if (str.lastIndexOf(".") == -1) throw new unknownExtension(file.getAbsolutePath());
        str = str.substring(str.lastIndexOf('.'));
        if (str.endsWith(".rar")) return EXTENSIONS.RAR;
        else if (str.endsWith(".zip")) return EXTENSIONS.ZIP;
        else throw new unknownExtension(file.getName());
    }

    public static void main(String[] args) {
        if (args.length < 1 && args.length > 2){
            System.out.println("Incorrect number of parametrs");
            return ;
        }
        if (args.length == 2) sort = "-s".equals(args[1]);
        if (sort == false && args.length == 2) {
            System.out.println("Unknown parametr in arg");
            return ;
        }
        File file = new File(args[0]);//args[0]
        EXTENSIONS ext;
        try {
            ext = parseName(file);
        }
        catch(unknownExtension e){
            return ;
        }
        List<FileDecorator> list = new ArrayList<>();
        AbstractArchive archive;
        if (ext.equals(EXTENSIONS.RAR)) archive = new rarArchive();
        else archive = new zipArchive();
        list = archive.createDecorator(file, ext);
        MTree tree = new MTree("Maintainer");
        Iterator<FileDecorator> iter = list.listIterator();
        while(iter.hasNext()) {
            tree.add(iter.next(), tree);
        }
        tree.printTree(tree, 0);
    }
}
