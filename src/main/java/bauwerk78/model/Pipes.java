package bauwerk78.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class Pipes extends GameObject {

    private static Random rand = new Random();
    private Image pipeFromTop = new Image("file:Images/Objects/Pipes/pipe_going_down.png");
    private Image pipeFromBottom = new Image("file:Images/Objects/Pipes/pipe_going_up.png");
    private Image pipeFromTopTop = new Image("file:Images/Objects/Pipes/pipe_top_going_down.png");
    private Image pipeFromBottomTop = new Image("file:Images/Objects/Pipes/pipe_top_going_up.png");
    private double upperYPosition;
    private double lowerYPosition;
    private double minimumHeight;
    private double gapValue;
    private double upperHeight;
    private double lowerHeight;
    private boolean scoreSet = false;

    public Pipes(double positionX, double objectWidth) {
        super(positionX, objectWidth);
        setObjectSpeedX(100);
        setObjectWidth(objectWidth);
        setMinimumHeight(100);
        setGapValue(200);
        setPipes();
        //setPositionX(windowWidth + randomXPosition());
        setPositionX(positionX);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void update() {
        setPositionX((getPositionX()) - (getObjectSpeedX() * Renderer.elapsedTime));
    }

    @Override
    protected void render(GraphicsContext gc) {
        update();
        //Lower pipe
        setLowerYPosition(Renderer.windowHeight - getLowerHeight());

        gc.setFill(Color.BLACK);
        gc.fillRect(getPositionX(), getLowerYPosition(), getObjectWidth(), getLowerHeight());
        gc.drawImage(pipeFromBottom, getPositionX(), getLowerYPosition() + 10, getObjectWidth(), getLowerHeight() - 10);
        gc.drawImage(pipeFromBottomTop, getPositionX() - 5, getLowerYPosition(), getObjectWidth() + 10, 10);


        //Upper pipe
        setUpperYPosition(0);
        gc.setFill(Color.BLACK);
        gc.fillRect(getPositionX(), getUpperYPosition(), getObjectWidth(), getUpperHeight());
        gc.drawImage(pipeFromTop, getPositionX(), getUpperYPosition(), getObjectWidth(), getUpperHeight() - 10);
        gc.drawImage(pipeFromTopTop, getPositionX() - 5, getUpperHeight() - 10, getObjectWidth() + 10, 10);
    }

    private void setPipes() {
        //Upper pipe.
        setUpperHeight(rand.nextInt(Renderer.windowHeight - ((int) getGapValue() / 2) - ((int) getMinimumHeight() * 2)) + getMinimumHeight());
        //setUpperHeight( (getUpperHeight()) / 10d * 10);
        System.out.println("upperheight " + getUpperHeight());
        //Lower pipe.
        setLowerHeight((Renderer.windowHeight - getUpperHeight()) - ((int) getGapValue() / 2d));
        System.out.println("lowerheight " + getLowerHeight());
    }

    public Rectangle2D getUpperPipeBoundary() {
        return new Rectangle2D(getPositionX(), getUpperYPosition(), getObjectWidth(), getUpperHeight());
    }

    public Rectangle2D getLowerPipeBoundary() {
        return new Rectangle2D(getPositionX(), getLowerYPosition(), getObjectWidth(), getLowerHeight());
    }

    public double getUpperHeight() {
        return upperHeight;
    }

    public void setUpperHeight(double upperHeight) {
        this.upperHeight = upperHeight;
    }

    public double getLowerHeight() {
        return lowerHeight;
    }

    public void setLowerHeight(double lowerHeight) {
        this.lowerHeight = lowerHeight;
    }

    public double getGapValue() {
        return gapValue;
    }

    public void setGapValue(double gapValue) {
        this.gapValue = gapValue;
    }

    public double getMinimumHeight() {
        return minimumHeight;
    }

    public void setMinimumHeight(double minimumHeight) {
        this.minimumHeight = minimumHeight;
    }

    public double getUpperYPosition() {
        return upperYPosition;
    }

    public void setUpperYPosition(double upperYPosition) {
        this.upperYPosition = upperYPosition;
    }

    public double getLowerYPosition() {
        return lowerYPosition;
    }

    public void setLowerYPosition(double lowerYPosition) {
        this.lowerYPosition = lowerYPosition;
    }

    public boolean isScoreSet() {
        return scoreSet;
    }

    public void setScoreSet(boolean scoreSet) {
        this.scoreSet = scoreSet;
    }
}//End of class.
