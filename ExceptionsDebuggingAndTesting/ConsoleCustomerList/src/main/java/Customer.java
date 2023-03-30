public class Customer {
    private final String name;
    private final String phone;
    private final String email;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Customer(String name, String phone, String email) {
        this.name = name;
        if(!checkNumber(phone)){
            throw new IllegalArgumentException("Неверный формат номера");
        } else {
            this.phone = startFormatPhoneNumber(phone);
        }
        if(!checkEmail(email)){
            throw new IllegalArgumentException("Неверный формат почты");
        } else {
            this.email = email;
        }

    }

    public boolean checkNumber(String number) {
        String template = "[+]?[7-8]{1}[0-9]{10}";
        if (!number.matches(template)) return false;
        else return true;
    }
    public boolean checkEmail(String email) {
        String template = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";
        if (!email.matches(template)) return false;
        else return true;
    }
    public String startFormatPhoneNumber(String number) {
        String result = "";
        if (number.length() == 12){
            String numbers = number.substring(1, 12);
            result += "+" + formatPhoneNumber(numbers);
        }
        if (number.length() == 11) {
            result += "+" + formatPhoneNumber(number);
        }
        if (number.length() == 10) {
            result += "+" + 7 + formatPhoneNumber(number);
        }
        return result;
    }

    public String formatPhoneNumber(String phone){
        String regex = "[^0-9]";
        String onlyNumbers = phone.replaceAll(regex, "");
        StringBuilder result = new StringBuilder();
        char[] chars = onlyNumbers.toCharArray();
        if (chars[0] == '8'){
            chars[0] = '7';
        }
        for (char c : chars) {
            result.append(c);
        }
        return result.toString();
    }


    public String toString() {
        return name + " - " + email + " - " + phone;
    }
}
