package se.lexicon.lars.model;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Bird {

    public static double elapsedTime;

    private double positionX;
    private double positionY;
    private double velocity = 0;
    private double jumpSpeed = -30;
    private double speedX = 200;
    private final double gravity = 120;
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
        setPositionY((Renderer.windowHeight - circleHeight) / 2);
    }

    public void playerInputResponse(GraphicsContext gc, Scene scene) {
        getPlayerInput(scene);
        if (input.contains("UP")) {
            System.out.println("jump?");
            velocity += jumpSpeed;
        }
        if (input.contains("RIGHT")) {
            System.out.println("going right");
            setPositionX(getPositionX() + speedX * elapsedTime);
            if (getPositionX() > Renderer.windowWidth - circleWidth) {
                setPositionX(Renderer.windowWidth - circleWidth);
            }
        }
        if (input.contains("LEFT")) {
            System.out.println("going left");
            setPositionX(getPositionX() - speedX * elapsedTime);
            if (getPositionX() < 0) {
                setPositionX(0);
            }
        }
    }

    public static void nanoTimer(long currentNanoTime) {
        elapsedTime = (currentNanoTime - Renderer.startNanoTime.doubleValue()) / 1_000_000_000d;
        Renderer.startNanoTime = currentNanoTime;
    }

    //TODO ball falls through, can't see what kind of stupid i have done atm.
    public void renderCircle(GraphicsContext gc, Scene scene, long currentNanoTime) {
        playerInputResponse(gc, scene);
        velocity += (gravity * elapsedTime);
        setPositionY(getPositionY() + (velocity * elapsedTime));
        if (getPositionY() <  0) {
            setPositionY(0);
            velocity = 0;
        }
        if(getPositionY() + circleHeight >= Renderer.windowHeight) {
            setPositionY(Renderer.windowHeight - circleHeight);
            velocity = 0;
        }
        System.out.println(getPositionY());
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

