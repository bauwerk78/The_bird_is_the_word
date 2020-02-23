package bauwerk78.model;

import bauwerk78.tools.Delayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class BirdAnimation {

    private List<Image> imageList = new ArrayList<>();
    Delayer delayer = new Delayer();

    private double posX;
    private double posY;
    private double drawWidth;
    private double drawHeight;
    private int numOfFrames = 4;
    private int currentFrame;
    private boolean renderFrame;

    public BirdAnimation(double posX, double posY, double drawWidth, double drawHeight) {
        this.posX = posX;
        this.posY = posY;
        this.drawWidth = drawWidth;
        this.drawHeight = drawHeight;
        setImages();
    }

    private void setImages() {
        imageList.add(new Image("file:Images/PlayerCharacter/FlappyBird/frame-1.png"));
        imageList.add(new Image("file:Images/PlayerCharacter/FlappyBird/frame-2.png"));
        imageList.add(new Image("file:Images/PlayerCharacter/FlappyBird/frame-3.png"));
        imageList.add(new Image("file:Images/PlayerCharacter/FlappyBird/frame-4.png"));
    }

    public void resetFrameCount() {
        currentFrame = 0;
        renderFrame = false;
    }

    public void animate(GraphicsContext gc, double posX, double posY) {
        if(!renderFrame) {
            renderFrame = delayer.delayTimer(0.1);
            if(renderFrame) {
                renderFrame = false;
                currentFrame++;
                if (currentFrame == numOfFrames - 1) {
                    currentFrame = 0;
                }

            }
            gc.drawImage(imageList.get(currentFrame), posX, posY, drawWidth, drawHeight);
        }
    }


}
