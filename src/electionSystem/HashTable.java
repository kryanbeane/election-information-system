package electionSystem;

public class HashTable<T> {

    List<T>[] hashTableArray;                                       // Need to figure out why this allows use of the linked list methods while it's an array.

    public int hashFunction(String name) {                          // Hashes the names.
        int total=0;                                                // Total is the value that will be mapped in the hash table. Sets initial total to 0.
        for(int i=0; i<name.length(); i++) {                        // For loop to traverse through each character in the string of the name.
            total+=name.charAt(i);                                  // Adds ASCII values of each char to total.
        }

        System.out.println(total%hashTableArray.length);
        return total%hashTableArray.length;                         // Returns the remainder after dividing the total by the hash table size.
    }

    public void insertHash(String name, T person) {
        hashTableArray[hashFunction(name)].addNode(person);
    }

    public void removeHash(String name, T person) {
        int node = hashFunction(name);
        for(int i = 0; i < hashTableArray[node].length(); i++) {
            if(hashTableArray[node].accessAtIndex(i).getContents() == person) {
                hashTableArray[node].removeNode(i);
            }
        }
    }

    public void edit(String oldName, T oldPerson, String newName, T newPerson) {
        removeHash(oldName, oldPerson);
        insertHash(newName, newPerson);
    }




}

