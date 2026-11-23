import HES.Systems.EnvironmentalSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Environmental SyStems");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Grid containing the 8 bays
        JPanel grid = new JPanel(new GridLayout(2, 3, 10, 10));
        Controls control = new Controls();


        JLabel[] labels = new JLabel[control.wards.length];
        JPanel[] panels = new JPanel[control.wards.length];

        for (int i = 0; i < control.wards.length; i++) {
            Wards ward = control.wards[i];
            JPanel panel = new JPanel();
            panels[i] = panel;
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            labels[i] = new JLabel(ward.getName());
            labels[i].setText("<html>" + ward.getName() +
                    "<br> Ideal Temperature: " + String.format("%.1f", ward.getIdealTemp()) +
                    "°C<br> Current: " + String.format("%.1f", ward.getCurrentTemp()) + "°C" +
                    "Heating Status: "+ control.isHeatingOn(ward.getID())+ "</html>");
            panel.add(labels[i]);
            grid.add(panel);

        }

        JPanel Humidity = new JPanel();
        Humidity.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel Humid = new JLabel("Humidity");
        Humid.setText("<html> Inside: "+ String.format("%d",control.getInHumidity())+"%"
                + "<br> Outside: " + String.format("%d",control.getOutHumidity()) +
                "% <br> Dehumidifier status: "+ control.isDehumidifierOn()+ "<html> ");
        Humidity.add(Humid);
        grid.add(Humidity);

        JPanel Pollution = new JPanel();
        Pollution.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel pollution = new JLabel("Pollution");
        pollution.setText("<html> Pollution: "+ String.format("%.1f",control.getPollution()) +"%"
                + "<br> AirPurifier status: " + control.isAirPurifierOn() + "<html>");
        Pollution.add(pollution);
        grid.add(Pollution);


        frame.add(grid);
        frame.setVisible(true);
        final int[] counter = {0};
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.checkTemp();
                control.checkIfHumid();
                control.checkPollution();

                Humid.setText("<html> Inside: "+ String.format("%d",control.getInHumidity())+"%"
                        + "<br> Outside: " + String.format("%d",control.getOutHumidity()) +
                        "% <br> Dehumidifier status: "+ control.isDehumidifierOn()+ "<html>");

                int inside = control.getInHumidity();
                int outside = control.getOutHumidity();
                Humidity.setBackground( inside > (outside + 10) ? Color.RED : Color.WHITE);

                pollution.setText("<html> Pollution: "+ String.format("%.1f",control.getPollution()) +"%"
                        + "<br> AirPurifier status: " + control.isAirPurifierOn() + "<html>");

                Pollution.setBackground(
                        control.getPollution() > 5.0f
                                ? Color.RED : Color.WHITE
                );

                for (int i = 0; i < control.wards.length; i++) {
                    Wards ward = control.wards[i];
                    labels[i].setText("<html>" + ward.getName() +
                            "<br> Ideal Temperature: " + String.format("%.1f", ward.getIdealTemp()) +
                            "<br> Current: " + String.format("%.1f", ward.getCurrentTemp()) + "°C" +
                            "<br> Heating Status: "+ control.isHeatingOn(ward.getID())+ "</html>");

                    // Red when temperature is out of range
                    panels[i].setBackground(Math.abs(ward.getCurrentTemp()-ward.getIdealTemp())
                            > 0.5f ? Color.RED : Color.WHITE);

                }

                frame.repaint();
            }

        });

        timer.start();
    }

}

