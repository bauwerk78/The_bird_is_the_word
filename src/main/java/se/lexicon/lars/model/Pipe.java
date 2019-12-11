package se.lexicon.lars.model;

import static se.lexicon.lars.model.Renderer.elapsedTime;
import static se.lexicon.lars.model.Renderer.windowWidth;
import static se.lexicon.lars.model.Renderer.windowHeight;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class Pipe extends GameObject {

    private double startPosition;
    private static Random rand = new Random();

    public Pipe() {
        init();
    }

    public Pipe(double objectWidth, double objectHeight) {
        super(objectWidth, objectHeight);
    }

    public Pipe(double positionY, double objectWidth, double objectHeight) {
        super(positionY, objectWidth, objectHeight);
        setObjectSpeedX(100);
        setPositionX(windowWidth + randomXPosition());
    }

    public Pipe(double objectWidth, double objectHeight, double objectSpeedX, double objectSpeedY) {
        super(objectWidth, objectHeight, objectSpeedX, objectSpeedY);
    }

    public Pipe(double positionX, double positionY, double objectWidth, double objectHeight, double objectSpeedX, double objectSpeedY) {
        super(positionX, positionY, objectWidth, objectHeight, objectSpeedX, objectSpeedY);
    }

    @Override
    protected void init() {
        setPositionX(windowWidth + 50);
        setPositionY(windowHeight - getObjectHeight());
    }

    @Override
    protected void update() {
        setPositionX((getPositionX()) - (getObjectSpeedX() * elapsedTime));
    }

    @Override
    protected void render(GraphicsContext gc) {
        update();
        gc.setFill(Color.BLACK);
        gc.fillRect(getPositionX(), getPositionY(), getObjectWidth(), getObjectHeight());
    }

    private double randomXPosition() {
        return rand.nextInt(180) + 20;
    }

}//End of class.
