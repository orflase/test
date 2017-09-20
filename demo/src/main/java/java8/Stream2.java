package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.Test;

/** 
 * @author  李林林 
 * @date 2017年8月16日 下午5:47:32 
 * @since
 */
public class Stream2 {

	/**
	 * 筛选和切片
	 * 
	 * filter(predicate)-接收lambda，从流中排除某些元素
	 * limit(n)-截断流，使其元素不超过给定数量
	 * skip(n)-跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流，与limit(n)互补
	 * distinct-筛选，通过流所生成元素的hashcode()和equals()去重复元素
	 * 
	 */


	/**
	 * 内部迭代：迭代操作由stream api完成
	 */
	private List<User> list = Arrays.asList(
			new User("zhang","男",18),
			new User("zhao","女",19),
			new User("wang","男",18),
			new User("li","女",16)
			);

	@Test
	public void test1(){
		//中间操作：不会执行任何操作
		Stream<User> stream = list.stream()
				.filter((x) -> x.getAge()>16)
				.limit(2);
		//终止操作：一次性执行全部内容，即惰性求值(延迟加载)
		//多个中间操作可以连接起来性格一条流水线，除非流水线上触发器终止操作，否则中间操作不会执行任何的处理，而是在终止操作时一次性全部处理，成为惰性求值 
		stream.forEach(System.out::println);
	}

	//外部迭代
	@Test
	public void test2(){
		Iterator<User> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	/**
	 * skip
	 * @author: 李林林
	 * @date:2017年9月15日 下午3:35:04 void
	 */
	@Test
	public void test3(){
		list.stream().skip(1).forEach(System.out::println);
	}

	/**
	 * 
	 * 映射
	 * map-接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每一个元素上，并将其映射成一个新的元素
	 * flatMap-接收一个函数作为参数，将流中的每个值都换成一个流，然后把流连接成一个流
	 * 
	 */

	@Test
	public void test4(){
		list.stream().map(User::getName
				).forEach(System.out::println);

		//flatMap
		//
		list.stream().map(Stream2::filterCharacter).forEach((x) -> x.forEach(System.out::println));
		//
		System.out.println("---");
		list.stream().flatMap(Stream2::filterCharacter).forEach(System.out::println);
	}

	public static Stream<Character> filterCharacter(User str){
		List<Character> list  = new ArrayList<Character>();
		for (Character character : str.getName().toCharArray()) {
			//list.add(character);
			System.out.println(character);
		}
		return list.stream();
	}
	
	
	/**
	 * 
	 * 排序
	 * 
	 * sorted()-自然排序 Comparable
	 * sorted(Comparator com)-定制排序
	 * 
	 */
	
	@Test
	public void test5(){
		List<String> list = Arrays.asList("aa","bb","cc","dd");
		list.stream().sorted().forEach(System.out::println);
		//
		list.stream().sorted((x,y) -> {
			if(x.equals(y)){
				return 1;
			}else{
				return -1;
			}
		} ).forEach(System.out::println);
	}
	
	
	
	
}
