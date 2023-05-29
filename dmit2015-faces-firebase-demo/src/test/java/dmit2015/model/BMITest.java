package dmit2015.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BMITest {
    @Test
    public void underweight(){
        BMI testCase = new BMI(100, 66);
        assertEquals(16.1, testCase.bmi(), 0.05);
        assertEquals("underweight", testCase.bmiCategory());
    }

    @Test
    public void normal(){
        BMI testCase = new BMI(140, 66);
        assertEquals(22.6, testCase.bmi(), 0.05);
        assertEquals("normal", testCase.bmiCategory());
    }

    @Test
    public void overweight(){
        BMI testCase = new BMI(175, 66);
        assertEquals(28.2, testCase.bmi(), 0.05);
        assertEquals("overweight", testCase.bmiCategory());
    }

    @Test
    public void obese(){
        BMI testCase = new BMI(200, 66);
        assertEquals(32.3, testCase.bmi(), 0.05);
        assertEquals("obese", testCase.bmiCategory());
    }

    @Test
    public void myBMI(){
        BMI testCase = new BMI(112, 67);
        assertEquals(17.5, testCase.bmi(), 0.05);
        assertEquals("underweight", testCase.bmiCategory());
    }
}
