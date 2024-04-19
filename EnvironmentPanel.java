import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnvironmentPanel extends JPanel {
    private Environment environment;

    // Constructor that takes an environment object
    public EnvironmentPanel(Environment environment) {
        this.environment = environment;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add a particle at the mouse click location with default radius and mass
                double radius = 10;  // Default radius
                double mass = 1;  // Default mass
                Particle newParticle = new Particle(new Vector(e.getX(), e.getY()), radius, mass);
                environment.addParticle(newParticle);
                repaint();  // Request a repaint to show the new particle
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Particle particle : environment.particles) {
            int x = (int) particle.position.x - (int) particle.radius;
            int y = (int) particle.position.y - (int) particle.radius;
            int diameter = (int) (particle.radius * 2);
            g.fillOval(x, y, diameter, diameter);
        }
    }

    // Setter method to update the environment
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
