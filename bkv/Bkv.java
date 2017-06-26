package bkv;

/**
 * Created by gvaradi on 2017.06.26..
 */
public class Bkv {
    public static void main(String[] args) {
        /*
        * @ Váradi Gergő
        * @ 2017.06.26.
        * Parse arguments and initialize starting values.
        */
        String vehicle = args[0];
        int number = Integer.parseInt(args[1]);
        boolean findVehicle = false;
        boolean findNumber = false;

        /*
        * @ Váradi Gergő
        * @ 2017.06.26.
        * Check the vehicle and the number.
        */
        for(Vehicle item: Vehicle.values()){
            if(item.getName().equals(vehicle)) {
                findVehicle = true;
                for (int actualNumber : item.getVehicleNumber()) {
                    if (actualNumber == number) {
                        findNumber = true;
                        if (item.isOutSide()) {
                            System.out.println("Közlekedik Budapesten kívűl: " + item.getName() + " " + number);
                        } else {
                            System.out.println("Nem közlekedik Budapesten kívűl: " + item.getName() + " " + number);
                        }
                    }
                }
            }
        }

        /*
        * @ Váradi Gergő
        * @ 2017.06.26.
        * If no vehicle or vehicle number is found, write a message.
        */
        if(!findVehicle){
            System.out.println("Nincs ilyen közlekedési jármű!");
        }else if(!findNumber){
            System.out.println("Nincs ilyen járatszám!");
        }
    }
}
