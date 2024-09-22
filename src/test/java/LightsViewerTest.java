import org.example.LightsModel;
import org.example.LightsViewer;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LightsViewerTest {

    @Test
    public void extendsJPanelTest(){
        Assert.assertTrue(new LightsViewer() instanceof JPanel);
    }

    @Test
    public void lightViewerConstructor(){
        List<LightsModel> lights = new ArrayList<>();
        lights.add(new LightsModel(Color.pink, 100, true, 320));
        lights.add(new LightsModel(Color.cyan, 60, true, 530));
        LightsViewer lightview = new LightsViewer(lights);

        int actual = lightview.getLengthOfLights();
        int expected = 2;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void lightsViewerNullConstructor(){
        LightsViewer lightview = new LightsViewer();

        int actual = lightview.getLengthOfLights();
        int expected = 5;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateLightsTest(){
        LightsViewer tobeReplaced = new LightsViewer();
        List<LightsModel> lights = new ArrayList<>();
        lights.add(new LightsModel(Color.green, 100, true, 600));
        lights.add(new LightsModel(Color.BLUE, 60, true, 290));
        lights.add(new LightsModel(Color.magenta, 100, false, 630));
        lights.add(new LightsModel());
        lights.add(new LightsModel(Color.magenta, 15, true, 810));
        tobeReplaced.updateLights(lights);

        Assert.assertNotEquals(tobeReplaced, new LightsViewer());
    }

}
