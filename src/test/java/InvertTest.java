import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InvertTest {



    @DisplayName("Numbers are distinct")
    @Test
    void distinctNumbers() {

        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 10);
        map.put("Oranges", 9);
        map.put("Bananas", 8);
        Map<Integer, Set<String>> map2 = MapUtils.invert(map);

        assertEquals(Set.of("Apple"), map2.get(10));
        assertEquals(Set.of("Oranges"), map2.get(9));
        assertEquals(Set.of("Bananas"), map2.get(8));
    }

    @DisplayName("Strings mapped to same Integer")
    @Test
    void mappedString(){

        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 10);
        map.put("Oranges", 9);
        map.put("Bananas", 9);
        Map<Integer, Set<String>> map2 = MapUtils.invert(map);
        assertEquals(Set.of("Oranges", "Bananas"), map2.get(9));
        assertEquals(Set.of("Apple"), map2.get(10));
    }

    @DisplayName("Return Empty Map on null")
    @Test
    void nullMap(){
        Map<String, Integer> map = null;
        Map<Integer, Set<String>> map2 = MapUtils.invert(map);
        assertTrue(map2.isEmpty());
    }

    @DisplayName("Return Empty Map on Empty Map")
    @Test
    void emptyMap(){
        Map<String, Integer> map = new HashMap<>();
        Map<Integer, Set<String>> map2 = MapUtils.invert(map);
        assertTrue(map2.isEmpty());
    }

    @DisplayName("Skips missing entries number")
    @Test
    void missingEntries(){
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", null);
        map.put("Oranges", 9);
        map.put("Bananas", 9);
        Map<Integer, Set<String>> map2 = MapUtils.invert(map);

        assertEquals(Set.of(9), map2.keySet());
        assertEquals(Set.of("Oranges", "Bananas"), map2.get(9));
        assertEquals(1, map2.size());
    }


}