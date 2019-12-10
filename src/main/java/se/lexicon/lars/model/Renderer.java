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

        Bird bird = new Bird();
        bird.initBird();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, windowWidth, windowHeight);
                bird.renderCircle(gc);
            }
        }.start();

        primaryStage.show();


    }
}




