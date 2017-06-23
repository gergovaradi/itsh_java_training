/**
 * Created by gvaradi on 2017.06.23..
 */
public class Car {
    int topSpeed;
    boolean running;
    Car(int topSpeed, boolean running){
        this.running = running;
        this.topSpeed = topSpeed;
    }

    boolean isRunning(){
        return running;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

}
