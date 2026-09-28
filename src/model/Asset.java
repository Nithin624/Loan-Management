package model;

public class Asset {

    private double goldValue;
    private double landValue;
    private double houseValue;
    private double vehicleValue;

    public Asset(double goldValue, double landValue,
                 double houseValue, double vehicleValue) {

        this.goldValue = goldValue;
        this.landValue = landValue;
        this.houseValue = houseValue;
        this.vehicleValue = vehicleValue;
    }

    public double getGoldValue() {
        return goldValue;
    }

    public double getLandValue() {
        return landValue;
    }

    public double getHouseValue() {
        return houseValue;
    }

    public double getVehicleValue() {
        return vehicleValue;
    }

    public double getTotalAssetValue() {
        return goldValue + landValue + houseValue + vehicleValue;
    }

    @Override
    public String toString() {
        return "Gold: ₹" + goldValue +
                ", Land: ₹" + landValue +
                ", House: ₹" + houseValue +
                ", Vehicle: ₹" + vehicleValue +
                ", Total Assets: ₹" + getTotalAssetValue();
    }
}
