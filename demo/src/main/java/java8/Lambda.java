package java8;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/** 
 * @author  李林林 
 * @date 2017年7月31日 下午4:52:33 
 * @since
 */
public class Lambda {
	List<User> list = Arrays.asList(
			new User("张无忌","男",24),
			new User("谢逊","男",55),
			new User("李逍遥","男",26),
			new User("赵灵儿","女",25)
			);

	public List<User> getUser(List<User> list, UserInreface<User> u){
		List<User> uList = new ArrayList<User>();
		for (User user : list) {
			if(u.test(user)){
				uList.add(user);
			}
		}
		return uList;
	}

	/**
	 * 方法1
	 * @author: 李林林
	 * @date:2017年7月31日 下午5:22:59 void
	 */
	@Test
	public void test1(){
		List<User> u = getUser(list, new UserInreface<User>() {
			@Override
			public boolean test(User t) {
				return t.getAge()>25;
			}
		});
		for (User user : u) {
			System.out.println(user);
		}
	}

	/**
	 *  方法2 lambda
	 * @author: 李林林
	 * @date:2017年7月31日 下午5:23:41 void
	 */
	@Test
	public void test2(){
		List<User> ulist = getUser(list, (user) -> user.getAge() > 25);
		ulist.forEach(System.out::println);
	}
	
	/**
	 * 方法3 Stream api
	 * @author: 李林林
	 * @date:2017年7月31日 下午5:32:11 void
	 */
	@Test
	public void test3(){
		list.stream()
		.filter((user) -> user.getAge() > 25)
		.limit(1)
		.map(User::getName)
		.forEach(System.out::println);
	}
	
}
