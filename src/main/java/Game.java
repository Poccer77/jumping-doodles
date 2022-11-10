import GameObjects.Platform;

import java.util.ArrayList;


public class Game {

    private long window;
    private ArrayList<Platform> platforms = new ArrayList<>();
    public float gap = 0.5f;
    public final float SPEED;
    private float distanceToNextPlatform = 0;

    public Game(long window, float SPEED) {
        this.window = window;
        this.SPEED = SPEED;
        platforms.add(new Platform());
    }

    public void loop() {

    }

    public void scroll() {
        for (Platform platform : platforms) {
            platform.setY(platform.getY() - SPEED);
            platform.draw();
        }

        distanceToNextPlatform += SPEED;

        if (distanceToNextPlatform >= gap) {
            platforms.add(new Platform());
            distanceToNextPlatform = 0;
        }

        if (platforms.get(0).getY() < -0.9f) {
            platforms.remove(0);
        }
    }
}

