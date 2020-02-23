package bauwerk78.tools;

import static bauwerk78.model.Renderer.elapsedTime;

public class Delayer {

    private double counter;
    private double timeToDelay;

    public Delayer() {

    }

    public Delayer(double timeToDelay) {
        this.timeToDelay = timeToDelay;
    }

    public void delayTimer() {
        this.delayTimer(this.timeToDelay);
    }

    public boolean delayTimer(double timeToDelay) {
        counter += elapsedTime;
        //System.out.println("counter: " + counter + " timeToDelay: " + timeToDelay);
        if (counter < timeToDelay) {
            return false;
        } else {
            counter = 0;
            return true;
        }
    }

}
