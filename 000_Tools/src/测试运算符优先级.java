public class 测试运算符优先级 {

	public static void main(String[] args) {
		Boolean a = false;
		if (a != null && !a) {
			System.out.println(a);
		} else {
			System.out.println("测试未通过");
		}
	}
}
