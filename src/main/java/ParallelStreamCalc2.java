import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 未完善
 */
public class ParallelStreamCalc2 {
//	public static final int CONST0INT = 987654321;
//	public static final int CONST_INT = 866278171;
	public static final int CONST_INT = 866278171;
	//140: 3=1 3x=5 + 1 1x3=4 13x=5+1

	public static int s_count = 0;
	public static int s_highest = 1;
	public static int s_maxhighsite = 0;

	public static void main(String[] args){
		int ret = CONST_INT;
		while(true){
			if((ret / 10) == 0){
				break;
			}else{
				s_highest++;
				ret = ret / 10;
			}
		}
		s_maxhighsite = CONST_INT / (int)(Math.pow(10, (s_highest - 1)));

//		verify();
		verify2();
		for(int i = 0; i < 10; i++){
			if((i & 1) == 1){
				if(i == 3){
//					s_count++;
					calcNext(2, i, 1);
				}else{
					calcNext(2, i, 0);
				}
			}
		}
		System.out.println(s_count);
	}


	static void calcNext(int flag, int prototype, int tricount){
		if(flag == s_highest){
			for(int i = 0; i <= s_maxhighsite; i++){
				int now_pro = prototype + (pow(10, flag - 1)) * i;
				if(now_pro <= CONST_INT){
					if(i == 3){
						s_count = s_count + tricount + 1;
					}else{
						s_count = s_count + tricount;
					}
				}
			}
		}else{
			for(int i = 0; i < 10; i++){
				int now_pro = prototype + (pow(10, flag - 1)) * i;
				if(i == 3){
					calcNext(flag + 1, now_pro, tricount + 1);
				}else{
					calcNext(flag + 1, now_pro, tricount);
				}
			}
		}
	}

	static void verify1() {
		File file = new File("out");
		if(file.exists()){
			file.delete();
		}
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			fos = new FileOutputStream(new File("out"), true);
			osw = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(osw);
			for(int i = 1; i <= CONST_INT; i++){
				if((i & 1) == 1){
					bw.write(String.valueOf(i));
					bw.write("\n");
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.flush();
				bw.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

//		StringReader sr = new StringReader(sb.toString());
//		int cout = 0;
//		try {
//			while(true){
//				int r = sr.read();
//				if(r == -1){
//					break;
//				}else{
//					if(r == 51){
//						cout++;
//					}
//				}
//			}
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("count = " + cout);
//		System.out.println("count = " + sb.toString());
	}

	static void verify2() {
		int _count = 0;
		for(int i = 1; i <= CONST_INT; i++){
			if((i & 1) == 1){
				StringReader sr = new StringReader(String.valueOf(i));
				try {
					while(true){
						int r = sr.read();
						if(r == -1){
							break;
						}else{
							if(r == 51){
								_count++;
							}
						}
					}
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("count = " + _count);
	}

	static int pow(int ori, int pow){
		int ret = ori;
		while(pow-- > 1){
			ret = ret * ori;
		}
		return ret;
	}
}
