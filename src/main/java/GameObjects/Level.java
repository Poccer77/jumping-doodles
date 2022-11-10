package GameObjects;

import java.util.ArrayList;

public class Level {

    private ArrayList<Platform> platforms = new ArrayList<>();
    public float gap;
    public final float SPEED;
    private float distanceToNextPlatform = 0;

    public Level (float gap, float SPEED) {
        this.gap = gap;
        this.SPEED = SPEED;
        platforms.add(new Platform());
    }

    public void scroll() {
        for (Platform platform : platforms) {
            platform.setY(platform.getY() - SPEED);
            platform.draw();
        }

        distanceToNextPlatform += SPEED;

        if (distanceToNextPlatform >= gap) {
            platforms.add(new Platform());
        }

        if (platforms.get(0).getY() < -1.1f) {
            platforms.remove(0);
        }
    }

}
