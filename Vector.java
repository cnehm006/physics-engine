public class Vector {
    public double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector subtract(Vector other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vector multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector divide(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }
}
