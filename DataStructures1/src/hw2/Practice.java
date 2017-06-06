package hw2;
import java.util.*;

public class Practice {

	public static void main(String[] args) {
		Set<Integer> set1 = new HashSet<Integer>();
		
		// A set's add method will add an element if it is not
		// already present in the set, and return true.  
		set1.add(20);  // of all these ints are being autoboxed to their 
		set1.add(10);	// corresponding wrapper class (Integer)
		set1.add(5);
		set1.add(20);
		// A set displays its contents with no regard to the order
		// in which the elements were added to the set.
		System.out.println(set1);
		
		System.out.println(set1.size());
		System.out.println(set1.contains(20));
		
		set1.remove(5);
		System.out.println(set1);
	
		//-------------------------------------------------------------------
		System.out.print("\n\n");
		List<Integer> list1 = new ArrayList<Integer>();
		// The add method of the ArrayList class adds the new element 
		// to the end of the list.  You can also add duplicate elements 
		// to a list (unlike a set).
		list1.add(23);
		list1.add(12);
		list1.add(55);
		list1.add(12);
		// If you print the contents of a list, the order of the list will
		// reflect the order in which the elements were added to the list
		// unlike a set.  
		System.out.println(list1);
		// With a list, you can use the get method to retrieve an element 
		// at a specified index.  The element is not removed from the list. 
		System.out.println(list1.get(2));
		// the set method of a list can be used to replace an element at a specified
		// index
		list1.set(3, 64);
		System.out.println(list1);
		// the indexOf method can be used to find the index of a specified
		// element
		System.out.println(list1.indexOf(12));
		// the List interface has a remove method that allows you to 
		// remove an element at a specific index, unlike Set
		list1.remove(0);
		System.out.println(list1);
		// unlike Set, the List interface has an add method in which you can 
		// add a specified element to a specific index.  The element that was 
		// at that index as well as everything after it will be shifted to the 
		// right
		list1.add(1, 33);
		System.out.println(list1);
		
		// the List interface also has a method to return the last index
		// of a specified element.  This is useful if you have duplicate 
		// elements in the list 
		list1.add(33);
		System.out.println(list1.lastIndexOf(33));
		System.out.print("\n\n");
		//------------------------------------------------------------------
		Map<Integer, String> map1 = new HashMap<Integer, String>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Hello world");
		System.out.println(sb);
	}
}
