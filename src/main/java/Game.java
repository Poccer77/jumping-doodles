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
    public float speed;
    private float distanceToNextPlatform = 0;

    public Game(long window, float speed) {
        this.window = window;
        this.speed = speed;
        this.init();
    }

    private void init() {

        ArrayList<Platform> returnPlatforms = new ArrayList<>();

        returnPlatforms.add(new Platform(-0.05f, -0.9f, 0.1f, 0.05f));

        for (float f = -0.9f + gap; f < 2f; f += gap) {
            returnPlatforms.add(new Platform(null, f, null, 0.05f));
        }
        player = new Player(-0.025f, -0.8f, 0.05f);
        platforms = returnPlatforms;
        jumpStrength = gap / 10f;
    }

    public void loop() {

        int stateA = glfwGetKey(window, GLFW_KEY_A);
        int stateD = glfwGetKey(window, GLFW_KEY_D);
        if (stateA == GLFW_PRESS) sidewaysMotion -= 0.0005f;
        if (stateD == GLFW_PRESS) sidewaysMotion += 0.0005f;
        if (player.getY() >= 0.5f && player.getUpwardsMomentum() >= 0) {
            this.scroll(player.getUpwardsMomentum());
            player.playerMovement(null, 0f);
        } else {
            this.scroll(speed);
            player.playerMovement(null, null);
        }
        checkCollision();
        if (endGame()) {
            speed = 0f;
        }

    }

    public void scroll(float speed) {

        for (Platform platform : platforms) {
            platform.setY(platform.getY() - speed);
            platform.draw();
        }

        distanceToNextPlatform += speed;

        if (distanceToNextPlatform >= gap) {
            platforms.add(new Platform(null, null, null, 0.05f));
            distanceToNextPlatform = 0;
        }

        if (platforms.get(0).getY() < -1.1f) {
            platforms.remove(0);
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

        if (player.getX() + player.getWidth() <= -1.01f) {
            player.setX(1.0f);
        }

        if (player.getX() >= 1.01f) {
            player.setX(-1.0f - player.getWidth());
        }
    }

    public boolean endGame() {
        if (player.getY() + player.getHeight() <= -1.1f) {
            return true;
        }
        return false;
    }
}

