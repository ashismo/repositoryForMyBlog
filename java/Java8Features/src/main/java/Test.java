import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] list = {3, 1, 3, 2, 6, 9, 7, 6};
		int index = search(6, list);
		System.out.println(index);
		
		
		Integer[] i = {1,2,6,9,10,5,12,11};
		int max = Arrays.asList(i).stream().mapToInt(x->x).max().getAsInt();
		System.out.println(max);
		
		
		Integer[] list1 =  {2,6,5,6,6,7,8};
		index = findIndex(list1, 8);
	}
	public static int findIndex(Integer[] list1, int searchItem) {
		int index = IntStream.range(0, list1.length).filter(x-> list1[x] == searchItem).findFirst().getAsInt();
		System.out.println(index);
		return index;
	}
	

	public static int search(Integer n, Integer[] list) {
		for (int i = 0; i < list.length; i++) {
			if (list[i] == n)
				return i;
		}
		return -1;
	}
	
}
