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
    public void updateLightsTest(){
        LightsViewer tobeReplaced = new LightsViewer();
        List<LightsModel> lights = new ArrayList<>();
        lights.add(new LightsModel(Color.green, 100, true));
        lights.add(new LightsModel(Color.BLUE, 60, true));
        lights.add(new LightsModel(Color.magenta, 100, false));
        lights.add(new LightsModel());
        lights.add(new LightsModel(Color.magenta, 15, true));
        tobeReplaced.updateLights(lights);

        Assert.assertNotEquals(tobeReplaced, new LightsViewer());
    }
}
