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


public class UserPassCheckerTest {
    @ParameterizedTest
    @CsvSource({
            "AmINGLaterIDe,TRUE",
            "CureCtiCYclUsIdELuLA,FALSE",
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
