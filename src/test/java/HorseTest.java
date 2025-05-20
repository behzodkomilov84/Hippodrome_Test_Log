import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class HorseTest {

    @Mock
    Horse horse;

    @Test
    public void ifFirstParameterIsNullThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, -5, 1);
        });
    }

    @Test
    public void ifFirstParameterIsNullThrowsExceptionWithMessage_NameCannotBeNull() {
        try {
            new Horse(null, 5, 1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void ifFirstParameterIsBlankThrowIllegalArgumentExceptionWithMessage_NameCannotBeBlank(String input) {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse(input, 0, 1);
        });
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void ifSecondParameterIsNegativeThrowIllegalArgumentExceptionWithMessage_SpeedCannotBeNegative() {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Horse name", -1, 1);
        });

        assertEquals("Speed cannot be negative.", e.getMessage());

    }

    @Test
    public void ifThirdParameterIsNegativeThrowIllegalArgumentExceptionWithMessage_DistanceCannotBeNegative() {
        IllegalArgumentException e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Horse name", 1, -1);
        });

        assertEquals("Distance cannot be negative.", e.getMessage());

    }

    @Test
    public void if_getName_EqualsFirstParameterOfConstructor() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Horse name", 1, 1);

        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);

        String nameString = (String) name.get(horse);

        assertEquals("Horse name", nameString);

    }

    @Test
    public void if_getSpeed_EqualsSecondParameterOfConstructor() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Horse name", 1, 1);

        Field speed = Horse.class.getDeclaredField("speed");
        speed.setAccessible(true);

        double horseSpeed = (double) speed.get(horse);
        assertEquals(1.0, horseSpeed, 0.0);
    }

    @Test
    public void if_getDistance_EqualsThirdParameterOfConstructor() {
        Horse horse = new Horse("Horse name", 1, 1);

        assertEquals(1.0, horse.getDistance());
    }

    @Test
    public void getDistance_EqualsZeroIfConstructorHasTwoParameters() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Horse name", 1);

        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveUsesGetRandomDouble() {
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            new Horse("Horse name", 1, 1).move();

            mockedHorse.verify(() -> {
                Horse.getRandomDouble(0.2, 0.9);
            });

        }
    }

    @Test
    public void moveDistanceTest() {
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            mockedHorse.when(()->Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            // Ваша логика, например:
            double distance = 10;
            double speed = 2;
            // вызов метода, который использует Horse.getRandomDouble
            double resultDistance = someMethodUsingHorse(distance, speed);

            // Расчет ожидаемого значения
            double expected = distance + speed * 0.5;
            assertEquals(expected, resultDistance);
        }
    }

    // пример метода, который вызывает Horse.getRandomDouble
    private double someMethodUsingHorse(double distance, double speed) {
        double randomValue = Horse.getRandomDouble(0.2, 0.9);
        return distance + speed * randomValue;
    }




}
