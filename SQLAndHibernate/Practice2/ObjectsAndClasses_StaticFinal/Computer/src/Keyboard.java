public class Keyboard {
    public final String typeOfKeyboard;
    public final Illumination illumination;
    public final double weight;

    public Keyboard(String typeOfKeyboard, Illumination illumination, double weight) {
        this.typeOfKeyboard = typeOfKeyboard;
        this.illumination = illumination;
        this.weight = weight;
    }

    public String getTypeOfKeyboard() {
        return typeOfKeyboard;
    }

    public Illumination getIllumination() {
        return illumination;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "Клавиатура" + "\n" +
                "Тип: " + getTypeOfKeyboard() + "\n" +
                "Наличие подсветки: " + getIllumination() + "\n" +
                "Вес: " + getWeight() + "\n";
    }
}
