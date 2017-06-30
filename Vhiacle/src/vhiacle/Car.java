package vhiacle;

/**
 * Created by gematrai on 2017.06.28..
 */
public class Car extends Vehicle{

    private int numberPassengers;

    public Car(int vehicleWeight, int numberPassengers) {
        super(vehicleWeight);
        this.numberPassengers = numberPassengers;
    }

    /**
     * @author Geri
     * created on 2017/06/28
     * @return weight of passangers plus the car
     *
     */
    @Override
    public int getGrossLoad() { return super.getGrossLoad()+
          (numberPassengers * super.getPERSON_AVERAGE_WEIGHT());   }



}
