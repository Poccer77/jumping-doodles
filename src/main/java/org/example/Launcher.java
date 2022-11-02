package org.example;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Launcher {

    public static void main(String[] args) {

        int height = 720;
        int width = 1280;

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("GLFW library failed to initialize");
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        long window = glfwCreateWindow(width, height, "jumping-doodles", 0, 0);
        if (window == 0) {
            throw new IllegalStateException("Window creation failed");
        }

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);

        glfwShowWindow(window);

        glfwMakeContextCurrent(window);

        GL.createCapabilities();

        while (!glfwWindowShouldClose(window)) {

            if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_TRUE) {
                glfwSetWindowShouldClose(window, true);
            }

            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);

            glBegin(GL_QUADS);

                glColor4f(1, 0, 0, 0);
                glVertex2f(-0.5f, 0.5f);

                glColor4f(0, 1, 0, 0);
                glVertex2f(0.5f, 0.5f);

                glColor4f(0, 0, 1, 0);
                glVertex2f(0.5f, -0.5f);

                glColor4f(1, 1, 1, 0);
                glVertex2f(-0.5f, -0.5f);

            glEnd();

            glfwSwapBuffers(window);
        }

        glfwTerminate();
    }
}