// import List.*;

// import java.util.ArrayList;
// import java.util.LinkedList;
// import java.util.PriorityQueue;

// public class Dijkstra {
//     public static int[] busca(int V, ArrayList<Nodes> graph, int source) {
//         int[] distance = new int[V];
//         for (int i = 0; i < V; i++) {
//             distance[i] = Integer.MAX_VALUE;
//         }

//         PriorityQueue<Nodes> pq = new PriorityQueue<>((v1, v2) -> v1.getWeight() - v2.getWeight());
//         pq.add(new Nodes(source, 0));

//         while (pq.size() > 0) {
//             Nodes current = pq.poll();

//             for ( Nodes n : graph.get(current.getId()-1) ) {

//                 if ((distance[current.getId()] + n.getWeight()) < distance[n.getId()]) {
//                     distance[n.getId()] = n.getWeight() + distance[current.getId()];
//                     pq.add(new Nodes(n.getId(), distance[n.getId()])   );
//                 }
//             }
//         }
//         // If you want to calculate distance from source to
//         // a particular target, you can return
//         // distance[target]
//         return distance;
//     }
// }
