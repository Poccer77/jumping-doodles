package GameObjects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Platform {

    private float x;
    private float y;
    private float width;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Platform() {

        width = ThreadLocalRandom.current().nextFloat(0.2f, 0.3f);
        x = ThreadLocalRandom.current().nextFloat(-1f, 1f - width);
        y = 2f;

        draw();
    }

    public void draw() {
        glBegin(GL_QUADS);
        glVertex2f(x, y + 0.1f);
        glVertex2f(x + width, y + 0.1f);
        glVertex2f(x + width, y);
        glVertex2f(x, y);
        glEnd();
    }
}
