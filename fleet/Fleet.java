package fleet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbencze on 2017.06.28..
 */
public class Fleet {

    private List<Ship> shipList = new ArrayList<>();
    private int waitingPersons;
    private int waitingCargo;

    /**
     * @author Bencze Boglárka
     * date: 2017.06.28.
     * Adds a ship to the ship list.
     */
    public void addShip(Ship ship) {
        shipList.add(ship);

    }

    /**
     * @author Bencze Boglárka
     * date: 2017.06.28.
     * Goes through the list and fills the ships with people and weigths.
     */
    public void fill(int passenger, int cargoWeight) {
        waitingPersons = passenger;
        waitingCargo = cargoWeight;

        for (Ship ship: shipList) {
            if (ship instanceof CargoShip) {
                waitingCargo = ((CargoShip) ship).loadCargo(waitingCargo);
            }

            if (ship instanceof Liner) {
                waitingPersons = ((Liner) ship).loadPassenger(waitingPersons);
            }

            if (ship instanceof FerryBoat) {
                waitingCargo = ((FerryBoat) ship).loadCargo(waitingCargo);
                waitingPersons = ((FerryBoat) ship).loadPassenger(waitingPersons);
            }
        }
    }

    public int getWaitingPersons() {
        return waitingPersons;
    }

    public int getWaitingCargo() {
        return waitingCargo;
    }

    public static void main(String[] args) {
        Fleet fleet = new Fleet();

        Liner liner = new Liner(100);

        CargoShip cargoShip = new CargoShip(50);

        FerryBoat ferryBoat = new FerryBoat(100, 40);

        fleet.addShip(liner);
        fleet.addShip(cargoShip);
        fleet.addShip(ferryBoat);

        fleet.fill(140, 190);

        System.out.println("fill the ships with: passengers: 140, weight: 120");
        System.out.println();


        System.out.println("liner passengers: " + liner.getPassengers());

        System.out.println("ferryBoat passengers: " + ferryBoat.getPassengers());

        System.out.println("    waiting people: " + fleet.getWaitingPersons());


        System.out.println("cargoShip weight: " + cargoShip.getCargoWeight());

        System.out.println("ferryBoat weight: " + ferryBoat.getCargoWeight());

        System.out.println("    waiting cargo: " + fleet.getWaitingCargo());

        System.out.println();


        System.out.println("fill the remaining places with: passengers: 350, weight: 170");

        fleet.fill(350, 170);


        System.out.println("liner1 passengers: " + liner.getPassengers());

        System.out.println("ferryBoat passengers: " + ferryBoat.getPassengers());

        System.out.println("    waiting people: " + fleet.getWaitingPersons());


        System.out.println("cargoShip weight: " + cargoShip.getCargoWeight());

        System.out.println("ferryBoat weight: " + ferryBoat.getCargoWeight());

        System.out.println("    waiting cargo: " + fleet.getWaitingCargo());

    }
}
