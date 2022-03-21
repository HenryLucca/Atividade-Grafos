import List.*;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        File file;
        Scanner reader;
        // Lists
        LinkedList<Nodes> listNode = new LinkedList<Nodes>();
        LinkedList<Edges> listEdges = new LinkedList<Edges>();
        // Read the file, Edges
        try {
            file = new File("src/File/arestas.txt");
            reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] split = data.split(",");
                int id = Integer.parseInt(split[0]);
                int originNode = Integer.parseInt(split[1]);
                int destinationNode = Integer.parseInt(split[2]);
                double weight = Double.parseDouble(split[3]);
                Edges edge = new Edges(id, originNode, destinationNode, weight);
                listEdges.add(edge);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occured reading 'arestas.txt'");
            e.printStackTrace();
        }
        // Read the file, Nodes
        try{
            file = new File("src/File/vertices.txt");
            reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] split = data.split(",");
                int id = Integer.parseInt(split[0]);
                String name = split[1];
                Nodes node = new Nodes(id, name );
                listNode.add(node);
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occured reading 'vertices.txt'");
            e.printStackTrace();
        }
        adjacentNodes(listNode, listEdges);
        
    }
    public static void adjacentNodes(LinkedList<Nodes> node, LinkedList<Edges> edge){
        // Parse Nodes
        for(int i = 0; i < node.size(); i++) {
            Nodes n = (Nodes)node.get(i);
            // Parse Edges
            for(int j = 0; j < edge.size(); j++) {
                Edges e = (Edges)edge.get(j);
                // If the id is equal to the current node
                if(e.getId() == n.getId()) {
                    System.out.println("O nó " + n.getName() + " tem origem no nó " + e.getOriginNode() + 
                    " e destino no nó " + e.getDestinationNode() + " e tem peso " + e.getWeight());
                }
            }
        }
    }
}
