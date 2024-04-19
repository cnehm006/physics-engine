public class Particle {
    Vector position;
    Vector velocity;
    Vector acceleration;
    double radius;
    double mass;
    double elasticity;

    public Particle(Vector position, double radius, double mass, double elasticity) {
        this.position = position;
        this.radius = radius;
        this.mass = mass;
        this.elasticity = elasticity;
        this.velocity = new Vector(0, 0);
        this.acceleration = new Vector(0, 0);
    }

    public void update() {
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.multiply(0);
    }

    public void applyForce(Vector force) {
        Vector f = force.divide(mass);
        acceleration.add(f);
    }

    public boolean collidesWith(Particle other) {
        double dx = this.position.x - other.position.x;
        double dy = this.position.y - other.position.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= (this.radius + other.radius);
    }

    public void resolveCollision(Particle other) {
        Vector collisionVector = other.position.subtract(this.position).normalize();
    
        Vector relativeVelocity = this.velocity.subtract(other.velocity);
        double velocityAlongNormal = relativeVelocity.dot(collisionVector);
    
        if (velocityAlongNormal > 0) {
            return;
        }
    
        double e = Math.min(this.elasticity, other.elasticity);
        double j = -(1 + e) * velocityAlongNormal;
        j /= (1 / this.mass + 1 / other.mass);
    
        Vector impulse = collisionVector.multiply(j);
        this.velocity = this.velocity.add(impulse.divide(this.mass));
        other.velocity = other.velocity.subtract(impulse.divide(other.mass));
    }
    
    
}
