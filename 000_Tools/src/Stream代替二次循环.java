import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;

public class Stream代替二次循环 {
	
	protected static NumberFormat nf = new DecimalFormat("#########000");
	
	static Integer[] a1;
	static Integer[] a2;
	static {
		int size = 10;
		a1 = new Integer[size];
		a2 = new Integer[size];
		for (int i = 1; i <= size; i++) {
			a1[i-1] = i;
			a2[i-1] = i;
		}
	}
	
	public static void old() {
		int row = 0;
		for (int i : a1) {
			for (int j : a2) {
				System.out.println(nf.format(++row) + " old - " + i + " x " + j + " = " + (i*j));
			}
		}
	}
	
	public static void stream() {
		class IntPair {
			int a;
			int b;
			public IntPair(int a, int b) {
				super();
				this.a = a;
				this.b = b;
			}
		}
		AtomicInteger row = new AtomicInteger(0);
		long t1 = System.currentTimeMillis();
		ArrayList<String> l = Arrays.stream(a1).flatMap(a -> Arrays.stream(a2).map(b -> new IntPair(a, b))).parallel().peek(pair -> {
			System.out.println(nf.format(row.incrementAndGet()) + " new - " + pair.a + " x " + pair.b + " = " + (pair.a*pair.b));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).collect(Collector.of(ArrayList<String>::new, (list, pair) -> {
			list.add(pair.a + " x " + pair.b);
		}, (l1, l2) -> {
			l1.addAll(l2);
			return l1;
		}));
		long t2 = System.currentTimeMillis();
		l.stream().forEach(s -> {
			System.out.println(s);
		});
		System.out.println("总时间: " + (t2 - t1) * 0.001 + "s");
	}
	
	public static void main(String[] args) {
		stream();
	}
}
