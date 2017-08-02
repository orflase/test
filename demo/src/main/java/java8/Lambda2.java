package java8;

import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

import com.rabbitmq.client.AMQP.Basic.Return;

/** 
 * @author  李林林 
 * @date 2017年7月31日 下午5:39:11 
 * @since
 */

/**
 * java8中引入了新的操作符"->"，成为箭头操作符或者lambda操作符
 * 箭头操作符将lambda拆分为两部分，
 * 
 * lambda表达式的参数列表的数据类型可以省略不写，因为jvm编辑器可以通过上下文判断
 * 
 * lambda表达式需要“函数式接口”的支持。
 * 函数式接口，这个接口中只有一个抽象方法的接口称为函数式接口，可以使用注解@FunctionalInterface修饰，可以检查是否是函数式接口  
 * 
 * 左侧：lambda表达式的参数列表
 * 右侧：lambda表达式中的所需要执行的的功能
 * 
 * 
 *  语法格式1 ：无参数，无返回值
 *  () -> System.out.println("hello")
 *  
 *  语法格式2：一个参数（小括号可以省略不写，习惯上加上小括号），无返回值
 *  (x) -> System.out.println(x)
 *  x -> System.out.println(x)
 *  
 *  语法格式3：有多个参数，并且lambda有多条语句，则lambda语句必须用大括号括起来并有return返回（若有一条语句则可以省略大括号和return），有返回值
 * 
 *  
 *  
 * @author 李林林
 * @date 2017年8月1日 上午10:12:38
 */
public class Lambda2 {

	@Test
	public void test1(){
		Runnable r = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("hello java");
			}
		};
		r.run();
		System.out.println("-------------------------------");
		//实现Runnable接口（无参，无返回）
		Runnable r1 = () -> System.out.println("hello lambda");
		r1.run();
	}

	@Test
	public void test2(){
		Consumer<String> c = (x) -> System.out.print(x);
		c.accept("hello");
	}

	@Test
	public void test3(){
		Comparator<Integer> comparator = (x,y) ->{
			System.out.println("hello");
			return Integer.compare(x, y);
		};
		System.out.println(comparator.compare(23, 22));
		System.out.println("-------------------------------");
		Comparator<Integer> comparator2 = (x,y) -> Integer.compare(x, y);
		System.out.println(comparator2.compare(23, 22));
	}

	/**
	 * 练习
	 * @author: 李林林
	 * @date:2017年8月1日 上午11:37:45 void
	 */
	@Test
	public void test4(){
		Fun<Integer> f= (x,y) -> {
			int z =x*y;
			return z;
		};
		System.out.println(f.test(1,3));
	}
}
