package List;

public class Nodes implements Comparable<Nodes>{ 
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
        Nodes[] newAdj = new Nodes[this.adj.length + 1];

        for(int i = 0; i < this.adj.length; i++){
            newAdj[i] = this.adj[i];
        }
        newAdj[this.adj.length] = new Nodes(id, weight);
        this.adj = newAdj;

    }

    @Override
    public int compareTo(Nodes o) {
        if (o.getId() == this.getId()) {
            return 0;
        } else if (o.getId() > this.getId()) {
            return -1;
        } else {
            return 1;
        }
    }
    
}
