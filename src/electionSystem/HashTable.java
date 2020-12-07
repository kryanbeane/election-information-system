package electionSystem;

public class HashTable<T> {

    List<T>[] hashTableArray;                                       // Need to figure out why this allows use of the linked list methods while it's an array.

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

    // Hashes a politician or a candidate name.
    public int hashFunction(String name) {                          // Hashes the names.
        int total=0;                                                // Total is the value that will be mapped in the hash table. Sets initial total to 0.
        for(int i=0; i<name.length(); i++) {                        // For loop to traverse through each character in the string of the name.
            total+=name.charAt(i);                                  // Adds ASCII values of each char to total.
        }

        System.out.println(total%hashTableArray.length);
        return total%hashTableArray.length;                         // Returns the remainder after dividing the total by the hash table size.
    }

    public void insertHash(String name, T person) {                 // Insert the hashed person into the hash table.
        hashTableArray[hashFunction(name)].addNode(person);
    }

    /**
     * Removes a hash from the hash table.
     * @param name - Name string of selected person to remove.
     * @param person - Object of selected person to remove.
     */
    public void removeHash(String name, T person) {
        int node = hashFunction(name);
        for(int i = 0; i < hashTableArray[node].length(); i++) {
            if(hashTableArray[node].accessAtIndex(i).getContents() == person) {
                hashTableArray[node].removeNode(i);
            }
        }
    }

    /**
     * Edits an old hash and replaces it with a new hashed person.
     * @param oldName - Name of old person.
     * @param oldPerson - Object of old person.
     * @param newName - Name of new person to hash.
     * @param newPerson - Object of new person to hash.
     */
    public void edit(String oldName, T oldPerson, String newName, T newPerson) {
        removeHash(oldName, oldPerson);
        insertHash(newName, newPerson);
    }

    public T getHash(int hash, int element) {
        return hashTableArray[hash].accessAtIndex(element).getContents();
    }

}
