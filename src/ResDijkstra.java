public class ResDijkstra {
    int[] distance;
    int[] ant;
    public ResDijkstra(int[] distance, int[] ant){
        this.distance = distance;
        this.ant = ant;
    }
    public int[] getAnt() {return ant;}
    public int[] getDistance() {return distance;}
}
