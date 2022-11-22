package GameObjects;

import static org.lwjgl.opengl.GL11.*;

public class Player {

    private float x, y, width, height;

    public Player (float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        draw(0, 0);
    }

    public void draw(float XOffset, float YOffset) {
        glBegin(GL_QUADS);
        glVertex2f(x + XOffset, y + height + YOffset);
        glVertex2f(x + width + XOffset, y + height + YOffset);
        glVertex2f(x + width + XOffset, y + YOffset);
        glVertex2f(x + XOffset, y + YOffset);
        glEnd();
    }

    public void PlayerMovement(float sidewaysMotion, float upwardImpulse) {
        draw(sidewaysMotion, upwardImpulse);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
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

    public float getHeight() {
        return height;
    }
}
