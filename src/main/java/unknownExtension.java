public class unknownExtension extends Exception{
    public unknownExtension(String name){
        System.out.println("File extension is incorrect " + name.substring(name.lastIndexOf('/') + 1));
    }
}
