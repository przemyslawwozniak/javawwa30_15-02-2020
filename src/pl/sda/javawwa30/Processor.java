package pl.sda.javawwa30;

//Przyklad 'value object' z DDD
public class Processor {
    private String name;
    private int cores;

    private Processor(String name, int cores) {
        this.name = name;
        this.cores = cores;
    }

    //factory method
    public static Processor of(String name, int cores) {
        return new Processor(name, cores);
    }

    @Override
    public String toString() {
        return "processor: " +
                "\n\t\tname: " + name +
                "\n\t\tcores: " + cores;
    }
}
