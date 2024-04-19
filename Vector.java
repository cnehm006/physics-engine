public class Vector {
    public double x;
    public double y;

    // Constructor to initialize the vector
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Method to add two vectors
    public Vector add(Vector v) {
        return new Vector(this.x + v.x, this.y + v.y);
    }

    // Method to subtract two vectors
    public Vector subtract(Vector v) {
        return new Vector(this.x - v.x, this.y - v.y);
    }

    // Method to multiply the vector by a scalar
    public Vector multiply(double scalar) {
        return new Vector(this.x * scalar, this.y * scalar);
    }

    // Method to calculate the magnitude of the vector
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    // Method to normalize the vector
    public Vector normalize() {
        double mag = magnitude();
        return new Vector(x / mag, y / mag);
    }
}
