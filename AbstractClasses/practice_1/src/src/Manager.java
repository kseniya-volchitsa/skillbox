public class Manager
extends FixEmployee{

    private static final double BONUS = 0.05D;

    public Manager(){
        super();
        this.income = Math.round((115_000 + (Math.random() * 25_000)) * 1.0d)/1.0d;
    }

    @Override
    public double getMonthSalary() {
        double result = fix + (this.income * BONUS);
        double monthSalary = Math.round(result * 1.0d)/1.0d;
        return monthSalary;
    }
}
