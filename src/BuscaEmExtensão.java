import List.*;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BuscaEmExtensão {

    LinkedList<Nodes> graph;
    Nodes node;

    public BuscaEmExtensão( LinkedList<Nodes> graph, Nodes node) {
        this.graph = graph;
        this.node = node;
    }

    public void busca(LinkedList<Nodes> graph, Nodes node){        

        // LinkedList<Integer> color = new LinkedList<Integer>();
        // // Branco = 0
        // // Cinza = 1
        // // Preto = 2
        // LinkedList<Double> distance = new LinkedList<Double>();
        // LinkedList<Nodes> ante = new LinkedList<Nodes>();

        int [] color = new int[graph.size()];
        double [] distance = new double[graph.size()];
        Nodes [] ante = new Nodes[graph.size()];

        // índice do vérice passado como parâmetro
        int nodeIndex = graph.indexOf(node);

        for(int i = 0; i < graph.size(); i++){
            color[i] = 0;
            distance[i] = -1;
        }

        color[nodeIndex] = 1;
        distance[nodeIndex] = 0;
        
        PriorityQueue <Nodes> queue = new PriorityQueue<Nodes>();
        queue.add(node);

        while( !queue.isEmpty() ){
            Nodes u = queue.poll();
            System.out.println("Nó no topo da fila : " + u.getId());

            for( Nodes v : u.getAdjacent()){
                System.out.println("Id do Adjacente : " + v.getId());
                int vIndex = v.getId()-1;

                if ( color[vIndex] == 0 ){
                    color[vIndex] = 1;   
                    ante[vIndex] = u;

                    // Se tiver inicializado como -1, ao adicionar vai pra 1, se não, só adicina mais 1
                    if ( distance[vIndex] == -1 ){
                        distance[vIndex] = distance[u.getId()-1] + 2;
                    } else {
                        distance[vIndex] = distance[u.getId()-1] + 1;
                    }
                    
                    queue.add(v);

                    System.out.println("V.getName : " + v.getId());
                    System.out.println("Topo da fila : " + queue.peek());
                }
            }

            
            color[u.getId()-1] = 2;
        }

        System.out.println("");
        
        // Printar o Algoritmo de Busca do vértice passado como parâmetro
        for(int k = 0; k < graph.size(); k++){
            System.out.println(" Color : " + color[k]);
            //System.out.println(" Antecessor : " + ante[k].getName());
            System.out.println(" Distancia : " + distance[k]);
        }
        
    }
}

// BFS (grafo G, vértice s)
// para cada vértice u
// cor[u] = BRANCO; d[u] = ; ante[u] = NIL;
// cor[s] = CINZA; d[s] = 0;
// Enfileira(Q, s);
// While ( ! EstahVazia(Q) )
// u = Desenfileira(Q);
// para cada v na lista Adj[u]
// if (cor[v] == BRANCO)
// cor[v] = CINZA; d[v] = d[u]+1; ante[v] = u;
// Enfileira (v, Q);

// cor[u] = PRETO;


            // do{
            //     i = 0;
                
            //     System.out.println(u.getName());
            //     System.out.println(u.getAdjacent()[i].getName() ) ;
            //     Nodes v = graph.get(uIndex).getAdjacent()[i];

            //     System.out.println(v.getName());
            //     int vIndex = graph.indexOf(v);

            //     System.out.println("vindex : " + vIndex);
            //     System.out.println(color[vIndex]);
                
            //     if(color[vIndex] == 0){
            //         System.out.println("skip");
            //         color[vIndex] = 1;
            //         distance[vIndex] = distance[uIndex] + graph.get(uIndex).getWeight();
            //         ante[vIndex] = u;
            //         queue.add(v);
            //     }

            //     i++;
            // }while(i < (graph.get(uIndex).getAdjacent().length - 1) );