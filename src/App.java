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
        //printAdjacents(listNode);
        System.out.println("");
        BuscaEmExtensão busca = new BuscaEmExtensão(listNode, listNode.get(0));
        busca.busca(listNode, listNode.get(0));
        
    }
    
    public static void adjacentNodes(LinkedList<Nodes> listNode, LinkedList<Edges> listEdges){
        for(int i = 0; i < listNode.size(); i++){
            Nodes node = listNode.get(i);
            for(int j = 0; j < listEdges.size(); j++){
                Edges edge = listEdges.get(j);
                if(edge.getOriginNode() == node.getId()){
                    node.addAdjacent(edge.getDestinationNode(), edge.getWeight());
                } else if(edge.getDestinationNode() == node.getId()){
                    node.addAdjacent(edge.getOriginNode(), edge.getWeight());
                }
            }
        }
    }

    public static void printAdjacents(LinkedList<Nodes> node){
        for(int i = 0; i < node.size(); i++) {
            Nodes n = (Nodes)node.get(i);
            System.out.println(" ");
            System.out.println("O vértice " + n.getName() + " possui os seguintes vértices adjacentes: ");      
            for(int j = 0; j < n.getAdjacent().length; j++) {
                Nodes adjacentNode = n.getAdjacent()[j];
                System.out.println("Vértice adjacente: " + adjacentNode.getId() + " Peso: " + adjacentNode.getWeight());
            }
        }
    }

    
}
