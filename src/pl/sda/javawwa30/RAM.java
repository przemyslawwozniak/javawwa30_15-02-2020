package pl.sda.javawwa30;

public class RAM {

    private String name;
    private int size;   //in GBs

    public static RAM of(String name, int size) {
        return new RAM(name, size);
    }

    private RAM(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String toString() {
        return "name: " + name +
                ", size: " + size;
    }

    public int getSize() {
        return size;
    }

    /*
    RAM r1 = new Ram("GR", 8);
    RAM r2 = new Ram("GR", 16);
    RAM.isR1LargerThanR2(r1, r2); ---> false
     */
    public static boolean isR1LargerThanR2(RAM r1, RAM r2) {
        return r1.getSize() > r2.getSize();
    }

    /*
    RAM r1 = new Ram("GR", 8);
    RAM r2 = new Ram("GR", 16);
    r1.isLargerThan(r2); ---> false
     */
    public boolean isLargerThan(RAM other) {
        return this.getSize() > other.getSize();
    }
}
