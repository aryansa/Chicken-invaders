package containers;

public class Game {
    private User user;
    private Spaceship spaceship;
    private boolean startWave = false;
    private long spendTime = 0;

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

    public boolean isStartWave() {
        return getSpendTime() > 5;
    }

    public void increaseTime() {
        spendTime++;
    }

    public long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(long spendTime) {
        this.spendTime = spendTime;
    }

    public void setStartWave(boolean startWave) {
        this.startWave = startWave;
    }
}
