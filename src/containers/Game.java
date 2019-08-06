package containers;

public class Game {
    private User user;
    private Spaceship spaceship;

    public Game(User user) {
        this.user = user;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public User getUser() {
        return user;
    }
}
