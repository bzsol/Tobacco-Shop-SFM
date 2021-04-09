import hu.sfm.utils.Encryption;
import hu.sfm.utils.UserPassChecker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PasswordTest {
    @Test
    void isEncryptedNotEqual(){
        String testPassword = "Árvíztűrőtükörfúrógép";
        Encryption.titkosit(testPassword);

        assertNotEquals(Encryption.titkosit(testPassword), Encryption.titkosit(testPassword));
    }
    @ParameterizedTest
    @MethodSource("PasswordList")
    void isDecoded(String alap,String vart){
        assertEquals(Encryption.visszafejt(Encryption.titkosit(alap)),vart);
    }
    public static List<Arguments> PasswordList(){
        return Arrays.asList(
                Arguments.of("Kenyer911asd", "Kenyer911asd"),
                Arguments.of("dU&AEk-S?hmVc57T", "dU&AEk-S?hmVc57T"),
                Arguments.of("DAUDrXgPvMrUhFgHzrfyn","DAUDrXgPvMrUhFgHzrfyn")
        );
    }
    @ParameterizedTest
    @CsvSource({
            "AmINGLaterIDe,TRUE",
            "CureCtiCYclUsIdELuLA,TRUE",
            "MinaTontam,TRUE",
            "a9He7aE$qc,FALSE"

    })
    void isCorrectUsername(String username,String expected){
        assertEquals(UserPassChecker.UsernameCheck(username),expected.equals("TRUE"));
    }
    @ParameterizedTest
    @CsvSource({
            "AjvbSfabXgJXvQOk,FALSE",
            "OAODa5o7f4gh,TRUE",
            "IpN;t+mV[e@G,FALSE",
            "284121070744,FALSE",
            "MAMcYBZyX,FALSE"

    })
    void isCorrectPassword(String password,String expected){
        assertEquals(UserPassChecker.passCheck(password),expected.equals("TRUE"));
    }
}
