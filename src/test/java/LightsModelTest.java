import org.example.LightsModel;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class LightsModelTest {

    @Test
    public void setColour(){
        LightsModel actual = new LightsModel(Color.red, 100, true, 600);
        Color expected = Color.red;

        Assert.assertEquals(expected, actual.getColour());
    }

    @Test
    public void setIntensity(){
        LightsModel actual = new LightsModel(Color.red, 55, true, 600);
        int expected = 55;

        Assert.assertEquals(expected, actual.getIntensity());
    }

    @Test
    public void setShine(){
        LightsModel actual = new LightsModel(Color.red, 55, false, 600);
        boolean expected = false;

        Assert.assertEquals(expected, actual.isShine());
    }

    @Test
    public void setShine2(){
        LightsModel newLight = new LightsModel();
        boolean expected = true;
        newLight.setShine(expected);
        boolean actual = newLight.isShine();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void nullConstructor(){
        LightsModel actual = new LightsModel();
        Color expected = Color.orange;

        Assert.assertEquals(expected, actual.getColour());

    }

    @Test
    public void testRandTimer(){
        LightsModel newLights = new LightsModel();
        int actual = newLights.getTimer();
        System.out.println(actual);

        Assert.assertTrue(actual < 1001 && actual > 99);
    }

    @Test
    public void setTimer(){
        LightsModel newLights = new LightsModel();
        int actual = newLights.randTimer();
        int expected = 500;
        newLights.setTimer(expected);

        Assert.assertTrue(expected != actual);
        Assert.assertEquals(expected, newLights.getTimer());

    }

    @Test
    public void setColour2(){
        LightsModel newLights = new LightsModel();
        Color expected = Color.black;
        newLights.setColour(expected);
        Color actual = newLights.getColour();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setIntensity2(){
        LightsModel newLights = new LightsModel();
        int expected = 20;
        newLights.setIntensity(expected);
        int actual = newLights.getIntensity();

        Assert.assertEquals(expected, actual);
    }

}
