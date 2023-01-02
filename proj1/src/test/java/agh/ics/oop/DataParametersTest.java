package agh.ics.oop;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataParametersTest {
    List<String> testData = new ArrayList<>();
    DataParameters testConfig;

    void setTestConfig(){
        testData.add("SimulationName");
        testData.add("200");
        testData.add("200");
        testData.add("150");
        testData.add("25");
        testData.add("15");
        testData.add("70");
        testData.add("125");
        testData.add("50");
        testData.add("25");
        testData.add("5");
        testData.add("30");
        testData.add("15");
        testData.add("1");
        testData.add("2");
        testData.add("1");
        testData.add("0");

        testConfig = new DataParameters(testData);
    }

    @Test
    void getNameTest(){
        setTestConfig();
        assertEquals("SimulationName", testConfig.getName());
    }

    @Test
    void getHeightTest(){
        setTestConfig();
        assertEquals(200, testConfig.getHeight());

    }

    @Test
    void getWidthTestTest(){
        setTestConfig();
        assertEquals(200, testConfig.getWidth());
    }

    @Test
    void getStartingGrassTest(){
        setTestConfig();
        assertEquals(150, testConfig.getStartingGrass());
    }

    @Test
    void getEnergyFromGrassTest(){
        setTestConfig();
        assertEquals(25, testConfig.getEnergyFromGrass());
    }

    @Test
    void getNewGrassPerDayTest(){
        setTestConfig();
        assertEquals(15, testConfig.getNewGrassPerDay());
    }

    @Test
    void getStartingAnimalsTest(){
        setTestConfig();
        assertEquals(70, testConfig.getStartingAnimals());
    }

    @Test
    void getStratingAnimalsEnergyTest(){
        setTestConfig();
        assertEquals(125, testConfig.getStratingAnimalsEnergy());
    }

    @Test
    void getMinimumCopulateEnergyTest(){
        setTestConfig();
        assertEquals(50, testConfig.getMinimumCopulateEnergy());
    }

    @Test
    void getCopulateEnergyDecreaseTest(){
        setTestConfig();
        assertEquals(25, testConfig.getCopulateEnergyDecrease());
    }

    @Test
    void getMinimumMutationTest(){
        setTestConfig();
        assertEquals(5, testConfig.getMinimumMutation());
    }

    @Test
    void getMaximumMutationTest(){
        setTestConfig();
        assertEquals(30, testConfig.getMaximumMutation());

    }

    @Test
    void getGenomeLengthTest(){
        setTestConfig();
        assertEquals(15, testConfig.getGenomeLength());
    }

    @Test
    void getMapVariantTest(){
        setTestConfig();
        assertEquals(1, testConfig.getMapVariant());
    }

    @Test
    void getGrassGrowVariantTest(){
        setTestConfig();
        assertEquals(2, testConfig.getGrassGrowVariant());
    }

    @Test
    void getMutationVariantTest(){
        setTestConfig();
        assertEquals(1, testConfig.getMutationVariant());
    }

    @Test
    void getNextGenTypeTest(){
        setTestConfig();
        assertEquals(0, testConfig.getNextGenType());
    }

    @Test
    void getWalkEnergyDecreaseTest(){
        setTestConfig();
        assertEquals(1, testConfig.getWalkEnergyDecrease());
    }

    @Test
    void getMoveDelayTest(){
        setTestConfig();
        assertEquals(2000, testConfig.getMoveDelay());
    }
}
