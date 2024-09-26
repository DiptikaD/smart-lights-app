package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                }
            }
        }).start();
    }

    public int getLengthOfLights(){
        return lights.size();
    }

    private static JPanel createControlPanel(LightsViewer lightsViewer){
        JPanel controlPanel = new JPanel();

        //select light
        JComboBox<String> lightSelector = new JComboBox<>();
        for (int i = 0; i < lightsViewer.getLengthOfLights(); i++){
            lightSelector.addItem("Light " + (i+1));
        }
        controlPanel.add(new JLabel("Select Light"));
        controlPanel.add(lightSelector);

        //select colour
        JComboBox<Color> colorSelector = new JComboBox<>(new Color[]{Color.red, Color.orange, Color.pink, Color.green, Color.blue});
        controlPanel.add(colorSelector);

        //intensity slider
        JSlider intensitySlide = new JSlider(0, 100, 100);
        intensitySlide.setMajorTickSpacing(10);
        intensitySlide.setPaintTicks(true);
        controlPanel.add(new JLabel("Intensity: "));
        controlPanel.add(intensitySlide);

        //timer slider
        JSlider timeSlider = new JSlider(100, 1000, 750);
        timeSlider.setMajorTickSpacing(10);
        timeSlider.setPaintTicks(true);
        controlPanel.add(new JLabel("Blinking: "));
        controlPanel.add(timeSlider);

        JButton apply = new JButton("Apply changes");
        controlPanel.add(apply);

        lightSelector.addActionListener(e -> {
            int selectedIndex = lightSelector.getSelectedIndex();
            LightsModel selectedLight = lightsViewer.lights.get(selectedIndex);

            colorSelector.setSelectedItem(selectedLight.getColour());
            intensitySlide.setValue(selectedLight.getIntensity());
            timeSlider.setValue(selectedLight.getTimer());
        });

        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectIndex = lightSelector.getSelectedIndex();
                Color selectedColor = (Color) colorSelector.getSelectedItem();
                int selectedIntensity = intensitySlide.getValue();
                int selectedTimer = timeSlider.getValue();

                if (selectIndex >=0){
                    LightsModel light = lightsViewer.lights.get(selectIndex);
                    light.setColour(selectedColor);
                    light.setIntensity(selectedIntensity);
                    light.setTimer(selectedTimer);
                    lightsViewer.repaint();
                }
            }
        });
        return controlPanel;
    }
}
