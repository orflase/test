package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

/** 
 * @author  李林林 
 * @date 2017年8月16日 下午2:23:40 
 * @since
 */
public class Stream1 {

	/**
	 * 流Stream是什么？
	 * 	是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。
	 * 	集合讲的是数据，流讲的是计算
	 * 注意 
	 * 	Stream不会自己存储数据。
	 * 	Stream不会改变原对象，他们会返回一个新的Stream。
	 * 	Stream操作是延迟的，他们会等到需要的结果时才执行。
	 */
	
	/**
	 * Stream的3个操作步骤
	 *	创建Stream，一个数据源 （如：集合、数组），获取一个流
	 *	中间操作，对数据源进行处理
	 *	终止操作，执行中间操作链，并产生结果
	 */

	//创建Stream
	@Test
	public void test(){
		//1、通过Collection系列提供的stream()（串行） 或parallelStream()（并行）获取
		List<String> list = new ArrayList<>();
		Stream<String> stream1 = list.stream();//串行流
		Stream<String> stream2 = list.parallelStream();//并行流
		
		//2、通过Arrays中的静态方法stream() 获取数据流
		User[] u = new User[2];
		Stream<User> stream3 = Arrays.stream(u);
		
		//3、通过Stream；类中的静态方法of()
		Stream<String> stream4 = Stream.of("11","2");
		
		//4、创建无限流
		//迭代
		Stream<Integer> stream5 = Stream.iterate(0, (x) -> x+2);
		stream5.limit(10)
		.forEach(System.out::println);
		//生成
		Stream.generate(() -> Math.random()).limit(10)
		.forEach(System.out::println);
		
		
		
	}
}
