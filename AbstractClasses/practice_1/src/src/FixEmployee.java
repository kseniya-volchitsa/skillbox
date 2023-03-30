public abstract class FixEmployee
implements Employee{

    protected double fix;
    protected String companyName;
    protected double income;

    protected double monthSalary = getMonthSalary();

    public FixEmployee(){
        this.fix = 0;
        this.companyName = "";
    }

    public double getIncome() {
        return income;
    }
    @Override
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public void setIncome(double income){
        this.income = income;
    }

    @Override
    public void setFix(double fix){
        this.fix = fix;
    }

    @Override
    public int compareTo(Object o) {
        Employee employee = (Employee) o;
        return Integer.compare((int) getMonthSalary(), (int) employee.getMonthSalary());
    }

    @Override
    public String toString() {
        return "FixEmployee{" + getClass().getName() +
                "fix=" + fix +
                ", companyName='" + companyName + '\'' +
                ", month salary =" + getMonthSalary() +
                ", income =" + income +
                '}';
    }
}
