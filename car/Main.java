/**
 * Created by gvaradi on 2017.06.23..
 */
public class Main {
    public static void main(String[] args) {
        Car yourCar = new Car(230,true);
        System.out.println(yourCar.isRunning());
        System.out.println(yourCar.getTopSpeed());
    }
}
