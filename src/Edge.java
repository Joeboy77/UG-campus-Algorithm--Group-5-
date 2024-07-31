public class Edge implements Comparable<Edge> {
    Nodes source;
    Nodes destination;
    double weight;
    //node weighted objects represents nodes in a weighted graph

    public Edge(Nodes source, Nodes destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }


    public String toString(){
        return String.format("%s-> %s, %f", source.name, destination.name, weight);
    } //this method returns a string representation of the edge
    //it is only needed when priority queue is used



    public int compareTo(Edge otherEdge) {
        if (this.weight > otherEdge.weight) {
            return 1;
        }
        else {
            return -1;
        }
    } //this method compares two edges and returns -1 if this edge is less than the other edge, 0 if they are equal, and 1 if this edge is greater than the other edge

}

