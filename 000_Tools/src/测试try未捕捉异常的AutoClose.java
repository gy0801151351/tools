import java.io.IOException;

public class 测试try未捕捉异常的AutoClose {

	static class TestAutoClose implements AutoCloseable {

		@Override
		public void close() throws Exception {
			System.out.println("测试自动关闭!");
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		try (TestAutoClose test = new TestAutoClose()) {
			if (true) {
				throw new Exception("嘿嘿");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("测试Finally...");
		}
	}
}
