import java.util.List;

public class GenericType {

	
	public static void main(String[] args){
		Box<Integer> integerBox = new Box<Integer>();
		Box<String> stringBox = new Box<String>();
		Box<List> listBox = new Box<List>();
		
		
		System.out.println(integerBox);
		
	}
}
class Box<T> {
    // T stands for "Type"
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}