import hu.sfm.utils.CurrencyManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class CurrencyManagerTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/testfile/currency.csv")
    void IsCreatePatternPass(String number,String pattern){

        Assertions.assertEquals(CurrencyManager.createPattern(number),pattern);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testfile/currency.csv")
    void IsRemoveTextFieldPatternPass(String result,String value){
        Assertions.assertEquals(CurrencyManager.removeTextFieldPattern(value),result);
    }


}
