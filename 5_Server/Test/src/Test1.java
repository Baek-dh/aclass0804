import java.util.Arrays;

public class Test1 {
	public static void main(String[] args) {
		String[] arr = "파일명1||파일명2||파일명3".split("\\|\\|");
		System.out.println(Arrays.toString(arr));
	}
}
