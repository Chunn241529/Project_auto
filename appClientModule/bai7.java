
public class bai7 {

	// câu a
	public static int A(int n) {
		if (n == 0) {
			return 4;
		} else if (n == 1) {
			return 3;
		} else {
			return A(n - 1) - A(n - 2) - 1;
		}
	}

//	// câu b
//	public static int A(int n) {
//		if (n == 1) {
//			return 1;
//		} else if (n == 2) {
//			return 3;
//		} else {
//			return A(n - 1) + A(n - 2) / 2;
//		}
//	}

	// câu c
//	public static int A(int n, int k) {
//		if (k > n) {
//			return 0;
//		} else if (k == 0 || k == n) {
//			return 1;
//		} else {
//			return A(n - 1, k) + A(n - 1, k - 1);
//		}
//	}

	public static void main(String[] args) {
		//câu a
		int n = 0;
		int result = A(n);
		System.out.println("A(" + n + ") = " + result);

//		// câu b
//		int n = 5;
//		int result = A(n);
//		System.out.println("A(" + n + ") = " + result);

//		// câu c
//		int n = 5;
//		int k = 2;
//		int result = A(n, k);
//		System.out.println("A(" + n + ", " + k + ") = " + result);

	}
}
