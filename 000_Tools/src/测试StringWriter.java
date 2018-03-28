import java.io.PrintWriter;
import java.io.StringWriter;

public class 测试StringWriter {

	public static void main(String[] args) {
		StringWriter sw = new StringWriter(1024);
		PrintWriter s = new PrintWriter(sw);
		String content = null;
		try {
			if (true) {
				throw new NullPointerException();
			}
		} catch (Exception e) {
			try {
				e.printStackTrace(s);
				s.flush();
				content = sw.toString();
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					s.close();
				} catch (Exception e3) {
				}
				try {
					sw.close();
				} catch (Exception e3) {
				}
			}
		}
		System.out.println(content);
	}
}
