package pl.sda.javawwa30;

public class Runner {

    public static void main(String[] args) {
        Processor i7cpu = Processor.of("Intel Core i7", 6);
        Processor i3cpu = Processor.of("i3", 2);

        RAM ram4 = RAM.of("GoodRam", 4);
        RAM ram8 = RAM.of("GoodRam", 8);
        RAM ram8_2nd = RAM.of("GoodRam", 8);
        RAM ram16 = RAM.of("GoodRam", 16);
        RAM ram16_2nd = RAM.of("GoodRam", 16);

        Motherboard mb1 = new Motherboard("Lenovo MB strong", i7cpu, 4, ram8);
        RAM[] clientsRams = {ram8, ram8_2nd, ram16, ram16_2nd};
        Motherboard mb2 = new Motherboard("Lenovo MB weak", i3cpu, 2, clientsRams);

        Computer myComputer = new Computer(mb1);   //8,-,-,-
        System.out.println(myComputer);
        myComputer.getMotherboard().add(ram4);   //8,4,-,-
        myComputer.getMotherboard().add(ram8_2nd);   //8,4,8,-
        myComputer.getMotherboard().add(ram16);  //8,4,8,16
        myComputer.getMotherboard().add(ram16_2nd);  //pojdzie w miejsce o idx = 1 czyli ram4 ---> 8, 16, 8, 16
        System.out.println(myComputer);


        Computer myOtherComputer = new Computer(mb2);
        System.out.println(myOtherComputer);
    }

}
