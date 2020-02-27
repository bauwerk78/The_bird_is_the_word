package bauwerk78.model;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

import static bauwerk78.model.Renderer.elapsedTime;

public class Bird {

    private ArrayList<String> input = new ArrayList<>();
    private BirdAnimation birdAnimation;
    private double positionX;
    private double positionY;
    private double velocity = 0;
    private double jumpSpeed = -30;
    private final double gravity = 200;
    private double imageWidth;
    private double imageHeight;
    private boolean gameOver;

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
        setImageWidth(80);
        setImageHeight(50);
        setPositionX(100);
        setPositionY((Renderer.windowHeight / 2d) - getImageHeight());
        birdAnimation = new BirdAnimation(positionX, positionY, getImageWidth(), getImageHeight());
    }

    public Rectangle2D getBoundaryOfBird() {
        return new Rectangle2D(getPositionX(), getPositionY(), getImageWidth(), getImageHeight());
    }

    public void playerInputResponse(Scene scene) {
        getPlayerInput(scene);
        if (input.contains("UP")) {
            //System.out.println("jump?");
            velocity += jumpSpeed;
            birdAnimation.resetFrameCount();
        }

    }

    public void renderBird(GraphicsContext gc, Scene scene) {
        playerInputResponse(scene);
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

