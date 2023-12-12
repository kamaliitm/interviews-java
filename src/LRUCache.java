import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class LinkedListNode {
    int key;
    int value;

    LinkedListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {

    Map<Integer, LinkedListNode> keyValueMap;
    Queue<LinkedListNode> queue;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyValueMap = new HashMap<>();
        queue = new LinkedList<>();
    }

    public void put(int key, int value) {
        if (!keyValueMap.containsKey(key)) {
            if (queue.size() == capacity) {
                // need to evict based on LRU
                LinkedListNode node = queue.poll();
                keyValueMap.remove(node.key);
            }
        } else {
            queue.remove(keyValueMap.get(key));
        }

        LinkedListNode node = new LinkedListNode(key, value);
        queue.add(node);
        keyValueMap.put(key, node);
    }

    public int get(int key) {
        if (!keyValueMap.containsKey(key)) {
            return -1;
        }

        LinkedListNode node = keyValueMap.get(key);
        queue.remove(node);
        queue.add(node);

        return node.value;
    }
}
