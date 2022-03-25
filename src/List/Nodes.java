package List;
import Adjacent;

public class Nodes {
    int id;
    String name;
    Adjacent[0] adjacent;
    public Nodes(int id, String name){
        this.id = id;
        this.name = name;
    }
    // public Nodes(int[] adjacent){
    //     this.adjacent = new Adjacent[adjacent.length];
    //     for(int i = 0; i < adjacent.length; i++){
    //         this.adjacent[i] = new Adjacent(adjacent[i], 1);
    //     }
    // }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public void addAdjacent(int id, int weight){
        Adjacent[] newAdjacent = new Adjacent[this.adjacent.length + 1];
        for(int i = 0; i < this.adjacent.length; i++){
            newAdjacent[i] = this.adjacent[i];
        }
        newAdjacent[this.adjacent.length] = new Adjacent(id, weight);
        this.adjacent = newAdjacent;
    }
}
