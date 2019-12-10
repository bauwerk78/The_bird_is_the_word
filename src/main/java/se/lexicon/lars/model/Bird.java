package se.lexicon.lars.model;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Bird {

    private double positionX;
    private double positionY;
    private double acceleration;
    private double gravity;
    private double circleHeight = 50;
    private double circleWidth = 50;

    private ArrayList<String> input = new ArrayList<>();

    public Bird() {
        initBird();
    }


    private void getPlayerInput(Scene scene) {
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.contains(code))
                            input.add(code);
                    }
                });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });

    }

    public void initBird() {
        setPositionX(100);
        setPositionY(Renderer.windowHeight- circleHeight);
    }

    public void birdMover(GraphicsContext gc, Scene scene) {
        getPlayerInput(scene);
        if(input.contains("SPACE")) {
            acceleration = 12;
        }
    }

    public void renderCircle(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillOval(getPositionX(), getPositionY(), circleWidth, circleHeight);
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }
}//End of class.

