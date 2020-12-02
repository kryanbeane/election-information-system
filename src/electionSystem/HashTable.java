package electionSystem;

public class HashTable {

    static class HashEntry {
        public String value;
        public HashEntry(String value) {
            this.value = value;
        }
    }

    static class HashMap {
        int TABLE_SIZE = 127;
        public HashEntry[] table;

        public HashMap() {                                  // Constructor
            table = new HashEntry[127];                     // Creates a new array called table
            for (int i = 0; i < TABLE_SIZE; i++) {
                table[i] = null;                            // Sets all values of array to null, stops once it reaches set table size
            }
        }

            int HashFunc(String value) {
                int key=0;                                  // Creates an integer key
                char[] charArray = value.toCharArray();     // Converts a string to an array of characters
                for(int i=0; i<value.length();i++) {
                    key += charArray[i];
                    System.out.println(key);
                }
                return key % TABLE_SIZE;                    // This is the bones of the function
            }
            void insert(String value) {
                int hash = HashFunc(value);
                if (table[hash] == null) {                  // What if table hash is not equal to null?
                    table[hash] = new HashEntry(value);
                }
            }

            HashEntry search(String value) {
                int hash = HashFunc(value);
                if(table[hash] == null) {
                    System.out.println("Value {" + value + "{ not found.");
                    return null;
                    } else {
                        return table[hash];
                }
            }
            void remove(String value) {
                int hash = HashFunc(value);
                if (table[hash] != null) {
                    table[hash] = null;
                }
            }
        }

        public static void main(String[] args) {
            HashMap hash = new HashMap();
            hash.insert("Allgood");
            hash.insert("B");
            hash.insert("C");
            System.out.println(hash.search("B"));
    }
}
