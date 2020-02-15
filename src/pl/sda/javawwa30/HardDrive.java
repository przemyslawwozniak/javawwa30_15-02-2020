package pl.sda.javawwa30;

public class HardDrive {

    private static final int MAX_RPM = 10000;
    private static final int MAX_SIZE = 10240;

    private String manufacturer;
    private int rpm;
    private int size;

    private HardDrive(String manufacturer, int rpm, int size) {
        this.manufacturer = manufacturer;
        this.rpm = rpm;
        this.size = size;
    }

    public static HardDrive of(String manufacturer, int rpm, int size) {
        return new HardDrive(manufacturer, rpm, size);
    }

    public int getSize() {
        return size;
    }

    public boolean isLargerThan(HardDrive other) {
        return this.getSize() > other.getSize();
    }

    @Override
    public String toString() {
        return "manufactured by: " + manufacturer +
                ", size: " + size +
                ", rpm: " + rpm;
    }

    public double calcScore() {
        return (100 * ((this.size * 1.0)/MAX_SIZE) + 100 * ((this.rpm * 1.0)/MAX_RPM))/2;
    }
}
