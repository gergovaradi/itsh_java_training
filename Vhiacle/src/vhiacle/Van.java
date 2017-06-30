package vhiacle;

/**
 * Created by gematrai on 2017.06.28..
 */
public class Van extends Car {

    private int cargoWeight;

    public Van(int vehicleWeight, int numberPassengers, int cargoWeight) {
        super(vehicleWeight, numberPassengers);
        this.cargoWeight = cargoWeight;
    }
    /**
     * @author Geri
     * created on 2017/06/28
     * @return weight of passangers plus cargo plus vehicle weight
     *
     */
    @Override
    public int getGrossLoad(){
        return super.getGrossLoad() + cargoWeight;
    }
}
