package test;

import electionSystem.HashTable;
import electionSystem.List;
import electionSystem.Politician;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    int size=800;
    HashTable<Politician> testTable;
    Politician pol;

    @BeforeEach
    void setUp() {
        try {
            testTable=new HashTable<>(size);
            pol=new Politician("1A", "bob", "ff", "19/01/2000", "Waterford", "https://images-ext-2.discordapp.net/external/27CvotwlaWxCbzNn1djU5bNwwXptQ-pZG2ygDszI8PE/https/pbs.twimg.com/profile_images/1109183335845298177/jFYfPL6k_400x400.jpg%22%22");
        } catch (Exception ignore) {}
    }

    @AfterEach
    void tearDown() {
        testTable.removeHash("bob", pol);
    }

    @Test
    void insertHash() {
        testTable.insertHash("bob", pol);
        int hash = testTable.hashFunction("bob");
        assertEquals(61, hash);
    }

    @Test
    void removeHash() {
        int hash = testTable.hashFunction("bob");
        testTable.removeHash("bob", pol);
        assertNull((testTable.getHash(hash, 0)));
    }

    @Test
    void edit() {
    }

    @Test
    void getHash() {
    }

    @Test
    void hashSize() {
    }
}