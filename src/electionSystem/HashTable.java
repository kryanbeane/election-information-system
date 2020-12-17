package electionSystem;

public class HashTable<T> {

    List<T>[] hashTableList;

    /**
     * Constructor.
     * @param size - takes in ths size of the hashtable.
     */
    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        //May be a problem with large hash tables
        // Creates a new array of lists.
        hashTableList= new List[size];
        for(int i=0; i<hashTableList.length; i++) { // Loopy Loop.
            // Adds a list for each index of hashtable array.
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
        int total=0;
        // Adds the key to the total
        for(int i=0; i<key.length(); i++) {
            total += key.charAt(i);                 //This line gets ASCII integer value and adds to total.
        }
        System.out.println(total%hashTableList.length);
        return total%hashTableList.length;
    }

    /**
     *  Insert the hashed person into the hash table.
     * @param key -
     * @param person -
     */
    public void insertHash(String key, T person) {
        // Insert the hashed person into the hash table.
        hashTableList[hashFunction(key)].addNode(person);
        // Inserts person at Index produced by hash function. ._.
    }

    /**
     * Removes a hash from the hash table.
     * @param key - Name string of selected person to remove.
     * @param person - Object of selected person to remove.
     */
    public void removeHash(String key, T person) {
        int arraySlot = hashFunction(key);
        for(int i = 0; i < hashTableList[arraySlot].length(); i++) {
            if(hashTableList[arraySlot].accessAtIndex(i).getContents() == person) {
                hashTableList[arraySlot].removeNode(i);
            }
            else {
                System.out.println("That person is not at ");
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
        //Have to have old and new Keys, as name of person may change.
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
