import List.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        File file;
        Scanner reader;
        int resp = 1;
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

        // Menu
        while(resp != 0){
            reader = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("Escolha o método que quer visualizar : ");
            System.out.println("1 - Dijkstra");
            System.out.println("2 - Busca em Profundidade");
            System.out.println("3 - Exibir Grafo");
            System.out.println("0 - Sair");
            resp = reader.nextInt();
            int source;
            System.out.println(" ");
            switch(resp){
                case 1:

                    System.out.println("Escolha o nó de origem : ");
                    source = reader.nextInt();
                    // Get Dijkstra Result
                    ResDijkstra resultDijkstra = dijkstra(tamanho, graph, source);
                    int[] distanceDijkstra = resultDijkstra.getDistance();
                    int[] antDijkstra = resultDijkstra.getAnt();
                    
                    /////////////////////////////////////////////////////////////////////////////////////////
                    ////////////////////////// PRINT DIJKSTRA RESULT/////////////////////////////////////////
                    /////////////////////////////////////////////////////////////////////////////////////////

                    for(int i = 0; i < tamanho; i++){
                        System.out.println("");
                        // Se for um vertice inacessivel
                        if(distanceDijkstra[i] == Integer.MAX_VALUE){
                            System.out.println("Distancia do vertice " + listNode.get(i).getId() + " para" +
                            " o vertice " +source+": INFINITO (inacessível)" );
                            System.out.println("Caminho: " + "Não existe caminho");
                        }
                        // Se for i for source
                        else if( listNode.get(i).getId() == source){
                            System.out.println("Distancia do vertice " + listNode.get(i).getId() + " para " +
                            "o vertice "+source+ " : 0 " );
                        }
                        // Se i for seguinte a source
                        else if(listNode.get(i).getId() == source+1){
                            System.out.println("Distancia do vertice " + listNode.get(i).getId() + " para " +
                            "o vertice "+source+": " + (distanceDijkstra[i] + Integer.MAX_VALUE ) );
                            System.out.println("Caminho: " + antDijkstra[i]);
                        }
                        // Caso geral
                        else {
                            System.out.println("Distancia do vertice " + listNode.get(i).getId() + " para " +
                            "o vertice "+source+": " + ( distanceDijkstra[i] + Integer.MAX_VALUE + 2 ) );
                            System.out.println("Caminho: " + antDijkstra[i]);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Escolha o nó de origem : ");
                    source = reader.nextInt();

                    //Get Largura Result
                    ResLargura resultWidth = buscaEmLargura(tamanho, graph, source);
                    int [] distanceWidth = resultWidth.getDistance();
                    int [] antWidth = resultWidth.getAnt();

                    ////////////////////////////////////////////////////////////////////////////////////////////
                    // ////////////////////////// PRINT largura RESULT /////////////////////////////////////////
                    // /////////////////////////////////////////////////////////////////////////////////////////
                    
                    for(int i = 0; i < tamanho; i++){
                        System.out.println("");
                        // Se for um vertice inacessivel
                        if(distanceWidth[i] == Integer.MAX_VALUE){
                            System.out.println("Distancia do vertice " + listNode.get(i).getId() + " para o vertice " +source+": INFINITO (inacessível)" );
                            System.out.println("Caminho: " + "Não existe caminho");
                        }
                        // Se for i for source
                        else if( listNode.get(i).getId() == source){
                            System.out.println(" ");
                        } 
                        // Caso Geral
                        else {
                            System.out.println("Distancia do vertice " + listNode.get(i).getId() + " para o vertice " +source+ " : " + distanceWidth[i] );
                            System.out.println("Caminho: " + antWidth[i]);
                        }
                    }
                    break;
                case 3: printAdjacents(graph); break;
                case 0: break;
                default: System.out.println("Opção inválida");
            
            }

        }

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

    public static void printAdjacents(ArrayList<ArrayList<AdjListNode>> graph){
        System.out.println("(ID, PesoDaLigação)");
        // Percorrer vértices
        for (int i = 0; i < graph.size(); i++){
            System.out.print("Vertice " + i + " possui os adjacentes : ");
            // Percorrer adjacentes
            for (int j = 0; j < graph.get(i).size(); j++){
                System.out.print("(" + graph.get(i).get(j).getVertex() + "," + graph.get(i).get(j).getWeight() + ") ");
            }
            System.out.println("");
        }
        // for(int i = 0; i < node.size(); i++) {
        //     Nodes n = (Nodes)node.get(i);
        //     System.out.println(" ");
        //     System.out.println("O vértice " + n.getName() + " possui os seguintes vértices adjacentes: ");      
        //     for(int j = 0; j < n.getAdjacent().length; j++) {
        //         System.out.println("Vértice adjacente: " + n.getAdjacent()[j].getId() + " Peso: " + n.getAdjacent()[j].getWeight());
        //     }
        // }
    }

    public static ResDijkstra dijkstra(
        int V, ArrayList<ArrayList<AdjListNode> > graph,
        int source)
    {
        int[] distance = new int[V];
        int[] ant = new int[V];

        for (int i = 0; i < V; i++) distance[i] = Integer.MAX_VALUE;
        
 
        PriorityQueue<AdjListNode> pq = new PriorityQueue<>((v1, v2) -> v1.getWeight() - v2.getWeight());
        pq.add(new AdjListNode(source, 0));
 
        while (!pq.isEmpty()) {

            AdjListNode current = pq.poll();
            ArrayList<AdjListNode> listaDoVertice = graph.get(current.getVertex()-1);

            for (AdjListNode n : listaDoVertice) {

                if ( distance[current.getVertex()-1] + n.getWeight() < distance[n.getVertex() - 1]) { 

                    //adicionar caminho de vértices
                    ant[n.getVertex()-1] = current.getVertex();
                    distance[n.getVertex() - 1] = n.getWeight() + distance[current.getVertex()-1];
                    pq.add(new AdjListNode( n.getVertex(), distance[n.getVertex()-1 ]));

                }
            }

        }
        distance[source-1] = 0;
        ant[source-1] = 0;
        ResDijkstra res = new ResDijkstra(distance, ant);
        return res;
    }
    
    public static ResLargura buscaEmLargura(
        int V, ArrayList<ArrayList<AdjListNode> > graph,
        int source){

            int[] distance = new int[V];
            int[] ant = new int[V];
            int[] color = new int[V];
            // COLOR
            // 1 = BRANCO
            // 2 = CINZA
            // 3 = PRETO
            
            // Inicializar cor e distancia
            for (int i = 0; i < V; i++) {
                distance[i] = -1;
                color[i] = 1;
                ant[i] = -1;
            }
            distance[source-1] = 0;
            color[source-1] = 0;

            ArrayList<AdjListNode> sourceNode = graph.get(source-1);

            Queue<ArrayList<AdjListNode>> q = new LinkedList<ArrayList<AdjListNode>>();
            q.add(sourceNode);

            // enquanto a fila nao esta vazia
            while(!q.isEmpty()){
                ArrayList<AdjListNode> n = q.poll();
                int indexN = graph.indexOf(n);

                // Para todo v em adj[n]
                for( AdjListNode v : n){
                    int indexV = v.getVertex() - 1;
                    // Se for branco
                    if(color[indexV] == 1){
                        //Adiciona v no fim da fila
                        q.add(graph.get(indexV));
                        color[indexV] = 2;
                        distance[indexV] = distance[indexN] + 1;
                        ant[indexV] = indexN + 1;
                    }

                }
                color[indexN] = 3;
            }

            
            distance[source-1] = 0;
            ant[source-1] = 0;
            ResLargura res = new ResLargura(distance, ant);
            return res;
        }

}
 