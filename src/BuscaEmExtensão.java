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

        int nodeIndex = graph.indexOf(node);

        // LinkedList<Integer> color = new LinkedList<Integer>();
        // // Branco = 0
        // // Cinza = 1
        // // Preto = 2
        // LinkedList<Double> distance = new LinkedList<Double>();
        // LinkedList<Nodes> ante = new LinkedList<Nodes>();
        int [] color = new int[graph.size()];
        double [] distance = new double[graph.size()];
        Nodes [] ante = new Nodes[graph.size()];


        for(int i = 0; i < graph.size(); i++){
            color[i] = 0;
            distance[i] = Double.MAX_VALUE;
            ante[i] = null;
        }

        color[nodeIndex] = 1;
        distance[nodeIndex] = 0;
        
        PriorityQueue <Nodes> queue = new PriorityQueue<Nodes>();
        queue.add(node);

    //     cor[] : guarda a cor de cada vértice

    //      d[] : guarda o comprimento (distância) do
    //      caminho desde s até um vértice qualquer
       
    //      ante[]: guarda o antecessor de cada vértice,
    //      no caminho de s até ele
       
    //      Ou seja, ante[u] guarda o antecessor de u no
    //      caminho

        while( !queue.isEmpty() ){

            System.out.println("Entrei no While");

            // u : vértice atual
            // uIndex : índice do vértice atual
            Nodes u = queue.poll();
            int uIndex = graph.indexOf(u);
            System.out.println("uIndex: " + uIndex);

            System.out.println("print [ adjacentes de U.lenght - 1 ]  : " + (graph.get(uIndex).getAdjacent().length -1));
            System.out.println(" ");

            for(int i = 0; i < u.getAdjacent().length; i++){
                
                // v : vértice adjacente de u
                // vIndex : índice do vértice adjacente de u
                Nodes v = graph.get(uIndex).getAdjacent()[i];
                System.out.println("Vértice adjacente: " + v.getId());

                int vIndex = graph.indexOf(v);

                System.out.println("vIndex color : " + color[vIndex] );

                if(color[vIndex] == 0){
                    System.out.println("skip");
                    color[vIndex] = 1;
                    distance[vIndex] = distance[uIndex] + graph.get(uIndex).getWeight();
                    ante[vIndex] = u;
                    queue.add(v);
                }

            }
            

            color[uIndex] = 2;
        }
        
        for(int i = 0; i < graph.size(); i++){
            System.out.println("Indice : " + i);
            System.out.println("Cor indice : " + color[i]);
            System.out.println("Distância indice : " + distance[i]);
            System.out.println("Antecessor indice : " + ante[i]);
            System.out.println(" ");
        }
    }
}
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