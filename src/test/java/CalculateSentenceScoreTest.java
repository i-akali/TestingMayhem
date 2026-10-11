import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CalculateSentenceScoreTest {

    private final HashMap<String, Double> map = new HashMap<>(Map.ofEntries(
            Map.entry("dogs", 1.5),
            Map.entry("are", 0.0),
            Map.entry("cute", 2.0),
            Map.entry("i", 0.0),
            Map.entry("love", 2.0),
            Map.entry("coding", 1.0),
            Map.entry("where", 0.5),
            Map.entry("is", 0.0),
            Map.entry("the", 0.0),
            Map.entry("function", 1.0),
            Map.entry("hate", -2.0),
            Map.entry("peanuts", -1.0)));


    @DisplayName("Baseline Test")
    @Test
    void calculateSentenceScore1(){
        assertEquals(
                3.5/3,
                Analyzer.calculateSentenceScore(map, "i love dogs"),
                1e-9 );

    }

    @DisplayName("All Capital Letters")
    @Test
    void allCapitalLetters(){
        assertEquals(
                1.5/4,
                Analyzer.calculateSentenceScore(map, "WHERE IS THE FUNCTION"),
                1e-9 );
    }

    @DisplayName("Mixed Capital Letters")
    @Test
    void mixedCapitalLetters(){
        assertEquals(
                1.5/4,
                Analyzer.calculateSentenceScore(map, "Where Is The Function"),
                1e-9 );
    }

    @DisplayName("Duplicate words in Sentence")
    @Test
    void doubleAppearances(){
        assertEquals(
                5.0/4,
                Analyzer.calculateSentenceScore(map, "i love dogs dogs"),
                1e-9 );
    }

    @DisplayName("Invalid Starting Letter")
    @Test
    void invalidStartingLetter(){
        assertEquals(
                2.0/2,
                Analyzer.calculateSentenceScore(map, "i love !hate"),
                1e-9 );
    }

    @DisplayName("Word Missing from Map")
    @Test
    void missingFromMap(){
        assertEquals(
                1.0/3,
                Analyzer.calculateSentenceScore(map, "coding is hard"),
                1e-9 );
    }

    @DisplayName("All Words Missing from Map")
    @Test
    void missingFromMap2(){
        assertEquals(
                0.0,
                Analyzer.calculateSentenceScore(map, "lions say roar !"),
                1e-9 );
    }

    @DisplayName("Empty Map")
    @Test
    void emptyMap(){
        assertEquals(
                0.0,
                Analyzer.calculateSentenceScore(Map.of(), "I hate peanuts !"),
                1e-9 );
    }

    @DisplayName("Null Map")
    @Test
    void nullMap(){
        assertEquals(
                0.0,
                Analyzer.calculateSentenceScore(null, "lions say roar !"),
                1e-9 );
    }

    @DisplayName("Null Sentence")
    @Test
    void nullSentence(){
        assertEquals(
                0.0,
                Analyzer.calculateSentenceScore(map, null),
                1e-9 );
    }

    @DisplayName("Empty Sentence")
    @Test
    void emptySentence(){
        assertEquals(
                0.0,
                Analyzer.calculateSentenceScore(map, ""),
                1e-9 );
    }

    @DisplayName("Negative Scores")
    @Test
    void negativeScores(){
        assertEquals(
                -1.0,
                Analyzer.calculateSentenceScore(map, "i hate peanuts"),
                1e-9 );
    }

    @DisplayName("Digit Start of Word")
    @Test
    void digitStart(){
        assertEquals(
                1.5,
                Analyzer.calculateSentenceScore(map, "2 dogs"),
                1e-9 );
    }

    @DisplayName("Letter Start Punctuation End")
    @Test
    void letterStartNotEnd(){
        assertEquals(
                2.0/3,
                Analyzer.calculateSentenceScore(map, "dogs! are cute"),
                1e-9 );
    }

    @DisplayName("Single Word")
    @Test
    void singleWord(){
        assertEquals(
                2.0,
                Analyzer.calculateSentenceScore(map, "cute"),
                1e-9 );
    }
}