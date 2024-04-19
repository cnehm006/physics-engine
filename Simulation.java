public class Simulation {
    public static void main(String[] args) {
        Environment environment = new Environment(800, 600);  // Width and height of the environment

        // Add initial particles
        environment.addParticle(new Particle(new Vector(100, 500), 10, 1));
        environment.addParticle(new Particle(new Vector(200, 500), 15, 2));
        environment.addParticle(new Particle(new Vector(300, 500), 20, 3));

        // Start the simulation loop
        while (true) {
            environment.update();
            // Add code here to delay the loop, handle rendering, and check for user interactions
        }
    }
}
