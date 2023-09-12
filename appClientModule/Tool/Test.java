package Tool;

public class Test {
	public static void main(String[] args) {
		double probability = Math.random(); // Sinh ra một số ngẫu nhiên từ 0.0 đến 1.0

		if (probability <= 0.5) {
			// Thực hiện hành động khi xác suất là 30%
			System.out.println("Xác suất 30% xuất hiện.");
		} else {
			// Thực hiện hành động khi xác suất không phải 30%
			System.out.println("Xác suất không phải 30%.");
		}
	}
}
