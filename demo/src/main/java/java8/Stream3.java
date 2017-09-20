package java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/** 
 * 终止操作
 * @author  李林林 
 * @date 2017年9月18日 下午5:27:08 
 * @since
 */
public class Stream3 {


	/**
	 * 
	 * 查找与匹配
	 * 
	 * allMatch,检查是否匹配所有元素
	 * anyMatch,检查是否至少匹配一个元素
	 * noneMatch,检查是否没有匹配所有元素
	 * findFirst,返回第一个元素
	 * findAny,返回当前流中的任意元素
	 * count,返回流中元素的总数
	 * max,返回流中最大值
	 * min,返回流中最小值
	 */

	private List<User> list = Arrays.asList(
			new User("zhang","男",18),
			new User("zhao","女",19),
			new User("wang","男",18),
			new User("li","女",16)
			);

	@Test
	public void test1(){
		boolean b =	list.stream().noneMatch((e) ->
		e.getName().equals("zhao"));
		System.out.println(list.stream().max( (x,y) -> Integer.compare(x.getAge(), y.getAge()))  );
		
		list.stream().map(User::getAge).min(Integer::compare);
	}

}
