package hw3;
import edu.colorado.nodes.DoubleNode;

public class DoubleLinkedSeq implements Cloneable {
	private int manyNodes;
	private DoubleNode head;
	private DoubleNode tail;
	private DoubleNode cursor;
	private DoubleNode precursor;
	
	public DoubleLinkedSeq() {
		manyNodes = 0;
		head = null;
		tail = null;
		cursor = null;
		precursor = null;
	}
	public void addAfter(double element) {
		if (tail == null && head == null) {
			tail = new DoubleNode(element, tail);
			head = tail;
			cursor = tail;
		}
		else if (cursor == null) {
			tail.addNodeAfter(element);
			tail = tail.getLink();
			cursor = tail;
		}
		else {
			cursor.addNodeAfter(element);
			precursor = cursor;
			cursor = cursor.getLink(); 
		}
		manyNodes++;
	}
	public void addBefore(double element) {
		if (tail == null && head == null) {
			head = new DoubleNode(element, head);
			tail = head;
			cursor = head;
		}
		else if (cursor == null || head == cursor) {
			head = new DoubleNode(element, head);
			cursor = head;
		}
		else {
			precursor.addNodeAfter(element);
			cursor = precursor.getLink();
		}
		manyNodes++;
	}
	public void addAll(DoubleLinkedSeq addend) {
		if (addend == null)
			throw new NullPointerException("The sequence you are trying "
					+ "to add is empty.");
		tail.setLink(addend.head);
		tail = addend.tail;
		manyNodes += addend.manyNodes;
	}
	public void advance() {
		if (!isCurrent())
			throw new IllegalStateException("There is no current "
					+ "element to advance.");
		
		if (cursor == tail) {
			cursor = null;
			precursor = null;
		}else {
			precursor = cursor;
			cursor = cursor.getLink();
		}
	}
	public Object clone() {
		DoubleLinkedSeq copyPart;
		
		try {
			copyPart = (DoubleLinkedSeq)super.clone();
		}catch (CloneNotSupportedException e) {
			throw new RuntimeException("This class does not implement Cloneable.");
		}
		DoubleNode[] answer = new DoubleNode[2];
		answer = DoubleNode.listCopyWithTail(copyPart.head);
		copyPart.head = answer[0];
		copyPart.tail = answer[1];
		
		if (cursor == null)
			return copyPart;
		
		copyPart.cursor = DoubleNode.listSearch(copyPart.head, cursor.getData());
		
		if (cursor == head)
			return copyPart;
		
		copyPart.precursor = DoubleNode.listSearch(copyPart.head, precursor.getData());
		
		return copyPart;
	}
	public static DoubleLinkedSeq concatenation(DoubleLinkedSeq s1, DoubleLinkedSeq s2) {
		if (s1 == null || s2 == null)
			throw new IllegalArgumentException("One of the arguments is null.");
		
		DoubleLinkedSeq s3 = new DoubleLinkedSeq();
		
		DoubleNode[] s1Answer = new DoubleNode[2];
		s1Answer = DoubleNode.listCopyWithTail(s1.head);
		s3.head = s1Answer[0];
		s3.tail = s1Answer[1];
		
		DoubleNode[] s2Answer = new DoubleNode[2];
		s2Answer = DoubleNode.listCopyWithTail(s2.head);
		s3.tail.setLink(s2Answer[0]);
		s3.tail = s2Answer[1];
		
		s3.manyNodes = s1.manyNodes + s2.manyNodes;
		return s3;
	}
	public double getCurrent() {
		if (!isCurrent())
			throw new IllegalStateException("There is no current element.");
		
		return cursor.getData();
	}
	public boolean isCurrent() {
		if (cursor != null)
			return true;
		else
			return false;
	}
	public void removeCurrent() {
		if (!isCurrent())
			throw new IllegalStateException("There is no current element.");
		
		precursor.setLink(cursor.getLink());
		cursor = cursor.getLink();
		
		if (cursor == null) {
			tail = precursor;
			precursor = null;
		}
		manyNodes--;
	}
	public int size() {
		return manyNodes;
	}
	public void start() {
		cursor = head;
	}
}
