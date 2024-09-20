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


}
