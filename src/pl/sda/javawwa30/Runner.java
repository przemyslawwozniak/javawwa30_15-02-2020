package pl.sda.javawwa30;

public class Runner {

    public static void main(String[] args) {
        Processor i7cpu = Processor.of("Intel Core i7", 6, 12, 2400);
        Processor i3cpu = Processor.of("i3", 2, 4, 1800);

        RAM ram4 = RAM.of("GoodRam", 4);
        RAM ram8 = RAM.of("GoodRam", 8);
        RAM ram8_2nd = RAM.of("GoodRam", 8);
        RAM ram16 = RAM.of("GoodRam", 16);
        RAM ram16_2nd = RAM.of("GoodRam", 16);

        HardDrive hd1 = HardDrive.of("Seagate", 5400, 512);
        HardDrive hd2 = HardDrive.of("Seagate", 5400, 1024);
        HardDrive hd3 = HardDrive.of("Seagate", 5400, 2048);
        HardDrive hd4 = HardDrive.of("WD", 7200, 1024);
        HardDrive hd5 = HardDrive.of("WD", 7200, 2048);

        Motherboard mb1 = new Motherboard("Lenovo MB strong", i7cpu, 4, ram8, 2, hd1);
        RAM[] clientsRams = {ram8, ram8_2nd, ram16, ram16_2nd};
        HardDrive[] clientsHds = {hd4, hd5};
        Motherboard mb2 = new Motherboard("Lenovo MB weak", i3cpu, 2, clientsRams, 1, clientsHds);

        Computer myComputer = new Computer("Fake Lenovo Strong", mb1);   //8,-,-,-
        System.out.println(myComputer);
        myComputer.getMotherboard().add(ram4);   //8,4,-,-
        myComputer.getMotherboard().add(ram8_2nd);   //8,4,8,-
        myComputer.getMotherboard().add(ram16);  //8,4,8,16
        myComputer.getMotherboard().add(ram16_2nd);  //pojdzie w miejsce o idx = 1 czyli ram4 ---> 8, 16, 8, 16
        System.out.println(myComputer);
        
        Computer myOtherComputer = new Computer("Fake Lenovo Weak", mb2);
        System.out.println(myOtherComputer);


    }

}
