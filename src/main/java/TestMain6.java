import java.util.concurrent.*;

public class TestMain6 {
	public static void main(String[] args){
		int a = 0;
		System.out.println(1);
		System.out.println(2);
		System.out.println(3);
		try {
			a = a / a;
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(4);
		System.out.println(5);
	}

}
