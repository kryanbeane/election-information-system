package electionSystem;

public class HashTable<T> {

    List<T>[] hashTableList;

    /**
     * Constructor.
     * @param size - takes in ths size of the hashtable.
     */
    public HashTable(int size) {
        hashTableList= new List[size];
        for(int i=0; i<hashTableList.length; i++) {
            hashTableList[i] = new List<>();
        }
    }

    /**
     *  Hashes a string.
     * @param key - Key to be hashed.
     * @return - Remainder after dividing total by hash table size.
     */
    public int hashFunction(String key) {
        // Value that will be mapped in the hash table.
        int total=1;
        // Adds the key to the total
        for(int i=0; i<key.length(); i++) {
            total *= key.charAt(i);
        }
        System.out.println(total%hashTableList.length);
        return total%hashTableList.length;
    }

    /**
     *  Insert the hashed person into the hash table.
     * @param key -
     * @param person -
     */
    public void insertHash(String key, T person) {                 // Insert the hashed person into the hash table.
        hashTableList[hashFunction(key)].addNode(person);
    }

    /**
     * Removes a hash from the hash table.
     * @param key - Name string of selected person to remove.
     * @param person - Object of selected person to remove.
     */
    public void removeHash(String key, T person) {
        int node = hashFunction(key);
        for(int i = 0; i < hashTableList[node].length(); i++) {
            if(hashTableList[node].accessAtIndex(i).getContents() == person) {
                hashTableList[node].removeNode(i);
            }
        }
    }

    /**
     * Edits an old hash and replaces it with a new hashed person.
     * @param oldKey - Name of old person.
     * @param oldPerson - Object of old person.
     * @param newKey - Name of new person to hash.
     * @param newPerson - Object of new person to hash.
     */
    public void edit(String  oldKey, T oldPerson, String newKey, T newPerson) {
        removeHash(oldKey, oldPerson);
        insertHash(newKey, newPerson);
    }

    public T getHash(int hash, int index) {
        return hashTableList[hash].accessAtIndex(index).getContents();
    }

    ///Returns the hashtable size///
    public int hashSize(int hash){
        return hashTableList[hash].length();
    }





}
