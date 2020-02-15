package pl.sda.javawwa30;

import java.util.Objects;

public class RAM {

    private static final int MAX_RAM = 64;

    private String name;
    private int size;   //in GBs

    //caching with factory method
    //4, 8, 16, 32
    private static RAM[] ramInstances = new RAM[4];

    /**
     * ACHTUNG: nie bierze pod uwage pola 'name'
     * Enables caching of RAM instances.
     * 1. Lookup ramInstances for same-sized RAM.
     * 2. If yes, return it.
     * 3. If not, create one and put into the table.
     * @param name
     * @param size
     * @return
     */
    public static RAM of(String name, int size) {
        for(RAM ram : ramInstances) {
            if(ram != null) {
                if(ram.size == size) {
                    System.out.println("--- Returning cached RAM instance of size " + size);
                    return ram;
                }
            }
            else {  //RAM == null
                break;  //kazdy kolejny el w tablicy jest null
            }
        }
        RAM ramInstance = new RAM(name, size);
        for(int i = 0; i < ramInstances.length; i++) {
            if(ramInstances[i] == null) {
                System.out.println("--- New RAM instance put into cache of size " + size);
                ramInstances[i] = ramInstance;
                break;
            }
        }
        return ramInstance;
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

    public double calcScore() {
        return 100 * ((this.size * 1.0)/MAX_RAM);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RAM ram = (RAM) o;
        return size == ram.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
