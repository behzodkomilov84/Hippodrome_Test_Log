import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest extends Main {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Отключен по необходимости, пока не нужен запуск всем тестам")
    public void mainTest() throws Exception {

        main(new String[]{""});
    }

}