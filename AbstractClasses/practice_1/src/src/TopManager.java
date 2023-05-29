import java.math.BigDecimal;

public class TopManager extends FixEmployee{
    private static final double BONUS = 1.5D;

    @Override
    public double getMonthSalary() {
        double monthSalary;
        BigDecimal income = BigDecimal.valueOf(this.income);
        BigDecimal compare = new BigDecimal("10000000.0");
        if (income.compareTo(compare) > 0){
            monthSalary = Math.round((fix + (fix * BONUS)) * 1.0d)/1.0d;
        } else {
            monthSalary = Math.round(fix * 1.0d)/1.0d;
        }
        return monthSalary;
    }

    @Override
    public double getIncome() {
        return income;
    }


}
