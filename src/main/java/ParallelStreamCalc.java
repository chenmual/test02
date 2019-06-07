import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 未完善
 */
public class ParallelStreamCalc {
	public static void main(String[] args){
		int max = (int)(Math.sqrt(707829217)) + 2;
//		System.out.println(max);
		List<Integer> nums = new ArrayList<>();
		for(int i = 1; i < max; i++){
			nums.add(i);
		}
		nums.parallelStream().forEach(n -> {
			if(isPrime(n)){
				if(707829217 % n == 0){
					System.out.println("n1=" + n + " n2=" + (707829217 / n));
				}
			}
		});
	}

	static boolean isPrime(int num){
		int max = num >> 1;
		for(int i = 2; i <= max; i++){
			if(num % i == 0){
				return false;
			}
		}
		return true;
	}
}
