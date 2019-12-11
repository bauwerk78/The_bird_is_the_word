package se.lexicon.lars.model;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static se.lexicon.lars.model.Renderer.windowWidth;
import static se.lexicon.lars.model.Renderer.windowHeight;

public class Game {

    private static Random rand = new Random();

    private double upperPipeHeight;
    private double lowerPipeHeight;
    private double minimumPipeHeight;
    private double pipeWidth;
    private double pipesStartingXPoint;
    private boolean renderNewPipes = true;
    ArrayList<Pipes> pipes = new ArrayList<>();
    Bird bird;

    public Game() {
        initGame();
    }

    public void initGame() {
        setPipeWidth(50);
        setPipesStartingXPoint(windowWidth + 50);
        createBird();
        createPipes();
    }

    public void renderGame(GraphicsContext gc, Scene scene) {
        gc.clearRect(0, 0, windowWidth, windowHeight);
        bird.renderCircle(gc, scene);
        for(Pipes pipe : pipes) {
            pipe.render(gc);
        }
        /*if (lowerPipes.getPositionX() + getLowerPipeWidth() < 0 && upperPipes.getPositionX() + getUpperPipeWidth() < 0) {
            renderNewPipes = true;
            createPipes();
        }
        lowerPipes.render(gc);
        upperPipes.render(gc);*/
    }

    public void createPipes() {
        pipes.add(new Pipes(getPipesStartingXPoint(), getPipeWidth()));
    }

    public void removePipes() {

    }

    public void createBird() {
        bird = new Bird();
    }

    public double getPipesStartingXPoint() {
        return pipesStartingXPoint;
    }

    public void setPipesStartingXPoint(double pipesStartingXPoint) {
        this.pipesStartingXPoint = pipesStartingXPoint;
    }

    public double getPipeWidth() {
        return pipeWidth;
    }

    public void setPipeWidth(double pipeWidth) {
        this.pipeWidth = pipeWidth;
    }

    public double getUpperPipeHeight() {
        return upperPipeHeight;
    }

    public void setUpperPipeHeight(double upperPipeHeight) {
        this.upperPipeHeight = upperPipeHeight;
    }

    public double getLowerPipeHeight() {
        return lowerPipeHeight;
    }

    public void setLowerPipeHeight(double lowerPipeHeight) {
        this.lowerPipeHeight = lowerPipeHeight;
    }

    public double getMinimumPipeHeight() {
        return minimumPipeHeight;
    }

    public void setMinimumPipeHeight(double minimumPipeHeight) {
        this.minimumPipeHeight = minimumPipeHeight;
    }
}
