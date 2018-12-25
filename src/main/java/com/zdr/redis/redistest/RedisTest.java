package com.zdr.redis.redistest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("121.196.218.189",6379);
//	2	System.out.println("连接成功");
//		System.out.println("服务正在运行 "+jedis.ping());
		
		
		 
//		        //创建jedis对象
//		        Jedis jedis = new Jedis("121.196.218.189", 6379);
//		        //调用jedis对象的方法，方法名称和redis 的命令一致
//		        jedis.set("name", "Zheng");
//		        System.out.print("获取name值：");
//		        String string = jedis.get("name");
//		        System.out.println(string);
//		        jedis.close();

		
//	      JedisPool jedisPool = new JedisPool("121.196.218.189",6379); 
//	      Jedis jedis = null; 
//	       try { 
//	           jedis = jedisPool.getResource(); 
//	           jedis.set("surname", "Zheng"); 
//	           jedis.set("firstname", "Haishu"); 
//	           System.out.println("surname: " +jedis.get("surname")); 
//	           System.out.println("firstname: " +jedis.get("firstname")); 
//	       } catch (Exception e) { 
//	           e.printStackTrace(); 
//	       } finally { 
//	           if (jedis != null) 
//	                jedis.close(); 
//	       } 
//	       jedisPool.destroy();
//		
//		
//	        jedis.set("name", "Usher");
//	       
//			System.out.println("添加数据:" + jedis.get("name") + "\n");
//			// 拼接数据
//			jedis.append("name", "\040is very cool!");
//	 
//			System.out.println("拼接数据:" + jedis.get("name") + "\n");
//
//			// 设置多个数据
//			jedis.mset("name", "Usher", "age", "24", "sex", "male");
//	 
//			// 某个数据+
//			jedis.incrBy("age", 10);
//	 
//			System.out.println("姓名:" + jedis.get("name") + "\040年龄:"
//					+ jedis.get("age") + "\040性别:" + jedis.get("sex") + "\n");

	       
			Map<String, String> map = new HashMap<String, String>();
			// 添加数据
			map.put("name", "Usher");
			map.put("age", "24");
			map.put("sex", "male");
			// 添加到redis中
			jedis.hmset("Information", map);

			System.out.println("添加Map数据:"
					+ jedis.hmget("Information", "name", "age", "sex") + "\n");

			// 删除数据
			jedis.hdel("Information", "name");
	 
			System.out.println("删除Map数据:" + jedis.hmget("Information", "name")
					+ "\n");

			// 返回key=Information的值的个数
			System.out.println("返回key=Information的值的个数:"
					+ jedis.hlen("Information") + "\n");
			// 判断是否存在key=Information的对象
			System.out.println("判断是否存在key=Information的对象:"
					+ jedis.exists("Information") + "\n");
			// 返回map对象中的所有key值
			System.out.println("返回map对象中的所有key值:" + jedis.hkeys("Information")
					+ "\n");
			// 返回map对象中的所有value值
			System.out.println("返回map对象中的所有value值:" + jedis.hvals("Information")
					+ "\n");
			// 循环迭代key
			Iterator<String> it = jedis.hkeys("Information").iterator();
			// 判断是否有元素存在
			while (it.hasNext()) {
				// 获取元素key
				String key = it.next();
				System.out.println("返回key为" + key + "的值:"
						+ jedis.hmget("Information", key) + "\n");
			}
			
	       
	}
	
	@Test
	public void ListTest() {
				Jedis jedis = new Jedis("121.196.218.189",6379);
				// 先清空List
				jedis.del("Information");
				// 查看List是否清空
				System.out.println("Lis[清空后]t:" + jedis.lrange("Information", 0, -1)
						+ "\n");

				// 添加数据
				jedis.lpush("Information", "Usher");
				jedis.lpush("Information", "sex");
				jedis.lpush("Information", "age");
				// 查看List元素
				System.out.println("List[添加后]:" + jedis.lrange("Information", 0, -1)
						+ "\n");
				
				// 清空List
				jedis.del("Information");
				// 添加数据
				jedis.lpush("Information", "Elliot");
				jedis.lpush("Information", "sex");
				jedis.lpush("Information", "age");
				// 查看List元素
				System.out.println("List[清空+添加]:" + jedis.lrange("Information", 0, -1)
						+ "\n");


	}
	
	@Test
	public void setTest(){
				Jedis jedis = new Jedis("121.196.218.189",6379);
				// 添加元素
				jedis.sadd("user", "用户");
				jedis.sadd("user", "性别");
				jedis.sadd("user", "年龄");
				System.out.println("Key的value值[删除前]:" + jedis.smembers("user") + "\n");
				// 删除元素
				jedis.srem("user", "age");
				// 所有元素的value()
				System.out.println("Key的value值[删除后]:" + jedis.smembers("user") + "\n");
				// 判断值是否存在
				System.out.println("判断值是否存在:" + jedis.sismember("user", "age") + "\n");
				// 返回集合元素的个数
				System.out.println("返回集合元素的个数:" + jedis.scard("user") + "\n");
				// 返回随机元素
				System.out.println("返回随机元素:" + jedis.srandmember("user") + "\n");
		
		
	}
	
	
			/**
			 * jedis 排序
			 */
			@Test
			public void sortTest() {
				Jedis jedis = new Jedis("121.196.218.189",6379);
				// 添加元素
				jedis.rpush("sort", "1");
				jedis.lpush("sort", "2");
				jedis.lpush("sort", "3");
				jedis.lpush("sort", "4");
				System.out.println("元素:" + jedis.lrange("sort", 0, -1) + "\n");
				// 排序
				System.out.println("排序:" + jedis.sort("sort") + "\n");
				// jedis.sort("sort");
				// 再输出一次
				// System.out.println("元素:" + jedis.lrange("sort", 0, -1) + "\n");
			}

	
	
			/**
			 * 测试中文
			 */
			@Test
			public void chineseTest() {
				
				RedisUtil.getJedis().set("name", "中文测试");
				System.out.println(RedisUtil.getJedis().get("name"));
			}

	
	
	

}
