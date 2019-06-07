public class Person implements Comparable{
	public String name;
	public int age;

	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}


	@Override
	public boolean equals(Object o){
		return true;
	}

	public int compareTo(Object o) {
		Person p = (Person)o;
		return -this.age + p.age;
	}

	@Override
	public String toString() {
		return this.name;
	}


}
