package bkv;

/**
 * Created by gvaradi on 2017.06.26..
 */
public enum Vehicle {
    /*
    * Vehicle elements!
    */
    BUSZ("Busz", new int[]{1, 12, 3}, false),
    HEV("Hév", new int[]{2,4}, true),
    TROLI("Troli", new int[]{15,20}, false),
    METRO("Metro", new int[]{1,2,3,4}, false),
    VILLAMOS("Villamos", new int[]{12,24,1}, false);

    /*
    * Running state!
    */
    private String name;
    private int[] vehicleNumber;
    private boolean outSide;

    Vehicle(String name, int[] vehicleNumber, boolean outSide) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.outSide = outSide;
    }

    /*
    * @ Váradi Gergő
    * @ 2017.06.26.
    * Returns the array of the current vehicle numbers.
    */
    public int[] getVehicleNumber() {
        return vehicleNumber;
    }

    /*
    * @ Váradi Gergő
    * @ 2017.06.26.
    * Returns true if the current vehicle travel outside Budapest.
    */
    public boolean isOutSide() {
        return outSide;
    }

    /*
    * @ Váradi Gergő
    * @ 2017.06.26.
    * Returns the name of the current vehicle.
    */
    public String getName() {
        return name;
    }
}
