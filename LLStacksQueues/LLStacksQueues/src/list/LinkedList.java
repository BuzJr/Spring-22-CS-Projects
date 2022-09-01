package list;

/**
 * 
 * A custom built linked list
 * T here is the type the list stores
 */
public class LinkedList<T> implements List<T>{

	/* Dummy head and tail */
	private ListNode<T> head, tail;
	private int size;
	
	public LinkedList() {
		/* TODO: Implement this method */ 
		head = new ListNode<T>(null);
		tail = new ListNode<T>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}
	
	public int size() {
		/* TODO: Implement this method */  
		return size;
	}
	
	/**
	 * Clears out the entire list
	 */
	public void clear() {
		/* TODO: Implement this method */
		head.next = tail;
		tail.prev = head;
		size = 0;
	}
	
	/**
	 * Inserts new data at the end of the list (i.e., just before the dummy tail node)
	 * @param data
	 */
	public void insertAtTail(T data) {
		/* TODO: Implement this method */
		ListNode<T> newNode = new ListNode<T>(data);
		newNode.next = tail;
		newNode.prev = tail.prev;
		tail.prev.next = newNode;
		tail.prev = newNode;
		
		this.size++;
	}
	
	/**
	 * Inserts data at the front of the list (i.e., just after the dummy head node
	 * @param data
	 */
	public void insertAtHead(T data) {
		/* TODO: Implement this method */  
		ListIterator<T> it = front();
		ListNode<T> newNode = new ListNode<T>(data);
		if(it.curNode.equals(null)) {
			tail.prev = newNode;
			head.next = newNode;
		}
		newNode.prev = head;
		newNode.next = head.next;
		head.next.prev = newNode;
		head.next = newNode;
		
		this.size++;
	}
	
	/**
	 * Inserts node such that index becomes the position of the newly inserted data
	 * @param data
	 * @param index
	 */
	public void insertAt(int index, T data) {
		/* TODO: Implement this method */
		ListNode<T> newNode = new ListNode<T>(data);
		ListNode<T> node = head;
		int i = 0;
		while(i<index) {
			node = node.next;
			i++;
		}
		newNode.next = node.next;
		node.next.prev = newNode;
		node.next = newNode;
		newNode.prev = node;
		size++;
		
		
	}
	
	/**
	 * Inserts data after the node pointed to by iterator
	 */
	public void insert(ListIterator<T> it, T data) {
		/* TODO: Implement this method */ 
		ListNode<T> newNode = new ListNode<T>(data);
		newNode.next = it.curNode.next;
		it.curNode.next.prev = newNode;
		it.curNode.next = newNode;
		newNode.prev = it.curNode;
		size++;
		
		
	}
	
	
	
	public T removeAtTail(){
		/* TODO: Implement this method */ 
		tail.prev.prev.next = tail;
		tail.prev = tail.prev.prev;
		size -=1;
		return null;
	}
	
	public T removeAtHead(){
		/* TODO: Implement this method */
		if(size==0)
			return null;
		Object x = head.next.getData();
		head.next.next.prev = head;
		head.next = head.next.next;
		size -=1;
		return (T)x;
	}
	
	/**
	 * Remove based on Iterator position
	 * Sets the iterator to the node AFTER the one removed
	 */
	public T remove(ListIterator<T> it) {
		/* TODO: Implement this method */
		it.curNode.prev.next = it.curNode.next;
		it.curNode.next.prev = it.curNode.prev;
		it.moveForward();
		size -=1;
		return null;
	}
	
	/**
	 * Returns index of first occurrence of the data in the list, or -1 if not present
	 * @param data
	 * @return
	 */
	public int find(T data) {
		/* TODO: Implement this method */  
		ListNode<T> node = head.next;
		int index = 0;
		while(!node.getData().equals(data)) {
			node = node.next;
			index +=1;
			if(index==size)
				break;
		}
		if(index!=size)
			return index;
		else
			return -1;
		
	
	}
	
	/**
	 * Returns the data at the given index, null if anything goes wrong (index out of bounds, empty list, etc.)
	 * @param index
	 * @return
	 */
	public T get(int index) { 
		/* TODO: Implement this method */ 
		int i = 0;
		ListNode<T> node = head.next;
		while(i < index) {
			node = node.next;
			i++;
		}
		
		return node.getData();
	}
	
	/**
	 * Returns the list as space separated values
	 */
	public String toString() {
		String toRet = "[";
		
		ListNode<T> curNode = head.next;
		while(curNode != tail) {
			toRet += curNode.getData() + ", ";
			curNode = curNode.next;
		}
		
		return toRet + "]";
	}
	
	/* Return iterators at front and end of list */
	public ListIterator<T> front(){ 
		/* TODO: Implement this method */  
		ListIterator<T> it = new ListIterator<T>(head.next);
		return it;
	}

	public ListIterator<T> back(){
		/* TODO: Implement this method */ 
		ListIterator<T> it = new ListIterator<T>(tail.prev);
		return it;
	}
	
	
}
