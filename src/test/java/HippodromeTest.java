import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Mock
    Horse horse;

    @Test
    public void testNullParameterInConstructor() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });

        Assertions.assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void testEmptyParameterInConstructor() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            List<Horse> horses = new ArrayList<>();
            new Hippodrome(horses);
        });

        Assertions.assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void getHorsesTest() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        Assertions.assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse hors : horses) {
            verify(hors).move();
        }
    }

    @Test
    public void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();

        horses.add(new Horse("Horse1", 1, 1));
        horses.add(new Horse("Horse2", 2, 2));
        horses.add(new Horse("Horse3", 3, 4));
        horses.add(new Horse("Horse4", 4, 3));

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses.get(2), hippodrome.getWinner());

    }
}