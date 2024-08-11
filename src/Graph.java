import java.util.*;

public class Graph {
    private Set<Nodes> nodes;
    private boolean directed;
    Graph(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    public void addNode(Nodes... n) {
        nodes.addAll(Arrays.asList(n));
    }

    public void addEdge(Nodes source, Nodes destination, double weight) {
        //nodes will be added to the graph if they are not already in the graph because the set will not allow duplicates
        nodes.add(source);
        nodes.add(destination);

        //edge helper prevents the creation of duplicate edges
        addEgdeHelper(source, destination, weight);

        if (!directed && source != destination) {
            addEgdeHelper(destination, source, weight);
        }

    }

    public void addEgdeHelper(Nodes a, Nodes b, double weight) {
        //goes through the list of edges and adds the edge if it is not already in the list
        for (Edge edge : a.edges) {
            if (edge.source == a && edge.destination == b) {
                edge.weight = weight;
                return;
            }
        }

        //adds the edge if it is not already in the list
        a.edges.add(new Edge(a, b, weight));
    }

    public void printEdges() {
        for (Nodes node : nodes) {
            LinkedList<Edge> edges = node.edges;

            if (edges.isEmpty()) {
                System.out.println(node.name + " has no edges");
                continue;
            }
            System.out.println("Node " + node.name + " has edges to: ");

            for (Edge edge : edges) {
                System.out.println("\t" + edge.destination.name + " with weight " + edge.weight);
            }

            System.out.println();
        }

    }

    //check if nodes have an edge between them
    public boolean hasEdge(Nodes source, Nodes destination) {
        LinkedList<Edge> edges = source.edges;
        for (Edge edge : edges) {
            if (edge.destination == destination) {
                return true;
            }
        }
        return false;

    }

    //reset the nodes to their original state
    public void resetNodesVisited() {
        for (Nodes node : nodes) {
            node.unvisit();
        }
    }


    public String shortestPath(Nodes start, Nodes end) {
        //keep track of which track gives the shortest path by keeping track of how
        //we arrived at the previous node
        //a pointer is kept to the parent of each node  to the start
        HashMap<Nodes, Nodes> changedAt = new HashMap<>();
        changedAt.put(start, null);

        //keeps track oof the shortest path to each node
        HashMap<Nodes, Double> shortestPath = new HashMap<>();

        //setting the shortest path weight of every node to infinity to start
        //and the start node to 0
        for (Nodes node : nodes) {
            if (node == start) {
                shortestPath.put(node, 0.0);
            } else {
                shortestPath.put(node, Double.POSITIVE_INFINITY);
            }
        }
        // going through all the nodes to go from the starting node
        for (Edge edge : start.edges) {
            shortestPath.put(edge.destination, edge.weight);
            changedAt.put(edge.destination, start);

        }

        start.visit();

        // This loop runs as long as there is an unvisited node that we can
        // reach from any of the nodes we could till then
        while (true) {
            Nodes currentNode = closestReachableUnvisited(shortestPath);
            // If we haven't reached the end node yet, and there isn't another
            // reachable node the path between start and end doesn't exist
            // (they aren't connected)
            if (currentNode == null) {
                System.out.println("There isn't a path between " + start.name + " and " + end.name + "it is the same place.");
                return "There isn't a path between " + start.name + " and " + end.name + "it is the same place.";
            }

            // If the closest non-visited node is our destination, we want to print the path
            if (currentNode == end) {
                System.out.println("The path with the smallest weight between "
                        + start.name + " and " + end.name + " is:");

                Nodes child = end;

                // It makes no sense to use StringBuilder, since
                // repeatedly adding to the beginning of the string

                // defeats the purpose of using StringBuilder
                String path = end.name;
                while (true) {
                    Nodes parent = changedAt.get(child);
                    if (parent == null) {
                        break;
                    }

                    // Since our changedAt map keeps track of child -> parent relations
                    // in order to print the path we need to add the parent before the child and
                    // it's descendants
                    path = parent.name + " ---- " + path;
                    child = parent;
                }
                System.out.println(path);
                System.out.println("Distance: " + shortestPath.get(end));
                System.out.println(" Time taken: " + shortestPath.get(end)/2 + " seconds");
                return path + "\n   Distance: " + shortestPath.get(end) + " " + "   \nTime taken: " + shortestPath.get(end)/2 + " seconds";
            }
            currentNode.visit();

            //go through the nodes individually
            for (Edge edge : currentNode.edges) {
                if (edge.destination.isVisited())
                    continue;

                if (shortestPath.get(currentNode)
                        + edge.weight
                        < shortestPath.get(edge.destination)) {
                    shortestPath.put(edge.destination,
                            shortestPath.get(currentNode) + edge.weight);
                    changedAt.put(edge.destination, currentNode);
                }
            }
        }
        
    }

    private Nodes closestReachableUnvisited(HashMap<Nodes, Double> shortestPathMap) {

        double shortestDistance = Double.POSITIVE_INFINITY;
        Nodes closestReachableNode = null;
        for (Nodes node : nodes) {
            if (node.isVisited())
                continue;

            double currentDistance = shortestPathMap.get(node);
            if (currentDistance == Double.POSITIVE_INFINITY)
                continue;

            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestReachableNode = node;
            }
        }
        return closestReachableNode;
    }
}
