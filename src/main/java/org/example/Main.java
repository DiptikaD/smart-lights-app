package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame jframe = new JFrame("pretty lights");
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            List<LightsModel> lights = new ArrayList<>();
            lights.add(new LightsModel(Color.pink, 100, true, 320, 50));
            lights.add(new LightsModel(Color.cyan, 60, true, 530, 50));
            lights.add(new LightsModel(Color.magenta, 100, true, 720, 50));
            lights.add(new LightsModel());
            lights.add(new LightsModel(Color.red, 15, true, 400, 50));

            LightsViewer lightsViewer = new LightsViewer(lights);
            jframe.add(lightsViewer, BorderLayout.CENTER);
            jframe.pack();
            jframe.setVisible(true);
        }) ;
    }
}