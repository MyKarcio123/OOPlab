package agh.ics.oop;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class BiomeTypeTest {
    @Test
    void toStringTest(){
        assertEquals("#00aeff", BiomeType.SNOWY.toString());
        assertEquals("#8aceed", BiomeType.ICY.toString());
        assertEquals("#2c3b30", BiomeType.BAGNO.toString());
        assertEquals("#4dff00", BiomeType.FOREST.toString());
        assertEquals("#024d16", BiomeType.JUNGLE.toString());
        assertEquals("#ffd900", BiomeType.DESERT.toString());
    }
}
