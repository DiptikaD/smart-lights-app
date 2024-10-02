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
        setPreferredSize(new Dimension(100, 100));
        startBlinking();
    }

    public LightsViewer(){
        List<LightsModel> newLights = new ArrayList<>();
        newLights.add(new LightsModel(Color.red, 100, true, 750, 50));
        newLights.add(new LightsModel(Color.orange, 100, true, 300, 50));
        newLights.add(new LightsModel(Color.yellow, 100, true, 700, 50));
        newLights.add(new LightsModel(Color.green, 100, true, 540, 50));
        newLights.add(new LightsModel(Color.blue, 100, true, 620, 50));
        newLights.add(new LightsModel(Color.CYAN, 100, true, 540, 50));
        newLights.add(new LightsModel(Color.white, 100, true, 620, 50));

        lights = newLights;
        setPreferredSize(new Dimension(100, 40));
        startBlinking();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2g = (Graphics2D) g;

        int x = 10;
        int y = 10;

        for (LightsModel light: lights){
            g2g.setColor(light.getColour());
            int currentSize = light.getSize();

            g2g.fillOval(x, y, currentSize, currentSize);

            AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, light.getIntensity() / 100.0f);
            g2g.setComposite(composite);

            x += currentSize + 20;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LightsViewer::createAndShowGUI);
    }

    private static void createAndShowGUI (){
        JFrame frame = new JFrame("pretty lights");
        LightsViewer lightsViewer = new LightsViewer();
        JPanel controlPanel = createControlPanel(lightsViewer);

        frame.setLayout(new BorderLayout());
        frame.add(lightsViewer, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static JPanel createControlPanel(LightsViewer lightsViewer){
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(0,2));

        //select light
        JComboBox<String> lightSelector = new JComboBox<>();
        for (int i = 0; i < lightsViewer.getLengthOfLights(); i++){
            lightSelector.addItem("Light " + (i + 1));
        }
        controlPanel.add(new JLabel("Select Light: "));
        controlPanel.add(lightSelector);

        //turn off all lights
        JButton turnOff = new JButton();
        controlPanel.add(new JLabel("Turn off Lights!"));
        controlPanel.add(turnOff);

        //select colour
        String[] colourNames = {"Red", "Orange", "Pink", "Green", "Blue", "White"};
        Color[] colours = {Color.red, Color.orange, Color.pink, Color.green, Color.blue, Color.white};

        JComboBox<String> colorSelector = new JComboBox<>(colourNames);
        controlPanel.add(new JLabel("Colour: "));
        controlPanel.add(colorSelector);

        //intensity slider
        JSlider intensitySlide = new JSlider(0, 100, 100);
        intensitySlide.setMajorTickSpacing(10);
        intensitySlide.setPaintTicks(true);
        controlPanel.add(new JLabel("Intensity: "));
        controlPanel.add(intensitySlide);

        //size adjuster
        //

        //timer slider
        JSlider timeSlider = new JSlider(100, 1000, 750);
        timeSlider.setMajorTickSpacing(100);
        timeSlider.setPaintTicks(true);
        controlPanel.add(new JLabel("Blinking: "));
        controlPanel.add(timeSlider);

        JButton apply = new JButton("Apply changes");
        controlPanel.add(apply);

        lightSelector.addActionListener(e -> {
            int selectedIndex = lightSelector.getSelectedIndex();
            LightsModel selectedLight = lightsViewer.lights.get(selectedIndex);

            colorSelector.setSelectedItem(getColourName(selectedLight.getColour(), colours, colourNames));
            intensitySlide.setValue(selectedLight.getIntensity());
            timeSlider.setValue(selectedLight.getTimer());
        });

        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectIndex = lightSelector.getSelectedIndex();
                Color selectedColor = colours[colorSelector.getSelectedIndex()];
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

        turnOff.addActionListener(e -> {
            for (LightsModel light : lightsViewer.lights){
                light.setShine(false);
            }
            lightsViewer.repaint();
        });
        return controlPanel;
    }

    private static String getColourName(Color colour, Color[] colours, String[] colourNames){
        for (int i = 0; i<colours.length; i++){
            if (colours[i].equals(colour)){
                return colourNames[i];
            }
        }
        return "";
    }
}
