package GameObjects;

import static org.lwjgl.opengl.GL11.*;

public class Player {

    private float x, y, width, height, upwardsMomentum, sidewaysMomentum;

    public Player (float x, float y, float dimension) {
        this.x = x;
        this.y = y;
        this.width = dimension;
        this.height = dimension * 2;
        draw(0, 0);
    }

    public void draw(float XOffset, float YOffset) {
        glBegin(GL_QUADS);
        glVertex2f(x + XOffset, y + height + YOffset);
        glVertex2f(x + width + XOffset, y + height + YOffset);
        glVertex2f(x + width + XOffset, y + YOffset);
        glVertex2f(x + XOffset, y + YOffset);
        glEnd();

        x += XOffset;
        y += YOffset;
    }

    public void playerMovement(Float sidewaysMomentumOverwrite, Float upwardsMomentumOverwrite) {
        draw((sidewaysMomentumOverwrite == null) ? sidewaysMomentum : sidewaysMomentumOverwrite,
             (upwardsMomentumOverwrite == null) ? upwardsMomentum : upwardsMomentumOverwrite);
        upwardsMomentum = upwardsMomentum - 0.001f;
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

    public float getUpwardsMomentum() {return upwardsMomentum;}

    public void setUpwardsMomentum(float upwardsMomentum) {this.upwardsMomentum = upwardsMomentum;}

    public float getSidewaysMomentum() {return sidewaysMomentum;}

    public void setSidewaysMomentum(float sidewaysMomentum) {this.sidewaysMomentum = sidewaysMomentum;}
}
