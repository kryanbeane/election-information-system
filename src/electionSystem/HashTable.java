package electionSystem;

public class HashTable<T> {

    List<T>[] hashTableArray;

    /**
     * Constructor.
     * @param polCandHashTableSize - takes in ths size of the hashtable.
     */
    public HashTable(int polCandHashTableSize) {
        hashTableArray= new List[polCandHashTableSize];
        for(int i=0; i<hashTableArray.length; i++) {
            hashTableArray[i] = new List<>();
        }
    }

    /**
     *  Hashes a politician or a candidate name.
     * @param key - Key to be hashed.
     * @return - Remainder after dividing total by hash table size.
     */
    public int hashFunction(int key) {
        // Value that will be mapped in the hash table.
        int total=0;
        // Adds the key to the total
        total+=key;

        System.out.println(total%hashTableArray.length);
        return total%hashTableArray.length;
    }

    /**
     *  Insert the hashed person into the hash table.
     * @param key -
     * @param person -
     */
    public void insertHash(int key, T person) {                 // Insert the hashed person into the hash table.
        hashTableArray[hashFunction(key)].addNode(person);
    }

    /**
     * Removes a hash from the hash table.
     * @param key - Name string of selected person to remove.
     * @param person - Object of selected person to remove.
     */
    public void removeHash(int key, T person) {
        int node = hashFunction(key);
        for(int i = 0; i < hashTableArray[node].length(); i++) {
            if(hashTableArray[node].accessAtIndex(i).getContents() == person) {
                hashTableArray[node].removeNode(i);
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
    public void edit(int oldKey, T oldPerson, int newKey, T newPerson) {
        removeHash(oldKey, oldPerson);
        insertHash(newKey, newPerson);
    }

    public T getHash(int hash, int element) {
        return hashTableArray[hash].accessAtIndex(element).getContents();
    }

    ///Returns the hashtable size///
    public int hashSize(int hash){
        return hashTableArray[hash].length();
    }


}
