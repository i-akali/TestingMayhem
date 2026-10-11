import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordSearchTest {

    private final HashMap<String, Set<String>> map = new HashMap<>(Map.of(
            "banana", Set.of("file1.txt", "file2.txt", "file3.txt"),
            "apple", Set.of("file2.txt"),
            "monkey", Set.of("file1.txt", "file2.txt"),
            "cat", Set.of("file1.txt", "file2.txt", "file3.txt"),
            "grapefruit", Set.of("file1.txt"),
            "peach", Set.of("file3.txt"),
            "bear", Set.of("file3.txt"),
            "dog", Set.of("file2.txt", "file3.txt")
    ));

    @DisplayName("One word in array")
    @Test
    void singleItem() {
        String[] words = {"cat"};
        assertEquals(List.of("file1.txt", "file2.txt", "file3.txt"), WordSearch.search(words, map));
    }

    @DisplayName("Words in same file")
    @Test
    void wordsInSameFile(){
        String[] words = {"peach", "bear"};
        assertEquals(List.of("file3.txt"), WordSearch.search(words, map));
    }

    @DisplayName("Words in different file")
    @Test
    void disjointSets(){
        String[] words = {"monkey", "bear"};
        assertEquals(List.of("file1.txt", "file2.txt", "file3.txt"), WordSearch.search(words, map));
    }

    @DisplayName("Ranks by appearances")
    @Test
    void rankedOrder(){
        String[] words = {"peach", "dog"};
        assertEquals(List.of("file3.txt", "file2.txt"), WordSearch.search(words, map));
    }

    @DisplayName("Breaks tie by lexicographical sorting")
    @Test
    void rankedOrderTie(){
        String[] words = {"cat", "apple"};
        assertEquals(List.of("file2.txt", "file1.txt", "file3.txt"), WordSearch.search(words, map));
    }

    @DisplayName("Ignores absent words")
    @Test
    void missingWord(){
        String[] words = {"aardvark", "monkey"};
        assertEquals(List.of("file1.txt", "file2.txt"), WordSearch.search(words, map));
    }

    @DisplayName("Ignores singular absent word")
    @Test
    void missingWord2(){
        String[] words = {"aardvark"};
        assertEquals(List.of(), WordSearch.search(words, map));
    }

    @DisplayName("Empty input empty output")
    @Test
    void emptyArray(){
        String[] words = {};
        assertEquals(List.of(), WordSearch.search(words, map));
    }

    @DisplayName("Null input empty output")
    @Test
    void nullArray(){
        String[] words = null;
        assertEquals(List.of(), WordSearch.search(words, map));
    }

    @DisplayName("Ranks correctly with no tie")
    @Test
    void rankedOrderNoTie(){
        String[] words = {"cat", "dog", "bear", "banana"};
        assertEquals(List.of("file3.txt", "file2.txt", "file1.txt"), WordSearch.search(words, map));
    }
}