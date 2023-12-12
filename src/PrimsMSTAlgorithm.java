import java.util.*;

class PrimNode {
    int vertex;
    int key;
    int parentVertex;

    PrimNode(int vertex, int key) {
        this.vertex = vertex;
        this.key = key;
        this.parentVertex = -1;
    }

    PrimNode(int vertex, int key, int parentVertex) {
        this.vertex = vertex;
        this.key = key;
        this.parentVertex = parentVertex;
    }
}

class PrimMinHeap {
    PrimNode[] data;
    int capacity;
    int heapSize;
    Map<Integer, Integer> vertexHeapIndexMap;

    PrimMinHeap(int capacity) {
        this.data = new PrimNode[capacity];
        this.capacity = capacity;
        this.heapSize = 0;
        this.vertexHeapIndexMap = new HashMap<>();
    }

    boolean insert(PrimNode node) {
        if (heapSize == capacity) {
            return false;
        }

        data[heapSize++] = node;
        vertexHeapIndexMap.put(node.vertex, heapSize-1);
        heapifyUp(heapSize-1);

        return true;
    }

    PrimNode extractMin() {
        if (heapSize == 0) {
            return null;
        }

        swap(0, --heapSize);
        heapifyDown(0);

        vertexHeapIndexMap.remove(data[heapSize]);

        return data[heapSize];
    }

    PrimNode peek() {
        if (heapSize == 0) {
            return null;
        }

        return data[0];
    }

    void updateNodeIfLowKey(int vertex, PrimNode newNode) {

        int index = getVertexIndex(vertex);
        if (index >= 0 && index < heapSize) {
            if (data[index].key > newNode.key) {
                data[index] = newNode;
                heapifyUp(index);
            }
        }
    }

    int getVertexIndex(int vertex) {
        return vertexHeapIndexMap.getOrDefault(vertex, -1);
    }

    void heapifyUp(int index) {
        while(parent(index) >= 0 && data[parent(index)].key > data[index].key) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    void heapifyDown(int index) {

        PrimNode smallestNode = data[index];
        int smallestIndex = index;

        if (left(index) < heapSize) {
            PrimNode leftChild = data[left(index)];
            if (leftChild.key < smallestNode.key) {
                smallestNode = leftChild;
                smallestIndex = left(index);
            }
        }

        if (right(index) < heapSize) {
            PrimNode rightChild = data[right(index)];
            if (rightChild.key < smallestNode.key) {
                smallestNode = rightChild;
                smallestIndex = right(index);
            }
        }

        if (smallestIndex != index) {
            swap(smallestIndex, index);
            if (smallestIndex < heapSize) {
                heapifyDown(smallestIndex);
            }
        }
    }

    void swap(int i, int j) {
        if (i == j) {
            return;
        }
        PrimNode temp = data[i];
        data[i] = data[j];
        data[j] = temp;
        vertexHeapIndexMap.put(data[j].vertex, j);
        vertexHeapIndexMap.put(data[i].vertex, i);
    }

    int left(int parentIndex) {
        return 2*parentIndex + 1;
    }

    int right(int parentIndex) {
        return 2*parentIndex + 2;
    }

    int parent(int childIndex) {
        return (childIndex-1)/2;
    }
}

class PrimEdge {
    int endVertex;
    int edgeWeight;

    PrimEdge(int endVertex, int edgeWeight) {
        this.endVertex = endVertex;
        this.edgeWeight = edgeWeight;
    }
}

class PrimGraph {
    int[] vertices;
    Map<Integer, List<PrimEdge>> edges;

    PrimGraph(int numVertices) {
        vertices = new int[numVertices];
        edges = new HashMap<>();
    }

    void addEdge(int vertexU, int vertexV, int weight) {
        List<PrimEdge> edgesFromVertexU = edges.getOrDefault(vertexU, new ArrayList<>());
        edgesFromVertexU.add(new PrimEdge(vertexV, weight));
        edges.put(vertexU, edgesFromVertexU);

        List<PrimEdge> edgesFromVertexV = edges.getOrDefault(vertexV, new ArrayList<>());
        edgesFromVertexV.add(new PrimEdge(vertexU, weight));
        edges.put(vertexV, edgesFromVertexV);
    }

    List<PrimEdge> getEdgesFromVertex(int vertex) {
        return edges.getOrDefault(vertex, null);
    }
}

public class PrimsMSTAlgorithm {

    public static int prims(int n, List<List<Integer>> edges, int start) {

        PrimGraph graph = new PrimGraph(n);
        for (List<Integer> edge: edges) {
            graph.addEdge(edge.get(0), edge.get(1), edge.get(2));
        }

        PrimMinHeap minHeap = new PrimMinHeap(n);
        for (int i = 1; i <= n; i++) {
            minHeap.insert(new PrimNode(i, Integer.MAX_VALUE));
        }

        Map<Integer, PrimNode> minSpanningTree = new HashMap<>();

        minHeap.updateNodeIfLowKey(start, new PrimNode(start, 0));

        while (minHeap.heapSize > 0) {
            PrimNode node = minHeap.extractMin();
            minSpanningTree.put(node.vertex, node);

            List<PrimEdge> edgesFromNode = graph.getEdgesFromVertex(node.vertex);
            for (PrimEdge edge: edgesFromNode) {
                if (minSpanningTree.containsKey(edge.endVertex)) {
                    continue;
                }

                minHeap.updateNodeIfLowKey(edge.endVertex, new PrimNode(
                        edge.endVertex, edge.edgeWeight, node.vertex));

            }
        }

        return minSpanningTree.values().stream().map(pn -> pn.key).reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
//        PrimMinHeap minHeap = new PrimMinHeap(10);
//        minHeap.insert(new PrimNode(1, 5));
//        minHeap.insert(new PrimNode(2, 3));
//        minHeap.insert(new PrimNode(3, 7));
//        minHeap.insert(new PrimNode(4, 2));
//        minHeap.insert(new PrimNode(5, 8));
//
//        System.out.println(minHeap.peek().key);
//        System.out.println(minHeap.extractMin().key);
//        System.out.println(minHeap.extractMin().key);
//        minHeap.insert(new PrimNode(6, 4));
//        System.out.println(minHeap.peek().key);
////        minHeap.decreaseKey(5, 1);
//        System.out.println(minHeap.peek().key);
//        System.out.println(minHeap.extractMin().key);
//        System.out.println(minHeap.extractMin().key);

        int mstTolalWeight = prims(5, Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 3, 4),
                Arrays.asList(4, 2, 6),
                Arrays.asList(5, 2, 2),
                Arrays.asList(2, 3, 5),
                Arrays.asList(3, 5, 7)
        ), 1);
        System.out.println(mstTolalWeight);
    }
}
