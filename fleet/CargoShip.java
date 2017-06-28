package fleet;

/**
 * Created by bbencze on 2017.06.28..
 */
public class CargoShip implements Ship, CanCarryGoods {

    private CanCarryGoods canCarryGoods;

    public CargoShip(int maxCargoWeight) {
        this.canCarryGoods = new CanCarryGoodsBehaviour(maxCargoWeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int loadCargo(int weight) {
        return canCarryGoods.loadCargo(weight);
    }

    @Override
    public int getCargoWeight() {
        return canCarryGoods.getCargoWeight();
    }
}
