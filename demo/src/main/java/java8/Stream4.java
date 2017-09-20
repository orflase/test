package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

/** 
 * @author  李林林 
 * @date 2017年9月18日 下午6:47:12 
 * @since
 */
public class Stream4 {



	private static List<User> list = Arrays.asList(
			new User("zhang","男",18),
			new User("zhao","女",19),
			new User("wang","男",18),
			new User("li","女",16)
			);


	/**
	 * 
	 * 归约，可以将流中的值反复结合起来，得到一个值。
	 */

	@Test
	public void test(){

		List<Integer> list = Arrays.asList(1,2,3,4,5);
		int sum = list.stream().reduce(2, (x,y) -> x+y);
		System.out.println(sum);
		Optional<Integer> sumw = list.stream().reduce(Integer::sum);
		System.out.println(sumw.get());
	}


	/**
	 * 
	 * 收集collect，将流转换为其他形式接受用一个collector接口实现，用于给stream中的元素做汇总
	 * 
	 */
	@Test
	public void test2(){
		
		//转list
		List<String> list1 =list.stream().map(User::getName).collect(Collectors.toList()) ;
		list1.forEach(System.out::println);

		//转set
		HashSet<String> set = list.stream().map(User::getName).collect(Collectors.toCollection(HashSet::new));
		set.forEach(System.out::println);

		//总数
		Long count = list.stream().collect(Collectors.counting());
		System.out.println(count);
		//平均年龄
		double ss = list.stream().collect(Collectors.averagingInt(User::getAge));
		System.out.println(ss); ;
		//总年龄
		 int sss = list.stream().collect(Collectors.summingInt(User::getAge));
		System.out.println(sss); ;
		//最大值
	}


	
	
	
	@Test
	public void test5555(){
		List<List<String>> flist = new ArrayList<List<String>>();
		List<String> clist = new ArrayList<String>();
		clist.add("qqqq");
		flist.add(clist);
		clist.add("wwww");
		System.out.println(flist.toString());
	}












}
