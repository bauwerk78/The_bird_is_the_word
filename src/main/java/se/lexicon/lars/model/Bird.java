package se.lexicon.lars.model;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static se.lexicon.lars.model.Renderer.elapsedTime;

public class Bird {


    private Image image;
    private ArrayList<String> input = new ArrayList<>();
    private BirdAnimation birdAnimation;
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
    private boolean gameOver;


    public Bird() {
        initBird();
    }

    public void setImage() {
        image = new Image("file:Images/mario_rambo2.gif", getImageWidth(), getImageHeight(), true, false);

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
        birdAnimation = new BirdAnimation(positionX, positionY, getImageWidth(), getImageHeight());
    }

    public Rectangle2D getBoundaryOfBird() {
        return new Rectangle2D(getPositionX(), getPositionY(), getImageWidth(), getImageHeight());
    }

    public void playerInputResponse(GraphicsContext gc, Scene scene) {
        getPlayerInput(scene);
        if (input.contains("UP")) {
            //System.out.println("jump?");
            velocity += jumpSpeed;
            birdAnimation.resetFrameCount();
        }

    }

    public void renderBird(GraphicsContext gc, Scene scene) {
        playerInputResponse(gc, scene);
        velocity += (gravity * elapsedTime);
        setPositionY(getPositionY() + (velocity * elapsedTime));
        if (getPositionY() < 0) {
            setPositionY(0);
            velocity = 0;
        }
        if (getPositionY() + getImageHeight() >= Renderer.windowHeight) {
            setPositionY(Renderer.windowHeight - getImageHeight());
            gameOver = true;
            velocity = 0;
        }

        //gc.drawImage(image,getPositionX(), getPositionY(), getImageWidth(), getImageHeight());
        birdAnimation.animate(gc, positionX, positionY);
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

    public boolean isGameOver() {
        return gameOver;
    }
}//End of class.

