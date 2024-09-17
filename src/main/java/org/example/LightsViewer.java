package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LightsViewer extends JPanel {

    private List<LightsModel> lights;

    public LightsViewer(List<LightsModel> inputLights){
        lights = inputLights;
        setPreferredSize(new Dimension(100, 40));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2g = (Graphics2D) g;

        int x = 10;
        int y = 10;

        for (LightsModel light: lights){
            g2g.setColor(light.getColour());

            g2g.fillOval(x, y, 5,5);

            AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, light.getIntensity() / 100.0f);
            g2g.setComposite(composite);

            x += 10;
        }
    }

    public void updateLights(List<LightsModel> newLights){
        lights = newLights;
        repaint();
    }

}
