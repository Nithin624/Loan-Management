package model;

public class Customer {

    private int customerId;
    private String name;
    private String phone;
    private String address;
    private Asset asset;

    public Customer(int customerId, String name,
                    String phone, String address,
                    Asset asset) {

        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.asset = asset;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Asset getAsset() {
        return asset;
    }

    @Override
    public String toString() {
        return "\nCustomer ID: " + customerId +
                "\nName: " + name +
                "\nPhone: " + phone +
                "\nAddress: " + address +
                "\nAssets: " + asset;
    }
}
