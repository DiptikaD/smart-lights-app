import org.example.LightsModel;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class LightsModelTest {

    @Test
    public void setColour(){
        LightsModel actual = new LightsModel(Color.red, 100, true);
        Color expected = Color.red;

        Assert.assertEquals(expected, actual.getColour());
    }

    @Test
    public void setIntensity(){
        LightsModel actual = new LightsModel(Color.red, 55, true);
        int expected = 55;

        Assert.assertEquals(expected, actual.getIntensity());
    }

    @Test
    public void setShine(){
        LightsModel actual = new LightsModel(Color.red, 55, false);
        boolean expected = false;

        Assert.assertEquals(expected, actual.isShine());
    }

    @Test
    public void nullConstructor(){
        LightsModel actual = new LightsModel();
        Color expected = Color.orange;

        Assert.assertEquals(expected, actual.getColour());

    }


}
