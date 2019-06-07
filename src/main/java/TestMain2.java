import java.util.ArrayList;
import java.util.List;

public class TestMain2 {
	public static void main(String[] args){
		List<Integer> listpara = new ArrayList<>();
		listpara.add(20);
		listpara.add(35);
		listpara.add(50);
		int x = listpara.parallelStream().mapToInt(i -> i + 2).sum();
		System.out.println(x);

		System.out.println("-------");
		String a = "abc";
		String b = "abc";
		String c = new String("abc");
		String d = new String(b);
		String e = new String(c);
		String f = "ab" + "c";
//		String g = f = c;
		String g = f;
		String h = new String("abc");
		System.out.println("a == abc " + (a == "abc"));
		System.out.println("a == b " + (a == b));
		System.out.println("a == c " + (a == c));
		System.out.println("d == b " + (d == b));
		System.out.println("a == d " + (a == d));
		System.out.println("c == d " + (c == d));
		System.out.println("e == c " + (e == c));
		System.out.println("d == e " + (d == e));
		System.out.println("f == a " + (f == a));
		System.out.println("f == c " + (f == c));
		System.out.println("h == c " + (h == c));
//		System.out.println(c.equals(b));
//		System.out.println(g.equals(a));
//		System.out.println(g.equals(f));
	}
}
