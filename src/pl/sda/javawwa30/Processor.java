package pl.sda.javawwa30;

//Przyklad 'value object' z DDD
public class Processor {
    private String name;
    private int cores;
    private int threads;
    private int clockFreq;

    private Processor(String name, int cores, int threads, int clockFreq) {
        this.name = name;
        this.cores = cores;
        this.threads = threads;
        this.clockFreq = clockFreq;
    }

    //factory method
    public static Processor of(String name, int cores, int threads, int clockFreq) {
        return new Processor(name, cores, threads, clockFreq);
    }

    @Override
    public String toString() {
        return "processor: " +
                "\n\t\tname: " + name +
                "\n\t\tcores: " + cores +
                "\n\t\tthreads: " + threads +
                "\n\t\tfrequency: " + clockFreq;
    }
}
