package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

/** 
 * @author  李林林 
 * @date 2017年8月16日 下午5:47:32 
 * @since
 */
public class Steam1 {

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
		//终止操作：一次性执行全部内容，即惰性求值
		stream.forEach(System.out::println);
	}
}
