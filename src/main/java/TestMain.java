import java.util.*;
import java.util.concurrent.*;

public class TestMain {
	public static void main(String[] arg) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		System.out.print(list);

		LinkedList<String> queue = new LinkedList<String>();
		queue.add("3");
		queue.add("6");
		queue.add("7");
		queue.add("8");
		queue.add(1, "5");
		System.out.println(queue);
		queue.add(1, "4");
		System.out.println(queue);

		queue.offer("9");
		queue.offerLast("10");

		System.out.println(queue);
		System.out.println("-----------");
//		System.out.println(queue.peek());
//		queue.pop();
		queue.push("push");
		System.out.println(queue);
		queue.offerFirst("offerFirst");
		System.out.println(queue);
		queue.addFirst("addFIrst");
		System.out.println(queue);
		System.out.println("-----------");
		queue.add("add");
		System.out.println(queue);
		queue.offerLast("offerLast");
		System.out.println(queue);
		queue.addLast("addLast");
		System.out.println(queue);
		queue.offer("offer");
		System.out.println(queue);
		System.out.println("-----------");
		queue.poll();
		System.out.println(queue + "---poll");
		queue.pollFirst();
		System.out.println(queue + "---pollFirst");
		queue.removeFirst();
		System.out.println(queue + "---removefirst");
		queue.pop();
		System.out.println(queue + "---pop");
		queue.remove();
		System.out.println(queue + "---remove");
		System.out.println("-----------");
		queue.removeLast();
		System.out.println(queue + "---removeLast");
		queue.pollLast();
		System.out.println(queue + "---pollLast");

		Iterator<String> it = queue.iterator();

//		while(it.hasNext()){
//			String s = it.next();
//			queue.remove(s);
//		}
		System.out.println(queue + "---00");

//		String abc = queue.element();
//		abc = queue.remove();
//		System.out.println(abc);
//		abc = queue.element();
//		System.out.println(abc);

//		queue.remove(3);
//		System.out.println(queue);

		Set<String> set = new HashSet<String>();
		TreeSet<Person> ps = new TreeSet<Person>();
		ps.add(new Person("15", 15));
		ps.add(new Person("19", 19));
		ps.add(new Person("17", 17));
		ps.add(new Person("22", 22));
		ps.add(new Person("16", 16));
		ps.add(new Person("60", 60));
		ps.add(new Person("100", 100));
		ps.add(new Person("72", 72));
		ps.add(new Person("40", 40));
		ps.add(new Person("28", 28));
		ps.add(new Person("43", 43));
		ps.add(new Person("55", 55));
		System.out.println(ps);

		Person p = ps.ceiling(new Person("", 22));
		System.out.println("ceiling(22)=" + p);
		p = ps.ceiling(new Person("", 20));
		System.out.println("ceiling(20)=" + p);
		p = ps.floor(new Person("", 17));
		System.out.println("floor(17)=" + p);
		p = ps.floor(new Person("", 18));
		System.out.println("floor(18)=" + p);
		p = ps.higher(new Person("", 55));
		System.out.println("higher(55)=" + p);
		p = ps.lower(new Person("", 72));
		System.out.println("lower(72)=" + p);

		p = ps.first();
		System.out.println("first=" + p);
		p = ps.last();
		System.out.println("last=" + p);

		SortedSet<Person> t1 = (TreeSet<Person>)ps.headSet(new Person("22", 20));
		System.out.println("headSet(22)=" + t1);


		SortedSet<Person> t2 = (TreeSet<Person>)ps.tailSet(new Person("22", 20));
		System.out.println("tailSet=(22)=" + t2);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("a", "111");
		map.put("b", "222");
		map.put("c", "333");
		map.put("d", "444");
		map.put("e", "555");
		map.put("f", "666");

		Set<String> keys = map.keySet();
		Iterator<String> it2 = keys.iterator();
		while(it2.hasNext()){
			String value = map.get(it2.next());
			System.out.println(value);
		}

		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		tree.put(7, "a");
		tree.put(5, "a");
		tree.put(2, "a");
		tree.put(9, "a");
		tree.put(6, "a");
		tree.put(4, "a");
		tree.put(3, "a");
		tree.put(1, "a");
		tree.put(12, "a");
		tree.put(-1, "a");
		tree.put(-2, "a");
		tree.put(20, "a");

		System.out.println(tree);
		System.out.println("first=" + tree.firstKey());
		System.out.println("last=" + tree.lastKey());
		System.out.println("celling(11)=" + tree.ceilingEntry(11).getKey());
		System.out.println("celling(12)=" + tree.ceilingEntry(12).getKey());
		System.out.println("floor(13)=" + tree.floorEntry(13).getKey());
		System.out.println("floor(12)=" + tree.floorEntry(12).getKey());
		System.out.println("lowerKey(13)=" + tree.lowerKey(13));
		System.out.println("lowerKey(12)=" + tree.lowerKey(12));
		System.out.println("higherKey(11)=" + tree.higherKey(11));
		System.out.println("higherKey(12)=" + tree.higherKey(12));

		System.out.println("---------");
		tree.pollFirstEntry();
		System.out.println(tree);
		tree.pollLastEntry();
		System.out.println(tree);
		System.out.println();

		ConcurrentHashMap<String, String> map2 = new ConcurrentHashMap<String, String>();

		Map<Integer,String> linkmap = new LinkedHashMap<Integer,String>();
		linkmap.put(6, "apple");
		linkmap.put(3, "banana");
		linkmap.put(2,"pear");
		linkmap.put(5,"pear");
		linkmap.put(4,"pear");
		linkmap.put(7,"pear");
		linkmap.remove(5);
		linkmap.put(2,"2f2");


