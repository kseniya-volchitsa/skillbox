import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company = new Company("company", 60_000, 80_000, 120_000);
        for(int i = 0; i < 180; i++){
            company.hire(new Operator());
        }
        for(int i = 0; i < 80; i++){
            company.hire(new Manager());
        }
        for(int i = 0; i < 10; i++){
            company.hire(new TopManager());
        }
        System.out.println("After hire");
        System.out.println("Top: ");
        biggest(company.getTopSalaryStaff(15));
        System.out.println("Lowest: ");
        lowest(company.getLowestSalaryStaff(30));

        System.out.println();
        int countOp = 0;
        int countMan = 0;
        int countTop = 0;
        List<Employee> list = company.getEmployees();
        int length = list.size() - 1;
        for(int i = 0; i < length; i++) {
            Employee element = list.get(i);
            if ((element.getClass().getName() == "Operator") && countOp < 90) {
                company.fire(element);
                countOp++;
                i--;
                length--;
            } if ((element.getClass().getName() == "Manager") && countMan < 40) {
                company.fire(element);
                countMan++;
                i--;
                length--;
            } if ((element.getClass().getName() == "TopManager") && countTop < 5) {
                company.fire(element);
                countTop++;
                i--;
                length--;
            }
        }
        System.out.println("After fire");
        System.out.println("Top: ");
        biggest(company.getTopSalaryStaff(15));
        System.out.println("Lowest: ");
        lowest(company.getLowestSalaryStaff(30));
    }

    public static void biggest(List<Employee> list){
        for (int i = list.size() - 1; i > 0; i--){
            System.out.println(list.get(i).getMonthSalary());
        }
    }

    public static void lowest(List<Employee> list){
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getMonthSalary());
        }
    }

}
