package List;

public class Nodes {
    int id;
    String name;
    Nodes[] adj = new Nodes[0];
    double weight;
    public Nodes(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Nodes(int id, double weight){
        this.id = id;
        this.weight = weight;
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

    public Nodes[] getAdjacent(){
        return this.adj;
    }
    public double getWeight(){
        return this.weight;
    }
    public void addAdjacent(int id, double weight){
        System.out.println("lenght" + this.adj.length);
        System.out.println("newAdj lenght = " + (this.adj.length + 1));

        Nodes[] newAdj = new Nodes[this.adj.length + 1];
        System.out.println("newAdj lenght = " + newAdj.length);

        for(int i = 0; i < this.adj.length; i++){
            newAdj[i] = this.adj[i];
        }
        newAdj[this.adj.length] = new Nodes(id, weight);
        this.adj = newAdj;

    }

    // public void addAdjacent(int id, double weight){
    //     System.out.println('x');
    //     Nodes[] newAdjacent = new Nodes[this.adj.length + 1];
    //     for(int i = 0; i < this.adj.length; i++){
    //         newAdjacent[i] = this.adj[i];
    //     }
    //     newAdjacent[this.adj.length] = new Nodes(id, weight);
    //     this.adj = newAdjacent;
    // }
    
}
