package GameObjects;

import Utilities.Tools;
import Utilities.Values;

import static org.lwjgl.opengl.GL11.*;

public class Player {

    private float x;
    private float y;
    private float width;
    private float height;
    private float upwardsMomentum;
    private float sidewaysMomentum;



    private float sidewaysAccu;

    public Player (float x, float y, float dimension) {
        this.x = x;
        this.y = y;
        this.width = Values.PLAYER_WIDTH.value;
        this.height = Values.PLAYER_HEIGHT.value;
        draw(0, 0);
    }

    public void draw(float XOffset, float YOffset) {

        float colorFadeToRed = Tools.RangeToRangeMapping(Math.min(-getSidewaysAccu(), 0), -0.0205f, 0, 0, 1);
        float colorFadeToBlue = Tools.RangeToRangeMapping(Math.min(getSidewaysAccu(), 0), -0.0205f, 0, 0, 1);

        glBegin(GL_QUADS);
        glColor4f(colorFadeToBlue, colorFadeToBlue, 1, 0);
        glVertex2f(x + XOffset, y + height + YOffset);
        glColor4f(255, colorFadeToRed, colorFadeToRed, 0);
        glVertex2f(x + width + XOffset, y + height + YOffset);
        glColor4f(255, colorFadeToRed, colorFadeToRed, 0);
        glVertex2f(x + width + XOffset, y + YOffset);
        glColor4f(colorFadeToBlue, colorFadeToBlue, 1, 0);
        glVertex2f(x + XOffset, y + YOffset);
        glColor4f(1, 1, 1, 0);
        glEnd();

        x += XOffset;
        y += YOffset;
    }

    public void playerMovement(Float sidewaysMomentumOverwrite, Float upwardsMomentumOverwrite) {
        draw((sidewaysMomentumOverwrite == null) ? sidewaysMomentum : sidewaysMomentumOverwrite,
             (upwardsMomentumOverwrite == null) ? upwardsMomentum : upwardsMomentumOverwrite);
        if (upwardsMomentum >= -0.1) upwardsMomentum = upwardsMomentum - 0.001f;
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

    public float getSidewaysAccu() {return sidewaysAccu;}

    public void setSidewaysAccu(float sidewaysAccu) {this.sidewaysAccu = sidewaysAccu;}
}
