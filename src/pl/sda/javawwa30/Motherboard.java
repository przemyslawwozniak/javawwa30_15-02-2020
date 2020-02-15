package pl.sda.javawwa30;

public class Motherboard {

    private String name;
    private Processor processor;
    private RAM[] rams;
    private HardDrive[] hardDrives;

    public Motherboard(String name, Processor processor, int ramSlots, int hdSlots) {
        this.name = name;
        this.processor = processor;
        this.rams = new RAM[ramSlots];
        this.hardDrives = new HardDrive[hdSlots];
    }

    public Motherboard(String name, Processor processor, int ramSlots, RAM ram, int hdSlots, HardDrive hd) {
        this(name, processor, ramSlots, hdSlots);
        this.rams[0] = ram;
        this.hardDrives[0] = hd;
    }

    /**
     * Client provides a bunch of RAMs and we pick up the optimal ones (biggest total RAM)
     * @param name
     * @param processor
     * @param ramSlots
     * @param rams
     */
    public Motherboard(String name, Processor processor, int ramSlots, RAM[] rams, int hdSlots, HardDrive[] hds) {
        this(name, processor, ramSlots, hdSlots);
        for(RAM ram : rams) {
            add(ram);
        }
        for(HardDrive hd : hds) {
            add(hd);
        }
    }


    /**
     * Inserts new RAM if there is an empty slot available.
     * If not, replaces smaller size RAM (if exists) with this one.
     * @param ram
     */
    public void add(RAM ram) {
        boolean insertedIntoEmptySlot = tryToInsertIntoEmptySlot(ram);
        if(!insertedIntoEmptySlot) {
            tryReplaceSmallest(ram);
        }
    }

    public void add(HardDrive hardDrive) {
        boolean insertedIntoEmptySlot = tryToInsertIntoEmptySlot(hardDrive);
        if(!insertedIntoEmptySlot) {
            tryReplaceSmallest(hardDrive);
        }
    }

    /**
     *
     * @param ram
     * @return true if RAM put into empty slot, false if not
     */
    private boolean tryToInsertIntoEmptySlot(RAM ram) {
        int emptySlotIdx = getEmptyIdx(rams);
        if(emptySlotIdx >= 0) {
            rams[emptySlotIdx] = ram;
            System.out.printf("Wlozno RAM %s w slot %d\n", ram, emptySlotIdx);
            return true;
        }
        else {
            System.out.println("Brak wolnego slotu");
            return false;
        }
    }

    private boolean tryToInsertIntoEmptySlot(HardDrive hardDrive) {
        int emptySlotIdx = getEmptyIdx(hardDrives);
        if(emptySlotIdx >= 0) {
            hardDrives[emptySlotIdx] = hardDrive;
            System.out.printf("Wlozno dysk twardy %s w slot %d\n", hardDrive, emptySlotIdx);
            return true;
        }
        else {
            System.out.println("Brak wolnego slotu");
            return false;
        }
    }

    /**
     *
     * @return int >= 0 if empty slot found or -1 if empty slot not found
     */
    private int getEmptyIdx(Object[] objArr) {
        for(int i = 0; i < objArr.length; i++) {
            if(objArr[i] == null)
                return i;
        }
        return -1;
    }

    private boolean tryReplaceSmallest(RAM ram) {
        int smallestSizeSlotIdx = getSmallestSizeRAMIdx();
        if(ram.isLargerThan(rams[smallestSizeSlotIdx])) {
            System.out.printf("Zamieniam mniejszy RAM %s na wiekszy RAM %s\n",
                    rams[smallestSizeSlotIdx], ram);
            rams[smallestSizeSlotIdx] = ram;
            return true;
        }
        else {
            System.out.printf("Aktualny RAM %s jest mniejszy niz najmniejszy w slocie %s\n",
                    ram, rams[smallestSizeSlotIdx]);
            return false;
        }
    }

    private int getSmallestSizeRAMIdx() {
        int smallestSizeRamIdx = 0;
        for (int i = 0; i < rams.length; i++) {
            if (rams[smallestSizeRamIdx].isLargerThan(rams[i])) {
                smallestSizeRamIdx = i;
            }
        }
        return smallestSizeRamIdx;
    }
    private boolean tryReplaceSmallest(HardDrive hardDrive) {
        int smallestSizeSlotIdx = getSmallestSizeHDIdx();
        if(hardDrive.isLargerThan(hardDrives[smallestSizeSlotIdx])) {
            System.out.printf("Zamieniam mniejszy dysk twardy %s na wiekszy dysk twardy %s\n",
                    hardDrives[smallestSizeSlotIdx], hardDrive);
            hardDrives[smallestSizeSlotIdx] = hardDrive;
            return true;
        }
        else {
            System.out.printf("Aktualny dysk twardy %s jest mniejszy niz najmniejszy zainstalowany %s\n",
                    hardDrive, hardDrives[smallestSizeSlotIdx]);
            return false;
        }
    }


    private int getSmallestSizeHDIdx() {
        int smallestSizeRamIdx = 0;
        for (int i = 0; i < hardDrives.length; i++) {
            if (hardDrives[smallestSizeRamIdx].isLargerThan(hardDrives[i])) {
                smallestSizeRamIdx = i;
            }
        }
        return smallestSizeRamIdx;
    }

    @Override
    public String toString() {
        return "motherboard: " +
                "\n\tname: " + name +
                "\n\t" + processor +
                "\n\t" + arrText("RAM", rams) +
                "\n\t" + arrText("Dyski twarde", hardDrives);
    }

    /**
     * RAM:
     *  1: name: x, size: y
     *  2:
     *  3:
     *  4:
     *
     * @return
     */
    private String arrText(String type, Object[] objArr) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(type);
        strBuilder.append(": ");
        for(int i = 0; i < objArr.length; i++) {
            strBuilder.append("\n\t\t");
            strBuilder.append(i + 1);
            strBuilder.append(": ");
            Object obj = objArr[i];
            if(obj != null) {
                strBuilder.append(obj);
            }
            else {
                strBuilder.append("---empty---");
            }
        }
        return strBuilder.toString();
    }

    public double calcScore() {
        double ramScore = 0.0;
        for(RAM ram : rams) {
            if(ram != null)
                ramScore += ram.calcScore();
        }
        ramScore /= rams.length;

        double hdScore = 0.0;
        for(HardDrive hd : hardDrives) {
            if(hd != null)
                hdScore += hd.calcScore();
        }
        hdScore /= hardDrives.length;

        return (ramScore + hdScore)/2;
    }
}
