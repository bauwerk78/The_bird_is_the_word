package se.lexicon.lars.model;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

import static se.lexicon.lars.model.Renderer.windowWidth;
import static se.lexicon.lars.model.Renderer.windowHeight;

public class Game {

    private static Random rand = new Random();

    private double randomUpperPipeWidth;
    private double randomUpperPipeHeight;
    private double randomLowerPipeWidth;
    private double randomLowerPipeHeight;
    private double maxUpperPipeHeight;
    private double maxLowerPipeHeight = (windowHeight / 2d);
    private double minimumPipeWidth;
    private double maximumPipeWidth;
    private double minimumPipeHeight;
    private boolean renderNewPipes = true;
    Pipe lowerPipe;
    Pipe upperPipe;
    Bird bird;

    public void initGame() {
        setMinimumPipeWidth(10);
        setMaximumPipeWidth(40);
        setMinimumPipeHeight(100);
        createBird();
        createPipes();
    }

    public void renderGame(GraphicsContext gc, Scene scene) {
        gc.clearRect(0, 0, windowWidth, windowHeight);
        bird.renderCircle(gc, scene);

        if(lowerPipe.getPositionX() + getRandomLowerPipeWidth() < 0 && upperPipe.getPositionX() + getRandomUpperPipeWidth() < 0) {
            renderNewPipes = true;
            createPipes();
        }
        lowerPipe.render(gc);
        upperPipe.render(gc);
    }

    private void randomizeHeight() {
        //Lower pipe
        setRandomLowerPipeHeight(rand.nextInt((int) getMaxLowerPipeHeight() - (int) getMinimumPipeHeight()) + getMinimumPipeHeight());

        //Upper pipe
        setMaxUpperPipeHeight(windowHeight - getRandomLowerPipeHeight());
        setRandomUpperPipeHeight(rand.nextInt((int) getMaxUpperPipeHeight() - (int) getMinimumPipeHeight()) + getMinimumPipeHeight());
        //System.out.println("lowerpipe height");
    }

    private void randomizeWidth() {

        //Lower pipe
        setRandomLowerPipeWidth(rand.nextInt((int) getMaximumPipeWidth()) + getMinimumPipeWidth());
        //Upper pipe
        setRandomUpperPipeWidth(rand.nextInt((int) getMaximumPipeWidth()) + getMinimumPipeWidth());
    }

    public void createPipes() {
        randomizeWidth();
        randomizeHeight();
        if(renderNewPipes) {
            lowerPipe = null;
            upperPipe = null;
            renderNewPipes = false;
        }
        //Lower pipe
        lowerPipe = new Pipe(windowHeight - getRandomLowerPipeHeight(), getRandomLowerPipeWidth(), getRandomLowerPipeHeight());

        //Upper pipe
        upperPipe = new Pipe(0, getRandomUpperPipeWidth(), getRandomUpperPipeHeight());

    }

    public void createBird() {
        bird = new Bird();
    }

    public double getMinimumPipeWidth() {
        return minimumPipeWidth;
    }

    public void setMinimumPipeWidth(double minimumPipeWidth) {
        this.minimumPipeWidth = minimumPipeWidth;
    }

    public double getMaximumPipeWidth() {
        return maximumPipeWidth;
    }

    public void setMaximumPipeWidth(double maximumPipeWidth) {
        this.maximumPipeWidth = maximumPipeWidth;
    }

    public double getRandomUpperPipeWidth() {
        return randomUpperPipeWidth;
    }

    public void setRandomUpperPipeWidth(double randomUpperPipeWidth) {
        this.randomUpperPipeWidth = randomUpperPipeWidth;
    }

    public double getRandomUpperPipeHeight() {
        return randomUpperPipeHeight;
    }

    public void setRandomUpperPipeHeight(double randomUpperPipeHeight) {
        this.randomUpperPipeHeight = randomUpperPipeHeight;
    }

    public double getRandomLowerPipeWidth() {
        return randomLowerPipeWidth;
    }

    public void setRandomLowerPipeWidth(double randomLowerPipeWidth) {
        this.randomLowerPipeWidth = randomLowerPipeWidth;
    }

    public double getRandomLowerPipeHeight() {
        return randomLowerPipeHeight;
    }

    public void setRandomLowerPipeHeight(double randomLowerPipeHeight) {
        this.randomLowerPipeHeight = randomLowerPipeHeight;
    }

    public double getMaxUpperPipeHeight() {
        return maxUpperPipeHeight;
    }

    public void setMaxUpperPipeHeight(double maxUpperPipeHeight) {
        this.maxUpperPipeHeight = maxUpperPipeHeight;
    }

    public double getMaxLowerPipeHeight() {
        return maxLowerPipeHeight;
    }

    public void setMaxLowerPipeHeight(double maxLowerPipeHeight) {
        this.maxLowerPipeHeight = maxLowerPipeHeight;
    }

    public double getMinimumPipeHeight() {
        return minimumPipeHeight;
    }

    public void setMinimumPipeHeight(double minimumPipeHeight) {
        this.minimumPipeHeight = minimumPipeHeight;
    }
}
