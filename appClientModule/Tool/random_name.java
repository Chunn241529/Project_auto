package Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class random_name {
    public static void main(String[] args) {
        List<String> firstNames = new ArrayList<>();
        firstNames.add("Trung");
        firstNames.add("Linh");
        firstNames.add("Thuan");
        firstNames.add("Vu");
        firstNames.add("Quan");
        firstNames.add("Sang");
        firstNames.add("Long");
        firstNames.add("Ngan");
        firstNames.add("Bang");
        firstNames.add("Ba");
        firstNames.add("Vien");
        firstNames.add("Quang");
        firstNames.add("Duc");
        firstNames.add("Trinh");
        firstNames.add("Nhi");
        // Thêm các tên người dùng khác vào danh sách
        
        List<String> lastNames = new ArrayList<>();
        lastNames.add("Vuong");
        lastNames.add("Nguyen");
        lastNames.add("Tran");
        lastNames.add("dev");
        lastNames.add("tester");
        lastNames.add("develop");
        lastNames.add("editor");
        lastNames.add("hoasi");
        lastNames.add("Smith");
        lastNames.add("ne");
        lastNames.add("Sunshine");
        lastNames.add("QA");
        // Thêm các họ khác vào danh sách
        
        int numberOfUsers = 100; // Số lượng người dùng bạn muốn tạo
        
        Random random = new Random();
        
        for (int i = 0; i < numberOfUsers; i++) {
            String randomFirstName = firstNames.get(random.nextInt(firstNames.size()));
            String randomLastName = lastNames.get(random.nextInt(lastNames.size()));
            String username = randomFirstName.toLowerCase() + randomLastName.toLowerCase() + i;
            
            System.out.println("User " + (i + 1) + ": " + username);
        }
    }
}
