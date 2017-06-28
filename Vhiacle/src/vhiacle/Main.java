package vhiacle;

import java.util.Scanner;

/**
 * Created by gematrai on 2017.06.28..
 */

/**
 * @author Geri
 * created on 2017/06/28
 * Main method, used Scenner to get informations from the user.
 * @return depends on the given information, by the Scanner
 *
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Give the type of the vehicle (can be : car, van):");
        String vhiacleType = scanner.next();

        if(vhiacleType.trim().toLowerCase().equals("van")) {
            System.out.println("Give the number of passengers here: ");
            int passangers = scanner.nextInt();
            System.out.println("Give the vehicle weight here: ");
            int vhiacleWeight = scanner.nextInt();
            System.out.println("Give the weight of cargo: ");
            int cargoWeight = scanner.nextInt();
            Van van = new Van(vhiacleWeight, passangers, cargoWeight);

            System.out.println("Full weight: " + van.getGrossLoad());
        }

        else if (vhiacleType.trim().toLowerCase().equals("car")){ System.out.println("Give the number of passengers here: ");
            int passangers = scanner.nextInt();
            System.out.println("Give the vehicle weight here: ");
            int vhiacleWeight = scanner.nextInt();
            Car car = new Car(vhiacleWeight, passangers);

            System.out.println("Full weight: " + car.getGrossLoad());
        }
        else{
            System.out.println("No vhiacle like this is in the fleet.");
        }

    }


}
