public class Processor {
    public final double frequency;
    public final int countOfCores;
    public final String vendor;
    public final double weight;

    public Processor(double frequency, int countOfCores, String vendor, double weight) {
        this.frequency = frequency;
        this.countOfCores = countOfCores;
        this.vendor = vendor;
        this.weight = weight;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getCountOfCores() {
        return countOfCores;
    }

    public String getVendor() {
        return vendor;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "Процессор \n" + "Частота: " + getFrequency() + "\n" +
                "Количество ядер: " + getCountOfCores() + "\n" +
                "Производитель: " + getVendor() + "\n" +
                "Вес: " + getWeight() + "\n";
    }
}
