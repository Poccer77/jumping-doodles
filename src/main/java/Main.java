import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import Utilities.Values;
import org.lwjgl.opengl.GL;


public class Main {
    // The window handle
    private long window;
    private float x;

    public void run() throws InterruptedException {
        init();
        loop();

        // Terminate GLFW and free the error callback
        glfwTerminate();
    }

    private void init() {

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create the window
        int height = 700;
        int width = 1000;
        window = glfwCreateWindow(width, height, "Hello World!", 0, 0);
        if (window == 0)
            throw new RuntimeException("Failed to create the GLFW window");


        // Make the window visible
        glfwShowWindow(window);

        glfwMakeContextCurrent(window);

        GL.createCapabilities();

        glfwSwapInterval(1);
    }

    private void loop() throws InterruptedException {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.

        Game game = new Game(window, Values.GAME_SPEED.value);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window)) {

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);

            game.loop();
            int reset = glfwGetKey(window, GLFW_KEY_R);
            if (reset == GLFW_PRESS) game = new Game(window, 0.001f);
            int close = glfwGetKey(window, GLFW_KEY_ESCAPE);
            if (close == GLFW_PRESS) break;
            glfwSwapBuffers(window);
            Thread.sleep(10);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Main().run();
    }
}