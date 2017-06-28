package fleet;

/**
 * Created by bbencze on 2017.06.28..
 */
public class CanCarryGoodsBehaviour implements CanCarryGoods {

    private int cargoWeight = 0;
    private int maxCargoWeight;

    public CanCarryGoodsBehaviour(int maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public int getMaxCargoWeight() {
        return maxCargoWeight;
    }

    /**
     * @author Bencze Boglárka
     * date: 2017.06.28.
     * @param weight a type int variable
     * Loads the weight into the cargoWeight variable.
     * @return the weight of unloaded cargo
     */
    public int loadCargo(int weight) {
        if (cargoWeight >= maxCargoWeight) {
            return weight;
        } else if (weight > maxCargoWeight - cargoWeight) {
            int placeForCargo = maxCargoWeight -cargoWeight;
            cargoWeight = maxCargoWeight;
            return weight- placeForCargo;
        } else {
            cargoWeight += weight;
            return 0;
        }
    }

}
