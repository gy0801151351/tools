import java.nio.charset.Charset;

public class 测试Unicode {

	public static void main(String[] args) {
		Charset source = Charset.forName("iso8859-1");
		Charset target = Charset.forName("utf8");
		String s = "è§£åæ";
		byte[] bytes = s.getBytes(source);
		String s1 = new String(bytes, target);
		System.out.println(s1);
		
	}
}
