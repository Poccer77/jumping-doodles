package org.example;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Model {

    private int drawCount;
    private int vId;

    public Model(float[] vertices) {
        drawCount = vertices.length / 3;

        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
        buffer.put(vertices);
        buffer.flip();

        vId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vId);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

}
