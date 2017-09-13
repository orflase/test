package java8;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.Test;

import cn.lill.demo.App;

/** 
 * 方法引用
 * @author  李林林 
 * @date 2017年8月15日 下午3:47:12 
 * @since
 */
public class TestMethodRef {

	/**
	 * 方法引用：若lambda体中的内容有方法已经实现，我们可以使用“方法引用”
	 * Lambda的另一种表现形式
	 * 
	 * 
	 * 主要三种
	 * 
	 * 注意
	 * 	1、lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的参数列表和返回类型保持一致
	 *  2、若lambda参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法参数时，可以使用ClassName::method
	 * 
	 * 对象::实例方法名
	 * 
	 * 
	 * 类::静态方法名
	 * 
	 * 
	 * 类::实例方法名
	 * 
	 * 
	 * 
	 * 
	 * 构造器引用
	 * 
	 * 格式
	 * 	ClassName::new
	 * 
	 * 需要调用的构造器的参数列表要与函数接口中的抽象方法参数列表保持一致
	 * 
	 * 
	 * 数组引用
	 * 
	 * 	Type::new
	 */


	/**
	 * 对象::实例方法名
	 * @author: 李林林
	 * @date:2017年8月15日 下午7:41:52 void
	 */
	@Test
	public void test1(){
		Consumer<String> con = (x) -> System.out.println(x);
		Consumer<String> con2 =  System.out::println;
		con.accept("hello one");
		con2.accept("hello two");

	}
	@Test
	public void test2(){
		User user = new User();
		user.setName("sss");
		Supplier<String> sup = () -> user.getName();
		Supplier<String> sup1 = user :: getName;
		System.out.println(sup.get());
		System.out.println(sup1.get());

	}
	/**
	 * 类::静态方法名
	 * @author: 李林林
	 * @date:2017年8月15日 下午7:43:29
	 * @return voit
	 */
	@Test
	public void test3(){
		Comparator<Integer> com = Integer::compare;
		System.out.println(com.compare(1, 2));
	}

	/**
	 *  类::实例方法名
	 * @author: 李林林
	 * @date:2017年8月15日 下午7:48:24 void
	 */
	@Test
	public void test4(){

		BiPredicate<String, String> bp = String::equals;
		System.out.println(bp.test("1", "2"));	

	}
	
	@Test
	public void test5(){
		Supplier<User> sup = User::new;
		User u = sup.get();
		System.out.println(u.toString());
	}
}
