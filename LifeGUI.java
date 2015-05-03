package lifegame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author ifedko
 */
public class LifeGUI {
    
    JFrame jframe;

    LifeController controller;

    LifeArea lifeArea;

    LifeAreaView lifeAreaView;

    JButton jProcessButton;

    JButton jFillButton;

    Timer timer;

    long counter;

    int delay;

    boolean isInProgress = false;

    private ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            update();
        }
    };

    private ActionListener processButtonClicked = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            if (isInProgress) {
                stop();
            } else {
                start();
            }
        }
    };

    private ActionListener fillButtonClicked = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            if (!isInProgress) {
                fill();
            }
        }
    };

    LifeGUI(LifeArea area, LifeController controller) {
        this.lifeArea = area;
        this.lifeAreaView = new LifeAreaView();
        this.controller = controller;

        jframe = new JFrame("Conway's Game of Life");
        jframe.setSize(500, 500);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        counter = 1L;
        delay = 600;
        timer = new Timer(delay, taskPerformer);

        fill();

        jProcessButton = new JButton("Start");
        jProcessButton.addActionListener(processButtonClicked);
        jFillButton = new JButton("Fill random");
        jFillButton.addActionListener(fillButtonClicked);

        jframe.add(BorderLayout.CENTER, lifeAreaView);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(jProcessButton);
        buttonPanel.add(jFillButton);
        jframe.add(BorderLayout.SOUTH, buttonPanel);

        jframe.setVisible(true);
    }

    public void update() {
        counter++;
        this.lifeArea = controller.updateArea(this.lifeArea);
        LifeAreaView areaView = new LifeAreaView();
        areaView.setArea(this.lifeArea);
        jframe.getContentPane().repaint();
    }

    public void start() {
        isInProgress = true;
        jProcessButton.setText("Stop");
        jFillButton.setEnabled(false);
        timer.start();
    }

    public void stop() {
        isInProgress = false;
        jProcessButton.setText("Start");
        jFillButton.setEnabled(true);
        timer.stop();
    }

    public void fill() {
        this.lifeArea = this.controller.fillArea(this.lifeArea);
        lifeAreaView.setArea(this.lifeArea);
        jframe.getContentPane().repaint();
    }

}