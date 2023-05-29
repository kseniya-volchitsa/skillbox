import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Company {

    private String name;
    private double operatorFix;
    private double managerFix;
    private double topmanagerFix;



    private ArrayList<Employee> employees = new ArrayList<>();

    public Company(String name, double operatorFix, double managerFix, double topmanagerFix){
        this.name = name;
        this.operatorFix = operatorFix;
        this.managerFix = managerFix;
        this.topmanagerFix = topmanagerFix;
    }

    public void hire (Employee employee){
        employee.setCompanyName(name);
        if (employee.getClass().getName() == "Operator"){
            employee.setFix(operatorFix);
        } if (employee.getClass().getName() == "Manager"){
            employee.setFix(managerFix);
        } if (employee.getClass().getName() == "TopManager"){
            employee.setFix(topmanagerFix);
            employee.setIncome(getIncome());
        }
        employees.add(employee);
    }

    public void hireAll (Collection<Employee> employes){
        for (Employee employee : employes){
            hire(employee);
        }
    }

    public void setTopManagerIncome(){
        double income = getIncome();
        for (Employee employee : employees){
            if(employee.getClass().getName() == "TopManager"){
                employee.setIncome(income);
            }
        }
    }

    public void fire (Employee employee){
        employees.remove(employee);
        setTopManagerIncome();
    }

    public double getIncome(){
        BigDecimal income = new BigDecimal("0.0");
        for (Employee employee : employees){
            if (employee.getClass().getName() == "Manager"){
                double plusdouble = Math.round(employee.getIncome() * 1.0)/1.0;
                BigDecimal plus = BigDecimal.valueOf(plusdouble);
                income = income.add(plus);
                income.setScale(0);
            }
        }
        double v = income.doubleValue();
        return v;
    }

    public List<Employee> getTopSalaryStaff(int count){
        if(count == 0 || count < 0 || count > employees.size() || employees.isEmpty()){
            return new ArrayList<>();
        }
        if(employees.size() > 0){
            Collections.sort(employees);
            ArrayList<Employee> list;
            TreeSet<Employee> set = new TreeSet<>();
                for(int i = employees.size() - 1; i >= 0 && set.size()!=count; i--){
                    set.add(employees.get(i));
                }
            list = new ArrayList<>(set);

            return list;
    }
        return new ArrayList<>();
    }

    public List<Employee> getLowestSalaryStaff(int count){
        if(count == 0 || count < 0 || count > employees.size() || employees.isEmpty()){
            return new ArrayList<>();
        }
        if(employees.size() > 0){
        Collections.sort(employees);
        ArrayList<Employee> list;
            TreeSet<Employee> set = new TreeSet<>();
                for(int i = 0; i < employees.size() && set.size()!=count; i++){
                    set.add(employees.get(i));
                }
        list = new ArrayList<>(set);

        return list;
    }
        return new ArrayList<>();
    }

    public void companyInfo(){
        int countOp = 0;
        int countMan = 0;
        int countTop = 0;
        for(Employee employee : employees){
            if (employee.getClass().getName() == "Operator"){
                countOp++;
            } if (employee.getClass().getName() == "Manager"){
                countMan++;
            } if (employee.getClass().getName() == "TopManager"){
                countTop++;
            }
        }
        System.out.println("Компания " + name);
        System.out.println("Операторов " + countOp + " c фикс.окладом " + operatorFix);
        System.out.println("Менеджеров " + countMan + " c фикс.окладом " + managerFix);
        System.out.println("Топ менеджеров " + countTop + " c фикс.окладом " + topmanagerFix);
        System.out.println("Доход компании за месяц: " + getIncome());
        System.out.println("15 самых высоких зарплат: ");
        for (Employee employee : getTopSalaryStaff(15)) {
            System.out.println(employee);
        }
        System.out.println("30 самых низких зарплат: ");
        for (Employee employee : getLowestSalaryStaff(30)) {
            System.out.println(employee);
        }
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
