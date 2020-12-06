package electionSystem;

public class HashTable<J> {

    //Create a list to use in hashing
    List<J>[] hashTable;

    private int arraySize = hashTable.length;

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        hashTable = new List[size];
        for(int i=0;i<hashTable.length;i++)
            hashTable[i]=new List<J>();
    }

    // Hashes the names of drinks and ingredients
    public int hashFunction(String name){
        // Initially 0
        int num=0;
        // Adds ASCII value of characters together to get total
        for(int i=0; i<name.length(); i++){
            num+=name.charAt(i);}
        System.out.println("HASH: " + num%hashTable.length);
        // Result of total modulus table length is returned
        return num%hashTable.length;
    }

    // Adds hash to hashtable.
    public void add(String name, J object){
        hashTable[hashFunction(name)].addNode(object);
    }

    // Removes hash from hashtable.
    public void remove(String name, J object){
        int element = hashFunction(name);
        for(int i = 0; i < hashTable[element].length(); i++)
            if(hashTable[element].accessAtIndex(i).getContents() == object){
                hashTable[element].removeNode(i);
            }
    }

    // Edits hashes for edited politicians and candidates.
    // Need to declare a candidate, election or politician object prior to using.
    public void edit(String oldName,J oldObject, String newName, J newObject){
        remove(oldName, oldObject);
        add(newName, newObject);
    }

    // Returns the hashtable size.
    public int hashSize(int hash){
        return hashTable[hash].length();
    }

    // Gets a given hash from the hashtable.
    public J getHash(int hash, int index){
        return hashTable[hash].accessAtIndex(index).getContents();
    }

    public void get(int index) {

    }


//    static class HashEntry {
//        public String value;
//        public HashEntry(String value) {
//            this.value = value;
//        }
//    }
//
//    static class HashMap {
//        int TABLE_SIZE = 127;
//        public HashEntry[] table;
//
//        public HashMap() {                                  // Constructor
//            table = new HashEntry[127];                     // Creates a new array called table
//            for (int i = 0; i < TABLE_SIZE; i++) {
//                table[i] = null;                            // Sets all values of array to null, stops once it reaches set table size
//            }
//        }
//
//            int HashFunc(String value) {
//                int key=0;                                  // Creates an integer key
//                char[] charArray = value.toCharArray();     // Converts a string to an array of characters
//                for(int i=0; i<value.length();i++) {
//                    key += charArray[i];
//                    System.out.println(key);
//                }
//                return key % TABLE_SIZE;                    // This is the bones of the function
//            }
//
//            void insert(String value) {
//                int hash = HashFunc(value);
//                if (table[hash] == null) {                  // What if table hash is not equal to null?
//                    table[hash] = new HashEntry(value);     // Need to add else statement for if hash =/= null
//                }
//            }
//
//            HashEntry search(String value) {
//                int hash = HashFunc(value);
//                if(table[hash] == null) {
//                    System.out.println("Value {" + value + "{ not found.");
//                    return null;
//                    } else {
//                        return table[hash];
//                }
//            }
//
//            void remove(String value) {
//                int hash = HashFunc(value);
//                if (table[hash] != null) {
//                    table[hash] = null;
//                }
//            }
//        }
//
//        public static void main(String[] args) {
//            HashMap hash = new HashMap();
//            hash.insert("Allgood");
//            hash.insert("B");
//            hash.insert("C");
//            System.out.println(hash.search("B"));
//    }
}
