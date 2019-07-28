public class FileInfo {
    private long size;
    private String name;

    FileInfo(long size, String name){
        this.size = size;
        this.name = name;
    }

    public long getSize() {
        return size;
    }


    public String getName() {
        return name;
    }

}
