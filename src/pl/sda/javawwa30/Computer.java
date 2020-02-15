package pl.sda.javawwa30;

public class Computer {

    private String motherboard; // płyta główna
    private String processor; // "i5", "i7", "intel", "amd"
    private int cores; // liczba rdzeni
    private int ram; // ilość ramu

    private int hd; // wielkość dysku w GB
    private String monitor; // nazwa preducenta
    private String printer; // nazwa drukarki

    public Computer(String motherboard, String processor, int cores, int ram) {

    }

    @Override
    public String toString() {
        return "Computer{" +
                "motherboard='" + motherboard + '\'' +
                ", processor='" + processor + '\'' +
                ", cores=" + cores +
                ", ram=" + ram +
                ", hd=" + hd +
                ", monitor='" + monitor + '\'' +
                ", printer='" + printer + '\'' +
                '}';
    }
}
