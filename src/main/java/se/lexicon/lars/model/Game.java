package se.lexicon.lars.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static se.lexicon.lars.model.Renderer.*;

public class Game {

    private static Random rand = new Random();

    private double upperPipeHeight;
    private double lowerPipeHeight;
    private double minimumPipeHeight;
    private double pipeWidth;
    private double pipesStartingXPoint;
    private boolean renderNewPipes = false;
    private double renderTimer;
    private boolean gameOver = false;

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

    public void resetGame() {
        bird = null;
        pipes.clear();
        setRenderTimer(0);
        setGameOver(false);
    }

    public void renderGame(GraphicsContext gc, Scene scene) {
        gc.clearRect(0, 0, windowWidth, windowHeight);
        if (!isGameOver()) {
            pipeTimer();
            bird.renderCircle(gc, scene);
            Iterator<Pipes> pipe = pipes.iterator();
            while (pipe.hasNext()) {
                Pipes pip = pipe.next();
                if (pip.getPositionX() + pip.getObjectWidth() <= 0) {
                    pipe.remove();
                }
                if (collisionDetection(bird.getBoundaryOfBird(), pip.getUpperPipeBoundary()) ||
                    collisionDetection(bird.getBoundaryOfBird(), pip.getLowerPipeBoundary())) {
                    setGameOver(true);
                }
            }
            for (Pipes pipers : pipes) {
                pipers.render(gc);
            }
        }
    }//End of renderGame method.

    public void mainGameLoop(GraphicsContext gc, Scene scene) {
        if(isGameOver()) {
            resetGame();
            initGame();
            return;
        }
        renderGame(gc, scene);
    }



    //Timer to spawn new pipes.
    public void pipeTimer() {
        float amountOfSecondsBetweenSpawns = 3;
        if (getRenderTimer() < amountOfSecondsBetweenSpawns) {
            setRenderTimer(getRenderTimer() + elapsedTime);
            return;
        }
        setRenderTimer(0);
        createPipes();
    }

    //Check if object1 collides with object2
    public boolean collisionDetection(Rectangle2D object1, Rectangle2D object2) {
        if (object1.intersects(object2)) {
            //System.out.println("collision detected.");
            object2 = null;
            object1 = null;
            return true;
        }
        return false;
    }

    public void createPipes() {
        pipes.add(new Pipes(getPipesStartingXPoint(), getPipeWidth()));
    }

    public void createBird() {
        bird = new Bird();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public double getRenderTimer() {
        return renderTimer;
    }

    public void setRenderTimer(double renderTimer) {
        this.renderTimer = renderTimer;
    }

    public boolean isRenderNewPipes() {
        return renderNewPipes;
    }

    public void setRenderNewPipes(boolean renderNewPipes) {
        this.renderNewPipes = renderNewPipes;
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
