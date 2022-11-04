package org.example;

import org.example.logic.Window;

public class Main {

    public static void main(String[] args) {
        Window window = Window.get();
        window.run();
    }
}