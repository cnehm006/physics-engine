public class Simulation {
    public static void main(String[] args) {
        Environment environment = new Environment(800, 600);  // Width and height of the environment

        environment.addParticle(new Particle(new Vector(100, 500), 10, 1, 0.7));
        environment.addParticle(new Particle(new Vector(200, 500), 15, 2, 0.7));
        environment.addParticle(new Particle(new Vector(300, 500), 20, 3, 0.7));

        while (true) {
            environment.update();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
        }
    }
}