		for (Iterator ti =  linkmap.keySet().iterator();ti.hasNext();)
		{
			Object key = ti.next();
			System.out.println( key+"="+ linkmap.get(key));
		}


		System.out.println("---------------ConcurrentLinkedQueue----------");

		ConcurrentLinkedQueue<String> queue1 = new ConcurrentLinkedQueue<>();
		String s = queue1.peek();
		if(null == s){
			System.out.println("peek=null");
		}
		s = queue1.poll();
		if(null == s){
			System.out.println("poll=null");
		}
		queue1.add("20");
		queue1.offer("30");
		queue1.offer("40");
		queue1.offer("50");
		System.out.println(queue1);

		System.out.println("peek :" + queue1.peek());
		System.out.println("after peek " + queue1);


		System.out.println("poll :" + queue1.poll());
		System.out.println("after poll " + queue1);

		System.out.println("---------------ConcurrentLinkedDeque----------");

		ConcurrentLinkedDeque<String> dq = new ConcurrentLinkedDeque<>();

		dq.add("5");
		dq.add("6");
		dq.offerLast("7");
		dq.addLast("8");
		dq.offer("9");
		//-----
		dq.addFirst("4");
		dq.offerFirst("3");
		dq.push("2");
		System.out.println(dq);
		//------
		System.out.println("----------从队头取");
		System.out.println(dq.peek());
		System.out.println(dq.peekFirst());
		System.out.println(dq.getFirst());
		System.out.println("----------从队尾取");
		System.out.println(dq.peekLast());
		System.out.println(dq.getLast());

		//------
		System.out.println("----------	从对头删");
		dq.poll();
		dq.pop();
		dq.pollFirst();
		dq.removeFirst();
		dq.remove();
		System.out.println(dq);
		//----
		System.out.println("----------从队尾删");
		dq.pollLast();
		dq.removeLast();
		System.out.println(dq);

		//---------------------
		System.out.println("--------------LinkedBlockingQueue------------");
		LinkedBlockingQueue<String> strs = new LinkedBlockingQueue<>(3);

//		try {
//			System.out.println(strs.take());
//		} catch(InterruptedException e) {
//			e.printStackTrace();
//		}
		try {
			System.out.println(strs.poll(5, TimeUnit.MILLISECONDS));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		strs.add("1");
		strs.add("2");
		strs.add("3");
		System.out.println(strs.offer("4"));
		try {
			System.out.println(strs.offer("4", 5, TimeUnit.MILLISECONDS));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
//		try {
//			strs.put("4");
//		} catch(InterruptedException e) {
//			e.printStackTrace();
//		}

		System.out.println(strs.remove());
		System.out.println(strs.poll());
		try {
			System.out.println(strs.take());
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		//---------------------
		System.out.println("--------------LinkedBlockingDeque------------");
		LinkedBlockingDeque<String> lbd = new LinkedBlockingDeque<>(6);
		lbd.add("+++");
		lbd.add("5");
		lbd.add("6");
		lbd.offer("7");
		try {
			lbd.put("8");
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		//队尾
		lbd.push("4");
		System.out.println(lbd);
		lbd.pop();
		System.out.println(lbd);
		lbd.remove();
		lbd.poll();
		try {
			lbd.take();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(lbd);
		int MAX_LEN = 10000000;
		//---------array
		String[] arr = new String[MAX_LEN];
		for(int i = 0; i < MAX_LEN; i++){
			arr[i] = String.valueOf(i * 2);
		}
		int len = arr.length;
		long starttime, endtime;
		starttime = System.currentTimeMillis();
		for(int i = 0 ; i < len; i++){
			if(arr[i] == null){
			}
		}
		endtime = System.currentTimeMillis();
		System.out.println("for:" + (endtime - starttime));
		starttime = System.currentTimeMillis();
		for(String string : arr) {
			if(string == null){
			}
		}
		endtime = System.currentTimeMillis();
		System.out.println("foreach:" + (endtime - starttime));

		starttime = System.currentTimeMillis();
		Arrays.stream(arr).forEach(string -> {if(string == null){}});
		endtime = System.currentTimeMillis();
		System.out.println("Arrays.stream.forEach:" + (endtime - starttime));

		//---------list
		long sum = 0;
		List<Integer> listA = new ArrayList<>(MAX_LEN);
		for(int i = 0; i < MAX_LEN; i++){
			listA.add(i);
		}

		starttime = System.currentTimeMillis();
		for(int i = 0 ; i < len; i++){
			sum += listA.get(i);
		}
		endtime = System.currentTimeMillis();
		System.out.println("List for:" + (endtime - starttime));

		sum = 0;
		starttime = System.currentTimeMillis();
		for(int i : listA){
			sum += i;
		}
		endtime = System.currentTimeMillis();
		System.out.println("List foreach:" + (endtime - starttime));

		//迭代器
		sum = 0;
		starttime = System.currentTimeMillis();
		Iterator<Integer> itInt = listA.iterator();
		while(itInt.hasNext()){
			sum += itInt.next();
		}
		endtime = System.currentTimeMillis();
		System.out.println("List iterator:" + (endtime - starttime));

		long sum2 = 0L;
		listA.forEach(a ->{
//			sum2 = sum2 + a.longValue();
		});

		listA.parallelStream().forEach(v -> {
			System.out.println(v);
		});
	}
}
