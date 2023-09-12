package Tool;

import java.util.UUID;

public class random_UUID {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String randomName = UUID.randomUUID().toString();
            System.out.println(randomName);
        }
    }
}