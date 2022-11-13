import GameObjects.Platform;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Game {

    private long window;
    private ArrayList<Platform> platforms;
    public float gap = 0.5f;
    public final float SPEED;
    private float distanceToNextPlatform = 0;

    public Game(long window, float SPEED) {
        this.window = window;
        this.SPEED = SPEED;
        platforms = init();
    }

    private ArrayList<Platform> init() {
        ArrayList<Platform> returnPlatforms = new ArrayList<>();

        returnPlatforms.add(new Platform(-0.1f, -0.9f, 0.2f));

        for (float f = -0.9f + gap; f < 2f; f += gap) {
            returnPlatforms.add(new Platform(null, f, null));
        }

        return returnPlatforms;
    }

    public void loop() {

        this.scroll();
    }

    public void scroll() {
        for (Platform platform : platforms) {
            platform.setY(platform.getY() - SPEED);
            platform.draw();
        }

        distanceToNextPlatform += SPEED;

        if (distanceToNextPlatform >= gap) {
            platforms.add(new Platform(null, null, null));
            distanceToNextPlatform = 0;
            System.out.println("+1");
        }

        if (platforms.get(0).getY() < -1.1f) {
            platforms.remove(0);
            System.out.println("-1");
        }
    }
}

