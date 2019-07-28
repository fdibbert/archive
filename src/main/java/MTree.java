import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MTree {
    public boolean sizeSort;
    public String ID;
    public ArrayList<FileInfo> files;
    public HashMap<String, MTree> tree;

    public MTree(String folder) {
        ID = folder;
        files = new ArrayList<>();
        tree = new HashMap<>();
    }

    public void add(FileDecorator file, MTree tree){
        String[] folders;
        if (file.getExt().equals(EXTENSIONS.RAR))
            folders = file.getFileName().split("\\\\");
        else
            folders = file.getFileName().split("/");
        addRecurs(file, folders, 0, tree);
    }

    private void addRecurs(FileDecorator file, String[] folders, int depth, MTree point){
        if (depth == folders.length - 1 && !file.isDirectory()){
            point.files.add(new FileInfo(file.getSize(), file.getFileName()));
        }
        else if (depth < folders.length && point.tree.containsKey(folders[depth])){
            point = point.tree.get(folders[depth]);
            addRecurs(file, folders, ++depth, point);
        }
        else if (depth < folders.length && !point.tree.containsKey(folders[depth])){
            point.tree.put(folders[depth],
                            new MTree(folders[depth]));
            point = point.tree.get(folders[depth]);
            addRecurs(file, folders, ++depth, point);
        }
    }



    public void printTree(MTree tree, int depth){
        tree.files.sort((p1, p2) -> {
            int p1length = p1.getName().lastIndexOf('.');
            int p2length = p2.getName().lastIndexOf('.');
            if (p1length == -1) return -1;
            if (p2length == -1) return 1;
            int i = p1.getName().substring(p1length)
                        .compareToIgnoreCase(p2.getName().substring(p2length));
            if (i != 0) return i;
            if (!main.getSort()) return p1.getName().compareToIgnoreCase(p2.getName());
            else return (int)(p2.getSize() - p1.getSize());
        });
        for (int i = 0; i < tree.files.size(); i++){
            for (int j = 0; j < depth; j++){
                System.out.print("\t");
                if (j == 1) System.out.print(" ");
            }
            System.out.println(tree.files.get(i).getName() + "  " + tree.files.get(i).getSize() + " bytes");
        }
        if (!tree.tree.isEmpty()){
            for(Map.Entry<String, MTree> pair: tree.tree.entrySet()){
                MTree newTree = pair.getValue();
                for (int j = 0; j < depth; j++){
                    System.out.print("\t");
                }
                System.out.println(pair.getKey());
                printTree(newTree, depth + 1);
            }
        }
    }
}
