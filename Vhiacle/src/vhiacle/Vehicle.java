package vhiacle;

/**
 * Created by gematrai on 2017.06.28..
 */
public class Vehicle {

    private int vehicleWeight;

    private final int PERSON_AVERAGE_WEIGHT = 75;


    /**
     * @author Geri
     * created on 2017/06/28
     * @return weight of the vehicle plus the driver
     *
     */
    public int getGrossLoad(){
        return vehicleWeight + PERSON_AVERAGE_WEIGHT;
    }

    public Vehicle(int vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public int getVehicleWeight() {
        return vehicleWeight;
    }

    public int getPERSON_AVERAGE_WEIGHT() {
        return PERSON_AVERAGE_WEIGHT;
    }
}
