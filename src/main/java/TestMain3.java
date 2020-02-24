import com.test.util.NumberUtil;

public class TestMain3 {
	public static final int a = 127;
	public static void main(String[] args){
		System.out.println(Test.f);

//		A.a = 20;

		AC ac = new AC();
		System.out.println(AC.a);

	}

	class Test{
		public static final short a = 127;
		public static final int b = 128;
		public static final int c = 20;
		public static final int d = 20;
		public static final int f = 50;
	}

}
interface A{
	int a = 10;
}
interface B{
	int b = 0;
}

class AC implements A {
}
