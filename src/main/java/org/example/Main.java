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
//            jframe.setSize(100,40);
            List<LightsModel> lights = new ArrayList<>();
            lights.add(new LightsModel(Color.green, 100, true, 320));
            lights.add(new LightsModel(Color.BLUE, 60, true, 530));
            lights.add(new LightsModel(Color.magenta, 100, true, 720));
            lights.add(new LightsModel());
            lights.add(new LightsModel(Color.red, 15, true, 400));

            LightsViewer lightsViewer = new LightsViewer(lights);
            jframe.add(lightsViewer, BorderLayout.CENTER);

//            nullary lightsViewer
//            jframe.add(new LightsViewer(), BorderLayout.CENTER);

            jframe.pack();
            jframe.setVisible(true);
        }) ;
    }
}