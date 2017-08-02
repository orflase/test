package java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/** 
 * @author  李林林 
 * @date 2017年8月1日 下午1:39:09 
 * @since
 */
public class Lambda3 {

	/*
	 * java8 四大核心函数式接口
	 * 
	 * Consumer<T> 消费型接口
	 * 		void accept(T t);
	 * 
	 * Supplier<T> 供给型接口
	 * 		T get();		
	 * 
	 * Function<T,R> 函数性接口
	 * 		R apply(T);
	 * 
	 * Predicate<T> 断言型接口
	 * 		boolean test<T>;
	 */
	//Consumer<T> 消费型接口
	@Test
	public void test1(){
		Consumer<String> c = (x) -> System.out.println("hello:"+x+"!");
		c.accept("Java");
	}
	// Supplier<T> 供给型接口
	@Test
	public void test2(){
		Supplier<String> s = () -> "hello,beautiful girl";
		String str = s.get();
		System.out.println(str);
	}
	//Function<T,R> 函数性接口
	@Test
	public void test3(){
		Function<String, Integer> f= (x) ->	x.length();
		Integer len = f.apply("hello");
		System.out.println(len);

	}
	//Predicate<T> 断言型接口
	@Test
	public void test4(){
		Predicate<String> p = (x) -> x.length()>5;
		boolean b = p.test("hello Java");
		System.out.println(b);
	}

}
