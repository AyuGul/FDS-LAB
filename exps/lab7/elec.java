import java.io.*;
import java.util.*;


public class elec {
    private static void printTreeWithDepth(MyTree<String> tree, Position<String> node, int depth, String parentIndex) {
        if (node == null) return;
        StringBuilder depthPrefix = new StringBuilder();
        depthPrefix.append("    ".repeat(depth));
        String currentIndex = parentIndex.isEmpty() ? "" : parentIndex + ".";
        if (!Objects.equals(node.getElement(), "")){
            String index ="";
            if(currentIndex!=""){
                index = currentIndex.substring(0,currentIndex.length()-1)+ " ";
            }
            System.out.println(depthPrefix + index + node.getElement());
        }
        else
            return;
        Iterator<Position<String>> children = tree.children(node).iterator();
        int i = 0;
        while (children.hasNext()) {
            String childIndex = currentIndex + (i + 1);
            printTreeWithDepth(tree, children.next(), depth + 1, childIndex);
            i++;
        }
    }
    public static void main(String[] args) {
        String data = "";
        try {
            FileReader fr = new FileReader("1.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            try {
                StringBuilder dataBuilder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    dataBuilder.append(line).append("\n");
                }
                data = dataBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int i = 0;
        while (data.charAt(i) != '\n') i++;
        MyTree<String> tree = new MyTree<>(data.substring(0, i));

        String rest = data.substring(i + 1);
        String[] lines = rest.split("\n");

        Queue<Position<String>> queue = new LinkedList<>();
        queue.add(tree.root());

        for (int j = 0; j < lines.length; j++) {
            String[] allChildren = lines[j].split(" ");

            int count = 0;
            int n = queue.size();
            while (count < allChildren.length) {
                Position<String> curr = queue.poll();
                if (!allChildren[count].equals(" ")) {
                    String[] children = allChildren[count].split(",");
                    for (String string : children) {
                        Position<String> child = tree.addChild(curr, string);
                        queue.add(child);
                    }
                }

                count++;
            }
            while (count < n) {
                queue.poll();
                count++;
            }
        }
        printTreeWithDepth(tree, tree.root(), 0, "");
    }
}
