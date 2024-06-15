import java.util.HashMap;
import java.util.Map;


public class LRUCache{
    int capacity;
    Map<Integer,ListNode> map;
    ListNode head;
    ListNode tail;

    public class ListNode {
        int key;
        int value;
        ListNode prev;
        ListNode next;
    
        public ListNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public void remove(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public void add(ListNode node){
        // add to the end of the doubliy linked list
        ListNode previousEnd = tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = tail;
        tail.prev = node;
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        ListNode node = map.get(key); // map -> 1:(1,2)
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value){
        if(map.containsKey(key)){
            remove(map.get(key));
        }
        ListNode newNode = new ListNode(key, value);
        add(newNode);
        map.put(key, newNode);

        if (map.size() > capacity) {
            ListNode nodeToDelete = head.next;
            remove(nodeToDelete);
            map.remove(nodeToDelete.key);
        }
    }

    public static void main(String[] args){
        LRUCache cache = new LRUCache(2);

        cache.put(1,2);
        cache.put(3,5);
        System.out.println("hello here is the result: " + cache.get(3));
        
    }
}