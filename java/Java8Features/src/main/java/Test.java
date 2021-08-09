
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] list = {3, 1, 3, 2, 6, 9, 7, 6};
		int index = search(6, list);
		System.out.println(index);
	}

	public static int search(Integer n, Integer[] list) {
		for (int i = 0; i < list.length; i++) {
			if (list[i] == n)
				return i;
		}
		return -1;
	}
	
}
