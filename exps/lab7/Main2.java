import java.io.File;
import java.io.IOException;
import java.nio.file.*;

class Node{
    private Path path;
    public Path getPath() {
        return path;
    }
    public void setPath(Path path) {
        this.path = path;
    }
    public Node(String name, int type, String date,Path path) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.path = path;
    }
    public Node(String name, int type,int size, String date) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String name;
    private int type;
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    private int size;
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    private String date;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public boolean isDirectory(){
        return this.getType()==0;
    }
}

public class Main2 {
    public static int size =0;
    public static void main(String[] args) {
        // Specify the path to the directory or file
        String path = "C:\\Users\\ayush\\Desktop\\C++";
        Path rootPath = Paths.get(path);
        MyTree<Node> tree = display(rootPath);

        // Display the tree
        displaySubtree(tree, tree.root().getElement());
        System.out.println("Total size: "+size);
        System.out.println();
    }

    private static Node createNode(Path filePath) {
        
        String fileName = filePath.getFileName().toString();
        int fileType = Files.isDirectory(filePath) ? 0 : 1;
        int fileSize = 0;
        try {
            fileSize = fileType == 1 ? (int) Files.size(filePath) : 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String lastModifiedTime = "";
        try {
            lastModifiedTime = Files.getLastModifiedTime(filePath).toString().substring(0, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileType == 0) {
            return new Node(fileName, fileType, lastModifiedTime,filePath);
        } else {
            return new Node(fileName, fileType, fileSize, lastModifiedTime);
        }
    }
    private static Position<Node> findPosition(MyTree<Node> tree, Node element) {
        for (Position<Node> position : tree.positions()) {
            if (position.getElement().equals(element)) {
                return position;
            }
        }
        return null;
    }
    public static void displaySubtree(MyTree<Node> tree, Node element) {
        Position<Node> position = findPosition(tree, element);
        if(position.equals(tree.root())){
            System.out.println("The root folder is: "+ element.getName());
            System.out.println("Contents are");
        }
        else{
        String dir = "";
        if(element.isDirectory()){
            dir="    <DIR> ";
            // Specify the path to the directory or file
            String path = element.getPath().toString();
            Path rootPath = Paths.get(path);
            MyTree<Node> tree1 = display(rootPath);

            // Display the tree
            displaySubtree(tree1, tree1.root().getElement());
            System.out.println();
        }
        else{
            dir="          ";
            System.out.println(element.getDate() + "  " + dir + element.getSize() + "  " +element.getName());
            size+=element.getSize();
        }
    }


        for (Position<Node> child : tree.children(position)) {
            displaySubtree(tree, child.getElement());
        }
    }
    public static MyTree<Node> display(Path path){
        File f = new File(path.toString());
        Node rootNode = createNode(path);
        MyTree<Node> tree = new MyTree<>(rootNode);
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            Path filePath = Paths.get(files[i].getAbsolutePath());
            Node fileNode = createNode(filePath);
            tree.addChild(tree.root(),fileNode);
        }
        return tree;
    }
}
