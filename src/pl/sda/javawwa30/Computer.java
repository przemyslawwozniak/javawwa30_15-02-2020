package pl.sda.javawwa30;

public class Computer {

    private Motherboard motherboard;
    private int hd;
    private String monitor;
    private String printer;


    public Computer(Motherboard motherboard) {
        this.motherboard = motherboard;
    }


    @Override
    public String toString() {
        return "Computer's specification:\n" +
                motherboard +
                "\nhd: " + hd +
                "\nmonitor: " + monitor +
                "\nprinter: " + printer;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }
}
