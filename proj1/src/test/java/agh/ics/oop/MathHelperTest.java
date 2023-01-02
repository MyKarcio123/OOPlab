package agh.ics.oop;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MathHelperTest {
    float a1 = 1, a2 = 7, a3 = 4;
    float b1 = 6, b2 = 10, b3 = 5;
    float v1 = 3, v2 = 12, v3 = 2;
    @Test
    void InvLerpTest(){
        assertEquals((v1 - a1) / (b1 - a1), MathHelper.InvLerp(a1, b1, v1));
        assertEquals((v2 - a2) / (b2 - a2), MathHelper.InvLerp(a2, b2, v2));
        assertEquals((v3 - a3) / (b3 - a3), MathHelper.InvLerp(a3, b3, v3));
    }

    @Test
    void LerpTest(){
        float newVal1 = (1.0f-v1) * a1 + b1 * v1;
        float newVal2 = (1.0f-v2) * a2 + b2 * v2;
        float newVal3 = (1.0f-v3) * a3 + b3 * v3;

        assertEquals(6, MathHelper.Clamp(a1, b1, newVal1));
        assertEquals(10, MathHelper.Clamp(a2, b2, newVal2));
        assertEquals(5, MathHelper.Clamp(a3, b3, newVal3));
    }

    @Test
    void ClampTest(){
        assertEquals(3, MathHelper.Clamp(a1, b1, v1));
        assertEquals(10, MathHelper.Clamp(a2, b2, v2));
        assertEquals(4, MathHelper.Clamp(a3, b3, v3));
    }
}
