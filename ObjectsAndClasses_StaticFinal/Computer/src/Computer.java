public class Computer {

    public final String name;
    public final String vendor;
    private Processor processor;
    private Ram ram;
    private Storage storage;
    private ScreenOfComputer screenOfComputer;
    private Keyboard keyboard;


    public Computer(String name, String vendor, Processor processor, Ram ram, Storage storage,
                    ScreenOfComputer screenOfComputer, Keyboard keyboard) {
        this.name = name;
        this.vendor = vendor;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.screenOfComputer = screenOfComputer;
        this.keyboard = keyboard;
    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public Processor getProcessor() {
        return processor;
    }

    public Ram getRam() {
        return ram;
    }

    public Storage getStorage() {
        return storage;
    }

    public ScreenOfComputer getScreenOfComputer() {
        return screenOfComputer;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setScreenOfComputer(ScreenOfComputer screenOfComputer) {
        this.screenOfComputer = screenOfComputer;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }
    public double getTotalWeight(){
        return processor.getWeight() + ram.getWeight() + storage.getWeight() +
                screenOfComputer.getWeight() + keyboard.getWeight();
    }
    public String toString(){
        return "Компьютер: " + getName() + "\n" +
                "Собран: " + getVendor() + "\n" +
                "Общий вес: " + getTotalWeight() + "\n" +
                processor + ram + storage + screenOfComputer + keyboard + "\n";
    }
}
