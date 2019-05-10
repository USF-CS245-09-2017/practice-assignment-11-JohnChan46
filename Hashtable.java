import java.lang.*;

public class Hashtable {
    HashNode[] buckets; 
    int num_buckets; 
    int size;

    public Hashtable() {
        num_buckets = 350000; //num between 2027 and 314527
        buckets = new HashNode[num_buckets];
        size = 0;
    }

    class HashNode {
        public String key;
        public String value;
        public HashNode next;

        public HashNode(String key, String value) {

            this.key = key;
            this.value = value;
            this.next = null;
            size = 0;
        }
    }


    public int getBucket(String key) { 
        int hash = key.hashCode();
        return Math.abs((hash % num_buckets));
    }


    public boolean containsKey (String key){
        int bucketfinder = getBucket(key);
        HashNode currentnode = buckets[bucketfinder];
        while (currentnode != null) {
            if (currentnode.key.equals(key)){
                return true;
            }
        }
        return false;

    }

    public String get (String key){
        int bucketfinder = getBucket(key);
        HashNode currentnode = buckets[bucketfinder];
        while (currentnode != null) {
            if (currentnode.key.equals(key)){
                return currentnode.value;
            }
            currentnode = currentnode.next;
        }
        return null;

    }



    public void put (String key, String value){
        int bucketfinder = getBucket(key);
        HashNode currentnode = buckets[bucketfinder];
        while (currentnode != null) {
            if (currentnode.key.equals(key)) {
                currentnode.key = key;
                currentnode.value = value;
                return;
            }
            currentnode = currentnode.next;
        }
        size++;

        HashNode new_node = new HashNode(key, value);
        new_node.next = buckets[bucketfinder];
        buckets[bucketfinder] = new_node;



    }

    public String remove (String key){

        int bucketfinder = getBucket(key);
        HashNode currentnode = buckets[bucketfinder];
        HashNode prevnode = null;
        while (currentnode.next != null && !currentnode.key.equals(key)) {
            prevnode = currentnode;
            currentnode = currentnode.next;
        }

        if (currentnode.key.equals(key))
            if (currentnode == null) {
                return null;
            }
        if (prevnode == null) {
            buckets[bucketfinder] = currentnode.next;
        }
        else {
            prevnode.next = currentnode.next;
            size--;
        }
        return currentnode.value;
    }



}