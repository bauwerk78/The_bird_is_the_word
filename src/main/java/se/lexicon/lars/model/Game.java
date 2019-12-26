package se.lexicon.lars.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

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
    private double gameScore;
    private double highScore;
    private File file;
    private FileWriter fileWriter;

    ArrayList<Pipes> pipes = new ArrayList<>();
    Bird bird;

    public Game() {
        initGame();
    }

    public void initGame() {
        setPipeWidth(50);
        setPipesStartingXPoint(windowWidth);
        createBird();
        createPipes();
        loadHighScore();
    }

    public void resetGame() {
        bird = null;
        pipes.clear();
        setRenderTimer(0);
        setGameOver(false);
        if(gameScore > highScore) {
            highScore = gameScore;
            saveHighScore();
        }
        gameScore = 0;
    }

    public void renderGame(GraphicsContext gc, Scene scene) {
        gc.clearRect(0, 0, windowWidth, windowHeight);
        if (!isGameOver()) {
            pipeTimer();
            bird.renderBird(gc, scene);
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
        if (isGameOver()) {
            resetGame();
            initGame();
            return;
        }
        renderGame(gc, scene);
        renderGameScore(gc);
    }

    public void loadHighScore() {
        try {
            file = new File("resources/HighScore.txt");
            Scanner scanner = new Scanner(file);
            if(scanner.hasNext()) {
                highScore = Double.parseDouble(scanner.nextLine());
            }
            scanner.close();
        } catch(NullPointerException | FileNotFoundException e) {
            System.out.println("Loading high score failed: " + e);
        }
    }

    public void saveHighScore() {
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(Double.toString(highScore));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Saving high score failed: " + e);
        }
    }

    public void renderGameScore(GraphicsContext gc) {
        for(Pipes pipe : pipes) {
            if(bird.getPositionX() >= pipe.getPositionX() + pipe.getObjectWidth() && !pipe.isScoreSet()) {
                gameScore = gameScore + 1;
                pipe.setScoreSet(true);
            }
        }
        Font theFont = Font.font("Verdana", FontWeight.BOLD, 15);
        gc.setFont(theFont);
        gc.setFill(Color.DARKRED);
        String bestScore = ("High Score: " + (int) highScore);
        String totalScore = ("Score: " + (int) gameScore);
        gc.fillText(bestScore, 10, 25);
        gc.fillText(totalScore, 10, 50);

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
