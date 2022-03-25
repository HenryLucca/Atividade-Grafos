package List;

public class Nodes {
    int id;
    String name;
    Adjacent[] adj;
    public Nodes(int id, String name){
        this.id = id;
        this.name = name;
        this.adj = new Adjacent[0];
    }
    // public Nodes(int[] adj){
    //     this.adj = new Adjacent[adj.length];
    //     for(int i = 0; i < adj.length; i++){
    //         this.adj[i] = new Adjacent(adj[i], 1);
    //     }
    // }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public Adjacent[] getAdjacent(){
        return this.adj;
    }
    public void addAdjacent(int id, double weight){
        Adjacent[] newAdjacent = new Adjacent[this.adj.length + 1];
        for(int i = 0; i < this.adj.length; i++){
            newAdjacent[i] = this.adj[i];
        }
        newAdjacent[this.adj.length] = new Adjacent(id, weight);
        this.adj = newAdjacent;
    }
}
