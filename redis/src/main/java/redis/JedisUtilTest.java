package redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtilTest {

	JedisPool 	pool;
	Jedis jedis;
	
	@Before
	public void setUp(){
		
		pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
		
		jedis = pool.getResource();
		
		jedis.auth("123456");
		
		jedis.select(4);
	}
	
	@Test
	public void testGet(){
		System.out.println(jedis.get("m"));
	}
	
	@Test
	public void testBasiString(){
		//-----添加数据----------
//		jedis.set("aaa", "111");
//		System.out.println(jedis.get("aaa"));
		
		//-----修改数据-----------
        //1、在原来基础上修改
//		jedis.append("aaa", "123");
//		System.out.println(jedis.get("aaa"));
		
		//2、直接覆盖原来的数据
//		jedis.set("aaa", "覆盖");
//		System.out.println(jedis.get("aaa"));
		
		//删除key对应的记录
//		jedis.del("aaa");
//		System.out.println(jedis.get("aaa"));
		
		 /**
         * mset相当于
         * jedis.set("name","minxr");
         * jedis.set("jarorwar","闵晓荣");
         */
		jedis.mset("aaa","111","bbb","222","ccc","333");
		System.out.println(jedis.mget("aaa","bbb","ccc"));
	}
	
	@Test
	public void testMap(){
		/*Map<String,String> user = new HashMap<String, String>();
		user.put("name","minxr");
        user.put("pwd","password");
        jedis.hmset("user", user);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget("user", "name","pwd");
        System.out.println(rsmap);*/
        
		//删除map中的某个键值
//		jedis.hdel("user", "pwd");
//      System.out.println(jedis.hmget("user", "name"));
//		System.out.println(jedis.hlen("user"));
//		System.out.println(jedis.exists("name"));
//		System.out.println(jedis.hkeys("user"));
//		System.out.println(jedis.hvals("user"));
		
		Iterator<String> iter = jedis.hkeys("user").iterator();
		while(iter.hasNext()){
			String key = iter.next();
			System.out.println(key+":"+jedis.hmget("user", key));
		}
	}
	
	@Test
	public void testList(){
		 //开始前，先移除所有的内容
//      jedis.del("java framework");
//      System.out.println(jedis.lrange("java framework",0,-1));
//		jedis.lpush("java", "111");
//		jedis.lpush("java", "222");
//		jedis.lpush("java", "333");
		//再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
		System.out.println(jedis.lrange("java", 0, -1));
		
	}
	
	@Test
	public void testSet(){
//		jedis.sadd("sname", "111");
//		jedis.sadd("sname", "222");
//		jedis.sadd("sname", "333");
//		jedis.sadd("nname", "333");
		
//		jedis.srem("nname", "333");
		
		System.out.println(jedis.smembers("sname"));
		System.out.println(jedis.sismember("sname", "111"));
		System.out.println(jedis.srandmember("sname"));
		System.out.println(jedis.scard("sname"));
	}
	
	@Test
	public void test() throws InterruptedException{
//		jedis.mset("sose", "sanme", "name", "jarorwar", "foo", "sanmdde","sname", "java framework", "user", "braand");
		jedis.mset("ever","time");
//		System.out.println(jedis.keys("*"));
//		System.out.println(jedis.keys("*name"));//返回[name, sname]
//		System.out.println(jedis.del("foo"));
		
//		jedis.setex("ever", 120, "min");
//		Thread.sleep(5000);
		System.out.println(jedis.ttl("ever"));
	}
}

