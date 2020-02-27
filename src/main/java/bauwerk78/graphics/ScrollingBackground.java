package bauwerk78.graphics;

import bauwerk78.model.Renderer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class ScrollingBackground {

    private ImageView imageView1 = new ImageView();
    private ImageView imageView2 = new ImageView();

    private Pane pane = new Pane();

    private double imageWidth;
    private double imageHeight;
    private double imageView1PosX;
    private double imageView2PosX;

    private double posX;
    private double posY;
    private double backgroundScrollSpeed = 100;

    public ScrollingBackground() {
        init();
    }

    private void init() {
        Image image = new Image("file:Images/Backgrounds/flappy_bg.png");
        imageWidth = Renderer.windowWidth;
        imageHeight = Renderer.windowHeight;

        imageView1.setImage(image);

        imageView1.setFitHeight(imageHeight);
        imageView1.setFitWidth(imageWidth);


        imageView2.setImage(image);
        imageView2.setFitHeight(imageHeight);
        imageView2.setFitWidth(imageWidth);

        pane.getChildren().addAll(imageView1, imageView2);

        imageView1PosX = 0;
        imageView2PosX = imageWidth;
    }

    public void update() {
        if(imageView1PosX < imageView2PosX && imageView1PosX <= -imageWidth) {
            imageView1PosX = imageView2PosX + imageWidth;
        }
        if(imageView2PosX < imageView1PosX && imageView2PosX <= -imageWidth) {
            imageView2PosX = imageView1PosX + imageWidth;
        }
        imageView1PosX = imageView1PosX - backgroundScrollSpeed * Renderer.elapsedTime;
        imageView2PosX = imageView2PosX - backgroundScrollSpeed * Renderer.elapsedTime;

        imageView1.relocate(imageView1PosX, 0);
        imageView2.relocate(imageView2PosX, 0);
    }

    public Pane getPane(){
        return pane;
    }



}//End of class.
