import List.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ArrayList;
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
                int weight = Integer.parseInt(split[3]);
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

        int tamanho = listNode.size();
        ArrayList<ArrayList<AdjListNode> > graph = new ArrayList<>();
        for(int i = 0; i < tamanho; i++){
            graph.add(new ArrayList<>());
        }

        setAdjacentNodes(listNode, listEdges, graph);
        int [] respostaDoDijkstra = dijkstra(tamanho, graph, 0);
        for(int i = 0; i < tamanho; i++){
            if(respostaDoDijkstra[i] == Integer.MAX_VALUE){
                System.out.println("Distancia do vertice " + listNode.get(i).getId() + " para o vertice 1: INFINITO (inacessível)" );
            } else if( listNode.get(i).getId() == 1){
                System.out.println("Distancia do vertice " + listNode.get(i).getId() + " para o vertice 1: 0"   );
            } else {
                System.out.println("Distancia do vertice " + listNode.get(i).getId() + " para o vertice 1: " + ( respostaDoDijkstra[i] + Integer.MAX_VALUE + 2  )  );
            }
        }
        //printAdjacents(listNode);
        System.out.println("");
        
    }
    public static void setAdjacentNodes(LinkedList<Nodes> listNode, LinkedList<Edges> listEdges, ArrayList<ArrayList<AdjListNode> > graph ){
        for(int i = 0; i < listNode.size(); i++){
            Nodes node = listNode.get(i);

            for(int j = 0; j < listEdges.size(); j++){
                Edges edge = listEdges.get(j);
                
                if(edge.getOriginNode() == node.getId()){
                    int id = edge.getDestinationNode();
                    int weigth = edge.getWeight();
                    graph.get(i).add(new AdjListNode( id , weigth));

                } else if(edge.getDestinationNode() == node.getId()){
                    int id = edge.getOriginNode();
                    int weigth = edge.getWeight();
                    graph.get(i).add(new AdjListNode( id , weigth));
                }


            }
        }
    }

    // public static void printAdjacents(LinkedList<Nodes> node){
    //     for(int i = 0; i < node.size(); i++) {
    //         Nodes n = (Nodes)node.get(i);
    //         System.out.println(" ");
    //         System.out.println("O vértice " + n.getName() + " possui os seguintes vértices adjacentes: ");      
    //         for(int j = 0; j < n.getAdjacent().length; j++) {
    //             System.out.println("Vértice adjacente: " + n.getAdjacent()[j].getId() + " Peso: " + n.getAdjacent()[j].getWeight());
    //         }
    //     }
    // }

    public static int[] dijkstra(
        int V, ArrayList<ArrayList<AdjListNode> > graph,
        int source)
    {
        source = 1;
        int[] distance = new int[V];
        for (int i = 0; i < V; i++)
            distance[i] = Integer.MAX_VALUE;
        distance[source] = 0;
 
        PriorityQueue<AdjListNode> pq = new PriorityQueue<>((v1, v2) -> v1.getWeight() - v2.getWeight());
        pq.add(new AdjListNode(source, 0));
 
        while (pq.size() > 0) {
            AdjListNode current = pq.poll();
            ArrayList<AdjListNode> listaDoVertice = graph.get(current.getVertex()-1);

            for (AdjListNode n : listaDoVertice) {

                if ( distance[current.getVertex()-1] + n.getWeight() < distance[n.getVertex() - 1]) { 

                    distance[n.getVertex() - 1] = n.getWeight() + distance[current.getVertex()-1];
                    pq.add(new AdjListNode( n.getVertex(), distance[n.getVertex()-1 ]));

                }
            }

        }

        return distance;
    }
}
