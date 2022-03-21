package List;

public class Edges {
    int id;
    int originNode;
    int destinationNode;
    double weight;
    public Edges(int id, int originNode, int destinationNode, double weight) {
        this.id = id;
        this.originNode = originNode;
        this.destinationNode = destinationNode;
        this.weight = weight;
    }
    public int getId(){
        return this.id;
    }
    public int getOriginNode(){
        return this.originNode;
    }
    public int getDestinationNode(){
        return this.destinationNode;
    }
    public double getWeight(){
        return this.weight;
    }

}
