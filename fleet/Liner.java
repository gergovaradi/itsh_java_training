package fleet;

/**
 * Created by bbencze on 2017.06.28..
 */
public class Liner implements Ship, CanCarryPassengers {

    private CanCarryPassengers canCarryPassengers;


    public Liner(int maxPassengers) {
        this.canCarryPassengers = new CanCarryPassengersBehaviour(maxPassengers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int loadPassenger(int nrOfPassengers) {
        return canCarryPassengers.loadPassenger(nrOfPassengers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPassengers() {
        return canCarryPassengers.getPassengers();
    }
}
