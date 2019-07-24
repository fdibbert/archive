public class unknownExtension extends Exception{
    public unknownExtension(String name){
        System.out.println("File extension or path is incorrect " + name);
    }
}
