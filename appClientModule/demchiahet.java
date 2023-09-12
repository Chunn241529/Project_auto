import java.util.Scanner;

public class demchiahet {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Nhập số lượng phần tử của mảng: ");
		int N = scanner.nextInt();

		int[] A = new int[N];

		System.out.println("Nhập các phần tử của mảng:");
		for (int i = 0; i < N; i++) {
			System.out.print("A[" + i + "] = ");
			A[i] = scanner.nextInt();
		}

		int count = countDivisibleByTwoAndFive(A);
		System.out.println("Số lượng phần tử chia hết cho 2 và 5 trong mảng là: " + count);

		scanner.close();
	}

	public static int countDivisibleByTwoAndFive(int[] arr) {
		int count = 0;
		for (int num : arr) {
			if (num % 2 == 0 && num % 5 == 0) {
				count++;
			}
		}
		return count;
	}
}
