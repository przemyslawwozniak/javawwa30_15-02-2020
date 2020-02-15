package pl.sda.javawwa30;

public class Motherboard {

    private String name;
    private Processor processor;
    private RAM[] rams;
    private HardDrive[] hardDrives;

    public Motherboard(String name, Processor processor, int ramSlots) {
        this.name = name;
        this.processor = processor;
        this.rams = new RAM[ramSlots];
    }

    public Motherboard(String name, Processor processor, int ramSlots, RAM ram) {
        this(name, processor, ramSlots);
        this.rams[0] = ram;
    }

    /**
     * Client provides a bunch of RAMs and we pick up the optimal ones (biggest total RAM)
     * @param name
     * @param processor
     * @param ramSlots
     * @param rams
     */
    public Motherboard(String name, Processor processor, int ramSlots, RAM[] rams) {
        this(name, processor, ramSlots);
        for(RAM ram : rams) {
            add(ram);
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
            tryToInsertIntoSmallestSlot(ram);
        }
    }

    /**
     *
     * @param ram
     * @return true if RAM put into empty slot, false if not
     */
    private boolean tryToInsertIntoEmptySlot(RAM ram) {
        int emptySlotIdx = getEmptySlotIdx();
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

    /**
     *
     * @return int >= 0 if empty slot found or -1 if empty slot not found
     */
    private int getEmptySlotIdx() {
        for(int i = 0; i < rams.length; i++) {
            if(rams[i] == null)
                return i;
        }
        return -1;
    }

    private boolean tryToInsertIntoSmallestSlot(RAM ram) {
        int smallestSizeSlotIdx = getSmallestSizeSlotIdx();
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

    private int getSmallestSizeSlotIdx() {
        int smallestSizeRamIdx = 0;
        for (int i = 0; i < rams.length; i++) {
            if (rams[smallestSizeRamIdx].isLargerThan(rams[i])) {
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
                ramText();
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
    private String ramText() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("\n\tRAM: ");
        for(int i = 0; i < rams.length; i++) {
            strBuilder.append("\n\t\t");
            strBuilder.append(i + 1);
            strBuilder.append(": ");
            RAM ram = rams[i];
            if(ram != null) {
                strBuilder.append(ram);
            }
            else {
                strBuilder.append("---empty---");
            }
        }
        return strBuilder.toString();
    }
}
