public class TestMain5 {
	public static void main(String[] args){

		String s1 = "abc";
		String s2 = new String("abc");
		String s3 = new String(s1);
		String s4 = new String(s2);
		String s5 = "abc";
		String s6 = "ab" + "c";
		String s7 = "ab" + new String("c");
		String s8 = new String("ab") + new String("c");
		System.out.println(s1 == s2);
		System.out.println(s2 == s3);
		System.out.println(s3 == s4);
		System.out.println(s4 == s1);
		System.out.println(s2 == s4);
		System.out.println(s1 == s3);
		System.out.println(s1 == s5);
		System.out.println(s1 == s6);
		System.out.println(s1 == s7);
		System.out.println(s1 == s8);

		System.out.println("---");
		System.out.println(s2 == s5);
		System.out.println(s3 == s5);
		System.out.println(s4 == s5);
		System.out.println("---");
		System.out.println(s2 == s6);
		System.out.println(s3 == s6);
		System.out.println(s4 == s6);

		System.out.println("=========");
		System.out.println(s1.equals(s2));
		System.out.println(s2.equals(s3));
		System.out.println(s3.equals(s4));
		System.out.println(s4.equals(s1));
		System.out.println(s2.equals(s4));
		System.out.println(s1.equals(s3));
		System.out.println(s1.equals(s5));
		System.out.println(s1.equals(s6));
		System.out.println(s1.equals(s7));
		System.out.println(s1.equals(s8));

	}
}
