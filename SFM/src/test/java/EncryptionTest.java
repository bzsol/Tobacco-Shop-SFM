import hu.sfm.utils.Encryption;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EncryptionTest {
    @Test
    void isEncryptedNotEqual(){
        String testPassword = "Árvíztűrőtükörfúrógép";
        Encryption.titkosit(testPassword);

        assertNotEquals(Encryption.titkosit(testPassword), Encryption.titkosit(testPassword));
    }
    @ParameterizedTest
    @MethodSource("PasswordList")
    void isDecoded(String test,String expected){
        assertEquals(Encryption.visszafejt(Encryption.titkosit(test)),expected);
    }
    public static List<Arguments> PasswordList(){
        return Arrays.asList(
                Arguments.of("Kenyer911asd", "Kenyer911asd"),
                Arguments.of("dU&AEk-S?hmVc57T", "dU&AEk-S?hmVc57T"),
                Arguments.of("DAUDrXgPvMrUhFgHzrfyn","DAUDrXgPvMrUhFgHzrfyn")
        );
    }
}
