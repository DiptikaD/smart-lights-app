package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LightsViewer extends JPanel {

    private List<LightsModel> lights;

    public LightsViewer(List<LightsModel> inputLights){
        lights = inputLights;
        setPreferredSize(new Dimension(100, 40));
        startBlinking();
    }

    public LightsViewer(){
        List<LightsModel> newLights = new ArrayList<>();
        newLights.add(new LightsModel(Color.red, 100, true, 750));
        newLights.add(new LightsModel(Color.orange, 100, true, 300));
        newLights.add(new LightsModel(Color.yellow, 100, true, 700));
        newLights.add(new LightsModel(Color.green, 100, true, 540));
        newLights.add(new LightsModel(Color.blue, 100, true, 620));
        lights = newLights;
        setPreferredSize(new Dimension(100, 40));
        startBlinking();
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

    public void startBlinking(){
        new Thread(() -> {
            while (true){
                for (LightsModel light : lights){
                    try{
                        Thread.sleep(light.getTimer());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    light.setShine(!light.isShine());
                    SwingUtilities.invokeLater(this::repaint);

                    try{
                        Thread.sleep(light.getTimer());
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }

//                    light.setIntensity(100);
                }
            }
        }).start();
    }

}
