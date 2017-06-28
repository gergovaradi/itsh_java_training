package fleet;

/**
 * Created by bbencze on 2017.06.28..
 */
public class CanCarryPassengersBehaviour implements CanCarryPassengers {

    private int passengers;
    private int maxPassengers;

    public CanCarryPassengersBehaviour(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getPassengers() {
        return passengers;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    /**
     * @author Bencze Boglárka
     * date: 2017.06.28.
     * @param nrOfPassengers a type int variable
     * Loads the people into the passengers variable.
     * @return the number of unloaded people
     */
    public int loadPassenger(int nrOfPassengers) {
        if (passengers >= maxPassengers) {
            return nrOfPassengers;
        } else if (nrOfPassengers > maxPassengers - passengers) {
            int placeForCargo = maxPassengers -passengers;
            passengers = maxPassengers;
            return nrOfPassengers- placeForCargo;
        } else {
            passengers += nrOfPassengers;
            return 0;
        }
    }

    /*public int loadPassenger(int nrOfPassengers) {
        if (nrOfPassengers > maxPassengers) {
            passengers = maxPassengers;
            return nrOfPassengers-maxPassengers;
        } else {
            passengers = nrOfPassengers;
            return 0;
        }
    }*/
}
