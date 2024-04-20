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
        this.gravity = new Vector(0, 9.81);
        this.damping = 0.1;
        this.elasticity = 0.7;
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
            Vector gravitationalForce = gravity.multiply(particle.mass);
            particle.applyForce(gravitationalForce);

            particle.update();
            checkBounds(particle);
        }

        for (int i = 0; i < particles.size(); i++) {
            for (int j = i + 1; j < particles.size(); j++) {
                Particle p1 = particles.get(i);
                Particle p2 = particles.get(j);
                if (areColliding(p1, p2)) {
                    resolveCollision(p1, p2);
                }
            }
        }
    }

    private void checkBounds(Particle particle) {
        if (particle.position.x - particle.radius < 0 || particle.position.x + particle.radius > width) {
            particle.velocity.x *= -elasticity;
            particle.velocity.x *= (1 - damping);
            particle.position.x = Math.max(particle.radius, Math.min(particle.position.x, width - particle.radius));
        }

        if (particle.position.y - particle.radius < 0 || particle.position.y + particle.radius > height) {
            particle.velocity.y *= -elasticity;
            particle.velocity.y *= (1 - damping);
            particle.position.y = Math.max(particle.radius, Math.min(particle.position.y, height - particle.radius));
        }
    }

    public void addParticle(Particle particle) {
        particles.add(particle);
    }

    private boolean areColliding(Particle p1, Particle p2) {
        double dx = p1.position.x - p2.position.x;
        double dy = p1. position.y - p2.position.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= (p1.radius + p2.radius);
    }
    
    private void resolveCollision(Particle p1, Particle p2) {
        Vector normal = new Vector(p2.position.x - p1.position.x, p2.position.y - p1.position.y);
        normal.normalize();

        Vector relativeVelocity = p2.velocity.subtract(p1.velocity);
        double velocityAlongNormal = relativeVelocity.dot(normal);

        if (velocityAlongNormal > 0) {
            return;
        }

        double restitution = Math.min(p1.elasticity, p2.elasticity);

        double j = -(1 + restitution) * velocityAlongNormal;
        j /= (1 / p1.mass + 1 / p2.mass);

        Vector impulse = normal.multiply(j);
        p1.velocity = p1.velocity.add(impulse.divide(p1.mass));
        p2.velocity = p2.velocity.subtract(impulse.divide(p2.mass));
    }
}
