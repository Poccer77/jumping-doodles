package GameObjects;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import Utilities.Tools;
import Utilities.Values;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Platform {

    private final float x;
    private float y;
    private final float width;
    private final float height;
    private float[] bonusAnimation = new float[3];
    private boolean givePoints = true;
    private float[] color = {1, 1, 1};

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {return height;}

    public boolean isGivePoints() {return givePoints;}

    public Platform(Float x, Float y, Float width, Float height) {

        this.width = (width == null) ? ThreadLocalRandom.current().nextFloat(Values.PLATFORM_WIDTH_LB.value, Values.PLATFORM_WIDTH_UB.value) : width;
        this.height = (height == null) ? Values.PLATFORM_HEIGHT.value : height;
        this.x = (x == null) ? ThreadLocalRandom.current().nextFloat(-1f, 1f - this.width) : x;
        this.y = (y == null) ? 1.1f : y;

        draw();
    }

    public void draw() {
        glBegin(GL_QUADS);
        glColor4f(color[0], color[1] , color[2], 0);
        glVertex2f(x, y + height);
        glColor4f(color[0], color[1] , color[2], 0);
        glVertex2f(x + width, y + height);
        glColor4f(color[0], color[1] , color[2], 0);
        glVertex2f(x + width, y);
        glColor4f(color[0], color[1] , color[2], 0);
        glVertex2f(x, y);
        glEnd();
        System.out.println(color[0]);

        if(!givePoints & color[0] <= 0.7 & color[1] >= 0.7 & color[2] <= 0.7) {
            color[0] += bonusAnimation[0];
            color[1] += bonusAnimation[1];
            color[2] += bonusAnimation[2];
        }
    }

    public void deactivate(float bonus) {
        float green = 1 - (bonus - 0.5f);
        System.out.println(bonus + ", " + green);
        if (bonus > 1) {
            color[0] = green;
            color[2] = green;
        }
        bonusAnimation[0] = (0.7f - color[0]) / 50f;
        bonusAnimation[1] = (0.7f - color[1]) / 50f;
        bonusAnimation[2] = (0.7f - color[2]) / 50f;
        givePoints = !givePoints;
    }
}
