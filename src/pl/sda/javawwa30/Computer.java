package pl.sda.javawwa30;

public class Computer {

    private String name;
    private Motherboard motherboard;


    public Computer(String name, Motherboard motherboard) {
        this.name = name;
        this.motherboard = motherboard;
    }


    @Override
    public String toString() {
        return "Computer's specification:\n" +
                "\nname: " + name +
                "\n" + motherboard +
                "\nTOTAL SCORE: " + calcTotalScore();
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public double calcTotalScore() {
        return motherboard.calcScore();
    }
}
