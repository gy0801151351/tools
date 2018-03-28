import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class 查看JVM所有参数 {

	public static void main(String[] args) {
		StringJoiner sj = System.getProperties().entrySet().stream().reduce(new StringJoiner("\r\n"), (s, entry) -> {
			String str = entry.getKey() + " = " + entry.getValue();
			s.add(str);
			return s;
		}, StringJoiner::merge);
		System.out.println(sj.toString());
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		System.out.println("启动时间 = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(runtimeMXBean.getStartTime())));
	}
}
