package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.Dungeon;

public class MattTest {
    @Test
    public void blahTest() {
        assertEquals("a", "a");
    }
    
    @Test
    public void blahTest2() {
        Dungeon d = new Dungeon(1, 2);
        assertEquals(d.getWidth(), 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"co", "ck", "s"})
    public void demo(String string) {
        // Prints to the debug console
        System.out.println(string);
        assertEquals(string, string);
    }
}
