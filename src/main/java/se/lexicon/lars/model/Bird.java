package se.lexicon.lars.model;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static se.lexicon.lars.model.Renderer.elapsedTime;

public class Bird {

    private double positionX;
    private double positionY;
    private double velocity = 0;
    private double jumpSpeed = -30;
    private double speedX = 200;
    private final double gravity = 200;
    private double circleHeight = 50;
    private double circleWidth = 50;
    private double imageWidth;
    private double imageHeight;
    private Image image;
    private ArrayList<String> input = new ArrayList<>();

    public Bird() {
        initBird();
    }

    public void setImage() {
        image = new Image("file:Images/mario_rambo1.gif", getImageWidth(), getImageHeight(), true, false);
        //setImageWidth(image.getWidth());
        //setImageHeight(image.getHeight());
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
        setImageWidth(80);
        setImageHeight(50);
        setImage();
        setPositionX(100);
        setPositionY((Renderer.windowHeight / 2d) - getImageHeight());
    }

    public void playerInputResponse(GraphicsContext gc, Scene scene) {
        getPlayerInput(scene);
        if (input.contains("UP")) {
            //System.out.println("jump?");
            velocity += jumpSpeed;
        }
/*        if (input.contains("RIGHT")) {
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
        }*/
    }

    //TODO ball falls through, can't see what kind of stupid i have done atm.
    public void renderCircle(GraphicsContext gc, Scene scene) {
        playerInputResponse(gc, scene);
        velocity += (gravity * elapsedTime);
        setPositionY(getPositionY() + (velocity * elapsedTime));
        if (getPositionY() < 0) {
            setPositionY(0);
            velocity = 0;
        }
        if (getPositionY() + getImageHeight() >= Renderer.windowHeight) {
            setPositionY(Renderer.windowHeight - getImageHeight());
            velocity = 0;
        }
        //System.out.println(getPositionY());

        /*gc.setFill(Color.BLACK);
        gc.fillOval(getPositionX(), getPositionY(), circleWidth, circleHeight);
        gc.setFill(Color.BLACK);*/
        gc.drawImage(image,getPositionX(), getPositionY(), getImageWidth(), getImageHeight());
    }

    public double getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(double imageWidth) {
        this.imageWidth = imageWidth;
    }

    public double getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(double imageHeight) {
        this.imageHeight = imageHeight;
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

