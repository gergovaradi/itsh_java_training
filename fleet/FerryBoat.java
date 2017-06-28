package fleet;

/**
 * Created by bbencze on 2017.06.28..
 */
public class FerryBoat implements Ship, CanCarryGoods, CanCarryPassengers {

    private CanCarryPassengers canCarryPassengers;
    private CanCarryGoods canCarryGoods;

    public FerryBoat(int maxCargoWeight, int maxPassenger) {
        this.canCarryGoods = new CanCarryGoodsBehaviour(maxCargoWeight);
        this.canCarryPassengers = new CanCarryPassengersBehaviour(maxPassenger);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int loadCargo(int weight) {
        return canCarryGoods.loadCargo(weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int loadPassenger(int nrOfPassengers) {
        return canCarryPassengers.loadPassenger(nrOfPassengers);
    }

    @Override
    public int getCargoWeight() {
        return canCarryGoods.getCargoWeight();
    }

    @Override
    public int getPassengers() {
        return canCarryPassengers.getPassengers();
    }
}
