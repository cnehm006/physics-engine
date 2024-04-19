public class Particle {
    Vector position;
    Vector velocity;
    Vector acceleration;
    double radius;
    double mass;

    public Particle(Vector position, double radius, double mass) {
        this.position = position;
        this.velocity = new Vector(0, 0);
        this.acceleration = new Vector(0, 0);
        this.radius = radius;
        this.mass = mass;
    }

    public void update() {
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.multiply(0);  // Reset acceleration after each update
    }

    public void applyForce(Vector force) {
        Vector f = force.divide(mass);
        acceleration.add(f);
    }
}
