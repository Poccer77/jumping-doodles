import GameObjects.Platform;
import GameObjects.Player;
import java.util.ArrayList;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import Utilities.Tools;
import Utilities.Values;
import org.lwjgl.opengl.GL;
import org.lwjgl.stb.STBTruetype;

public class Game {

    private final long window;
    private ArrayList<Platform> platforms;
    private Player player;
    private float jumpStrength;
    public float gap = 0.5f;
    public float speed;
    public float score = 0;
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
        jumpStrength = Values.PLAYER_JUMP_STRENGTH.value;
    }

    public void loop() {

        int stateA = glfwGetKey(window, GLFW_KEY_A);
        int stateD = glfwGetKey(window, GLFW_KEY_D);
        if (stateA == GLFW_PRESS && player.getSidewaysAccu() > -Values.PLAYER_HORIZONTAL_MAX_SPEED.value) {
            player.setSidewaysAccu(player.getSidewaysAccu() - 0.0005f);
            player.setSidewaysAccu((float)(Math.round(player.getSidewaysAccu() * 10000)) / 10000);
        }
        if (stateD == GLFW_PRESS && player.getSidewaysAccu() < Values.PLAYER_HORIZONTAL_MAX_SPEED.value) {
            player.setSidewaysAccu(player.getSidewaysAccu() + 0.0005f);
            player.setSidewaysAccu((float)(Math.round(player.getSidewaysAccu() * 10000)) / 10000);
        }
        if (player.getY() >= 0.5f && player.getUpwardsMomentum() >= 0) {
            this.scroll(player.getUpwardsMomentum());
            score += player.getUpwardsMomentum();
            player.playerMovement(null, 0f);
        } else {
            this.scroll(speed);
            score += speed;
            player.playerMovement(null, null);
        }
        checkCollision();
        glfwSetWindowTitle(window, Float.toString((float)(Math.round(score * 100)) / 10));
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
                platform.getY() <= player.getY() && player.getUpwardsMomentum() <= 0) {

                    player.setUpwardsMomentum(jumpStrength);
                    player.setSidewaysMomentum(player.getSidewaysAccu());
                    player.setSidewaysAccu(0);
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
        return player.getY() + player.getHeight() <= -1.1f;
    }
}

