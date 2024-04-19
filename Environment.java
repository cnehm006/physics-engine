import java.util.ArrayList;
import java.util.List;

public class Environment {
    List<Particle> particles;
    Vector gravity;
    double damping;
    double elasticity;
    double width;
    double height;

    public Environment(double width, double height) {
        this.particles = new ArrayList<>();
        this.gravity = new Vector(0, 9.81);  // Default gravity points downwards
        this.damping = 0.1;  // Default damping
        this.elasticity = 0.7;  // Default elasticity
        this.width = width;
        this.height = height;
    }

    public void setGravity(Vector gravity) {
        this.gravity = gravity;
    }

    public void setDamping(double damping) {
        this.damping = damping;
    }

    public void setElasticity(double elasticity) {
        this.elasticity = elasticity;
    }

    public void update() {
        for (Particle particle : particles) {
            // Apply gravity force
            particle.applyForce(new Vector(0, gravity.y * particle.mass));
            particle.update();
            checkBounds(particle);
        }
    }

    private void checkBounds(Particle particle) {
        if (particle.position.x - particle.radius < 0 || particle.position.x + particle.radius > width) {
            particle.velocity.x *= -elasticity;
            particle.position.x = Math.max(particle.radius, Math.min(particle.position.x, width - particle.radius));
        }
        if (particle.position.y - particle.radius < 0 || particle.position.y + particle.radius > height) {
            particle.velocity.y *= -elasticity;
            particle.position.y = Math.max(particle.radius, Math.min(particle.position.y, height - particle.radius));
        }
    }

    public void addParticle(Particle particle) {
        particles.add(particle);
    }
}
