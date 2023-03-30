public class ScreenOfComputer {
    public final double diagonal;
    public final TypeOfScreen typeOfScreen;
    public final double weight;

    public ScreenOfComputer(double diagonal, TypeOfScreen typeOfScreen, double weight){
        this.diagonal = diagonal;
        this.typeOfScreen = typeOfScreen;
        this.weight = weight;
    }
    public double getDiagonal (){return diagonal;}
    public TypeOfScreen getTypeOfScreen(){return typeOfScreen;};
    public double getWeight(){return weight;}

    public String toString(){
        return "Экран" + "\n" +
                "Диагональ: " + getDiagonal() + "\n" +
                "Тип: " + getTypeOfScreen() + "\n" +
                "Вес: " + getWeight() + "\n";
    }
}
