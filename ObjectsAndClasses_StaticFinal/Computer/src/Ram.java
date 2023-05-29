public class Ram {
    public final TypeOfRam typeOfRam;
    public final int volumeOfMemory;
    public final double weight;

    public Ram(TypeOfRam typeOfRam, int volumeOfMemory, double weight) {
        this.typeOfRam = typeOfRam;
        this.volumeOfMemory = volumeOfMemory;
        this.weight = weight;
    }

    public TypeOfRam getTypeOfRam() {
        return typeOfRam;
    }

    public int getVolumeOfMemory() {
        return volumeOfMemory;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "Оперативная память" + "\n" +
                "Тип: " + getTypeOfRam() + "\n" +
                "Объём: " + getVolumeOfMemory() + "\n" +
                "Вес: " + getWeight() + "\n";
    }
}
