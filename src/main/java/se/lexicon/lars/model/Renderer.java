package se.lexicon.lars.model;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Renderer extends Application {

    public static final int windowWidth = 800;
    public static final int windowHeight = 600;
    public static Long startNanoTime = System.nanoTime();
    public static double elapsedTime;

    public static void nanoTimer(long currentNanoTime) {
        elapsedTime = (currentNanoTime - Renderer.startNanoTime.doubleValue()) / 1_000_000_000d;
        Renderer.startNanoTime = currentNanoTime;
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("The bird is the word.");
        Group root = new Group();
        Scene mainScene = new Scene(root);

        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        Canvas canvas = new Canvas(windowWidth, windowHeight);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

       /* Bird bird = new Bird();
        GameObject pipe = new Pipe();*/
        Game game = new Game();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                nanoTimer(currentNanoTime);
                game.renderGame(gc, mainScene);
                //pipe.render(gc);
                //bird.renderCircle(gc, mainScene, currentNanoTime);

            }
        }.start();

        primaryStage.show();


    }
}




