import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class 测试日期与整型值 {

	public static void main(String[] args) {
		long datetime1 = 1522512000000L;
		long datetime2 = 1519791844570L;
		Date date1 = new Date(datetime1);
		Date date2 = new Date(datetime2);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(format.format(date1));
		System.out.println(format.format(date2));
	}
}
