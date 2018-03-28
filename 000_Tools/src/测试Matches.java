
public class 测试Matches {

	public static void main(String[] args) {
		String str = "123aaa";
		boolean b = str.substring(0, 1).matches("^[0-9]$");
		System.out.println(b);
	}
}
