public class Storage {
    public final TypeOfStorage typeOfStorage;
    public final int volumeOfMemory;
    public final double weight;

    public Storage(TypeOfStorage typeOfStorage, int volumeOfMemory, double weight) {
        this.typeOfStorage = typeOfStorage;
        this.volumeOfMemory = volumeOfMemory;
        this.weight = weight;
    }

    public TypeOfStorage getTypeOfStorage() {
        return typeOfStorage;
    }

    public int getVolumeOfMemory() {
        return volumeOfMemory;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "Накопитель информации" + "\n" +
                "Тип: " + getTypeOfStorage() + "\n" +
                "Объём памяти: " + getVolumeOfMemory() + "\n" +
                "Вес: " + getWeight() + "\n";
    }
}
