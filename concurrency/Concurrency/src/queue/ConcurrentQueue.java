package queue;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/**
 * A Linked-List based Queue
 * Is concurrent (i.e., can modify front and back in parallel)
 *
 * @param <T>
 */
public class ConcurrentQueue<T> implements IQueue<T>{
	private int size;
	private LinkedList<T> list;
	private Lock queuelock;
	private int count;
	private Condition queueCondition;
	/**
	 * Constructor: Initialize the inner list
	 */
	public ConcurrentQueue(){
		//TODO: Write this method
		list = new LinkedList<T>();
		queuelock = new ReentrantLock();
		queueCondition = queuelock.newCondition();
	}
	
	/**
	 * Return the size by invoking the size of the list
	 */
	public int size() { 
		//TODO: Write this method
		return list.size();
	}
	

	/**
	 * Simply add the data to the tail of the linked list
	 */
	public void enqueue(T data) {
		
		//TODO: Write this method
		queuelock.lock();
		try {
			list.addLast(data);
			queueCondition.signalAll();
		}
		finally {
			queuelock.unlock();
		}
		
	}
	
	/**
	 * Simply remove data from the head of the list
	 */
	public T dequeue(){	
		
		//TODO: Write this method
		T ret = null;
		queuelock.lock();
		try {
			while(size()==0)
				queueCondition.await();
			ret = list.removeFirst();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			queuelock.unlock();
		}
		return ret;
		
	}
	
	
}
