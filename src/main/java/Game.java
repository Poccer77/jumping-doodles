import GameObjects.Platform;
import GameObjects.Player;
import java.util.ArrayList;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;

public class Game {

    private long window;
    private ArrayList<Platform> platforms;
    private Player player;
    private float jumpStrength;
    private float sidewaysMotion;
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

        returnPlatforms.add(new Platform(-0.05f, -0.9f, 0.1f, 0.05f));

        for (float f = -0.9f + gap; f < 2f; f += gap) {
            returnPlatforms.add(new Platform(null, f, null, 0.05f));
        }
        player = new Player(-0.025f, -0.8f, 0.05f, 0.1f);
        platforms = returnPlatforms;
        jumpStrength = gap / 12.5f;
    }

    public void loop() {

        int stateA = glfwGetKey(window, GLFW_KEY_A);
        int stateD = glfwGetKey(window, GLFW_KEY_D);
        if (stateA == GLFW_PRESS) sidewaysMotion -= 0.0005f;
        if (stateD == GLFW_PRESS) sidewaysMotion += 0.0005f;
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
            platforms.add(new Platform(null, null, null, 0.05f));
            distanceToNextPlatform = 0;
            System.out.println("+1");
        }

        if (platforms.get(0).getY() < -1.1f) {
            platforms.remove(0);
            System.out.println("-1");
        }
    }

    public void checkCollision() {

        for (Platform platform : platforms) {
            if (platform.getX() < player.getX() + player.getWidth() &&
                    player.getX() < platform.getX() + platform.getWidth() &&
                    player.getY() <= platform.getY() + platform.getHeight() &&
                    platform.getY() <= player.getY() &&
                    player.getUpwardsMomentum() <= 0) {

                player.setUpwardsMomentum(jumpStrength);
                player.setSidewaysMomentum(sidewaysMotion);
                sidewaysMotion = 0f;
            }
        }
    }
}

