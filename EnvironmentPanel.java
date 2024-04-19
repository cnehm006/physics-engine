import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnvironmentPanel extends JPanel {
    private Environment environment;

    public EnvironmentPanel(Environment environment) {
        this.environment = environment;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double radius = 10;
                double mass = 1;
                double elasticity = 0.7;
                Particle newParticle = new Particle(new Vector(e.getX(), e.getY()), radius, mass, elasticity);
                environment.addParticle(newParticle);
                repaint();
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

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
