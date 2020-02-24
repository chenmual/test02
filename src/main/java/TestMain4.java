import com.test.util.NumberUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class TestMain4 {

	public static void main(String[] args){
//		System.out.println(NumberUtil.getMaxCommonDivisor(35, 28));
//		System.out.println(NumberUtil.getMinCommonMultiple(35, 28));

		String targetPath = "J:\\p\\icon4\\";
//		File target = new File("J:\\p\\icon4");
		File file = new File("J:\\p\\icon3");
		File[] files = file.listFiles();
		Arrays.stream(files).forEach(file1 -> {
			if(file1.isDirectory()){
				File[] files2 = file1.listFiles();
				for(File src : files2){
					try {
						Files.copy(src.toPath(), new File(targetPath + src.getName()).toPath());
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
//			System.out.println(file1.isDirectory()? "目录:" + file1.toString() : "文件:" + file1.toString());
		});
	}

}
