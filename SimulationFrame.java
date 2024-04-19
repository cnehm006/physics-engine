import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {
    private Environment environment;
    private EnvironmentPanel panel;

    public SimulationFrame() {
        setTitle("Physics Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        environment = new Environment(800, 600);  // Assuming width and height
        panel = new EnvironmentPanel(environment);
        panel.setPreferredSize(new Dimension(800, 600));
        add(panel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        setupControls(controlPanel);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);

        startSimulation();
    }

    private void setupControls(JPanel panel) {
        panel.setLayout(new GridLayout(4, 2, 5, 5)); // Layout for labels and text fields

        // Gravity control
        JLabel gravityLabel = new JLabel("Gravity:");
        JTextField gravityField = new JTextField("9.81");
        panel.add(gravityLabel);
        panel.add(gravityField);

        // Damping control
        JLabel dampingLabel = new JLabel("Damping:");
        JTextField dampingField = new JTextField("0.1");
        panel.add(dampingLabel);
        panel.add(dampingField);

        // Elasticity control
        JLabel elasticityLabel = new JLabel("Elasticity:");
        JTextField elasticityField = new JTextField("0.7");
        panel.add(elasticityLabel);
        panel.add(elasticityField);

        // Apply button to update values
        JButton applyButton = new JButton("Apply Changes");
        applyButton.addActionListener(e -> {
            double gravity = Double.parseDouble(gravityField.getText());
            double damping = Double.parseDouble(dampingField.getText());
            double elasticity = Double.parseDouble(elasticityField.getText());
            environment.setGravity(new Vector(0, gravity));
            environment.setDamping(damping);
            environment.setElasticity(elasticity);
        });
        panel.add(new JLabel()); // Placeholder to align button in the grid
        panel.add(applyButton);
    }

    private void startSimulation() {
        new Thread(() -> {
            while (true) {
                environment.update();
                panel.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new SimulationFrame();
    }
}
