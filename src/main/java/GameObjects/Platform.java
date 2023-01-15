package GameObjects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import Utilities.Values;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Platform {

    private float x, y, width, height;
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {return height;}

    public void setY(float y) {
        this.y = y;
    }

    public Platform(Float x, Float y, Float width, Float height) {

        this.width = (width == null) ? ThreadLocalRandom.current().nextFloat(Values.PLATFORM_WIDTH_LB.value, Values.PLATFORM_WIDTH_UB.value) : width;
        this.height = (height == null) ? Values.PLATFORM_HEIGHT.value : height;
        this.x = (x == null) ? ThreadLocalRandom.current().nextFloat(-1f, 1f - this.width) : x;
        this.y = (y == null) ? 1.1f : y;

        draw();
    }

    public void draw() {
        glBegin(GL_QUADS);
        glVertex2f(x, y + height);
        glVertex2f(x + width, y + height);
        glVertex2f(x + width, y);
        glVertex2f(x, y);
        glEnd();
    }
}
