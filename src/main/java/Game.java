import GameObjects.Platform;
import GameObjects.Player;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Game {

    private long window;
    private ArrayList<Platform> platforms;
    private Player player;
    private float jumpStrength;
    public float gap = 0.5f;
    public final float SPEED;
    private float distanceToNextPlatform = 0;

    public Game(long window, float SPEED) {
        this.window = window;
        this.SPEED = SPEED;
        this.init();
    }

    private void init() {

        ArrayList<Platform> returnPlatforms = new ArrayList<>();

        returnPlatforms.add(new Platform(-0.1f, -0.9f, 0.2f, 0.1f));

        for (float f = -0.9f + gap; f < 2f; f += gap) {
            returnPlatforms.add(new Platform(null, f, null, null));
        }
        player = new Player(-0.05f, -0.8f, 0.1f, 0.2f);
        platforms = returnPlatforms;
        jumpStrength = 0.05f;
    }

    public void loop() {

        this.scroll();
        player.playerMovement();
        checkCollision();
    }

    public void scroll() {

        for (Platform platform : platforms) {
            platform.setY(platform.getY() - SPEED);
            platform.draw();
        }

        distanceToNextPlatform += SPEED;

        if (distanceToNextPlatform >= gap) {
            platforms.add(new Platform(null, null, null, null));
            distanceToNextPlatform = 0;
            System.out.println("+1");
        }

        if (platforms.get(0).getY() < -1.1f) {
            platforms.remove(0);
            System.out.println("-1");
        }
    }

    public float checkCollision() {

        float momentum = 0f;

        for (Platform platform : platforms) {
            if (platform.getX() < player.getX() + player.getWidth() &&
                player.getX() < platform.getX() + platform.getWidth() &&
                player.getY() <= platform.getY() + platform.getHeight() &&
                platform.getY() <= player.getY() &&
                player.getMomentum() <= 0) {

                player.setMomentum(jumpStrength);
            };
        }
        return 0f;
    }
}

