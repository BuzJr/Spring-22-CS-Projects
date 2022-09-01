package hash;


/**
 * Hash Table implementation. Uses linear probing to resolve collisions.
 * @author Mark Floryan
 *
 * @param <K>
 * @param <V>
 */
public class HashTable<K,V> implements Map<K,V>{

	/* The array of objects and related things */
	private HashNode<K,V>[] table;
	
	/* YOU WILL LIKELY WANT MORE PRIVATE VARIABLES HERE */
	private int size;
	private static final int INITIAL_CAP = 10000;
	private V sentinel;
	private int colCount = 0;
	public HashTable() {
		this(INITIAL_CAP);
	}
	
	public HashTable(int initialCapacity) {
		/* TODO: IMPLEMENT THIS METHOD */
		size = initialCapacity;
		this.table = (HashNode<K,V>[])new HashNode[initialCapacity];
		
	}
	
	@Override
	public void insert(K key, V value) {
		/* TODO: IMPLEMENT THIS METHOD */
		int index = hash(key);
		if(table[index]==null) {
			table[index]=new HashNode<K,V>(key,value);
		}
		else {
			
			while(table[index]!=null && !table[index].getKey().equals(key)) {
				//index+=1; //Linear Probing
				index = Math.abs((hash(key)+index*index)%table.length);//Quadratic Probing
				//index = hash(index);//Double Hashing
				colCount ++;
				if(index==size)
					index=0;
			}
			if(table[index]==null) {
				table[index]=new HashNode<K,V>(key,value);
			}
		}
		table[index].setValue(value);
	}

	@Override
	public V retrieve(K key) {
		/* TODO: IMPLEMENT THIS METHOD */
		int index = hash(key);
		int initialindex = index;
		while(table[index]!=null||table[index]!=sentinel) {
			if(table[index].getKey().equals(key))
				return table[index].getValue();
			index+=1;
			if(index==size)
				index=0;
		}
		return null;
	}

	@Override
	public boolean contains(K key) {
		/* TODO: IMPLEMENT THIS METHOD */
		if(retrieve(key)!=null)
			return true;
		return false;
	}

	@Override
	public void remove(K key) {
		/* TODO: IMPLEMENT THIS METHOD */
		for(int i=0;i<table.length;i++) {
			if(table[i]!=null&&table[i].getKey().equals(key))
				table[i].setValue(sentinel);}
	}
	
	//Helper Functions
	private int hash(K key){
		return Math.abs(key.hashCode())%table.length;
	}
	private int hash(Integer key){
		return Math.abs(key.hashCode())%table.length;
	}
	
	public int getColCount() {
		return colCount;
	}
	
	
}
