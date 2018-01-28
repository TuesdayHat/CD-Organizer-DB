import models.CD;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class organizerTest {

    CD testCDOne = new CD("A Night At The Opera", "Queen");

    CD testCDTwo = new CD("Wilco", "Wilco");

    CD testCDThree = new CD("{awayland}", "Villagers");

    CD testCDFour = new CD("AM", "Arctic Monkeys");

    CD testFive = new CD("Comfort Eagle", "Cake");

    @Test
    public void NewTitleObjectGetsCorrectlyCreated_true() throws Exception {
        assertEquals("A Night At The Opera", testCDOne.getTitle());
    }

    @Test
    public void holdsMultipleCDsWithTitles_int() throws Exception {
        assertEquals(true, CD.getInstances().size() >= 3 );
    }

    @Test
    public void checksWhatTheSecondCDIsTitles_String() throws Exception {
        assertEquals("Wilco", CD.getInstances().get(1).getTitle());
    }

    @Test
    public void NewArtistObjectGetsCorrectlyCreated_String() throws Exception {
        assertEquals("Queen",testCDOne.getArtist());
    }

    @Test
    public void holdsMultipleCDsWithArtists_string() throws Exception {
        assertEquals(true, CD.getInstances().size() >= 3);
        assertEquals("Queen", CD.getInstances().get(0).getArtist());
    }

//    @Test
//    public void sortsInstanceListAlphabeticallyByArtist_array() throws Exception {
//        String[] sortedArtist = {"Arctic Monkeys", "Cake", "Queen", "Villagers", "Wilco"};
//        assertArrayEquals(sortedArtist, CD.getSorted());
//    }

}
