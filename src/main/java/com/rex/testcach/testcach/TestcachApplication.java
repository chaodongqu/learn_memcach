package com.rex.testcach.testcach;

import net.spy.memcached.CASValue;
import net.spy.memcached.internal.OperationFuture;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.spy.memcached.MemcachedClient;

import java.net.InetSocketAddress;

@SpringBootApplication
public class TestcachApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcachApplication.class, args);

		//
		try{
			// 本地连接 Memcached 服务
			MemcachedClient mcc = new MemcachedClient(
					new InetSocketAddress("127.0.0.1", 1023));

			System.out.println("Connection to server sucessful.");

			// 存储数据
			OperationFuture fo = mcc.set("Jeo", 900, "Harvard");
			

			// 查看存储状态
			System.out.println("set status:" + fo.get());

			// 输出值
			System.out.println(" value in cache - " + mcc.get("Jeo"));


			fo = mcc.set("Mickle", 900, "stanford");
			// 输出值
			System.out.println(" value in cache - " + mcc.get("Mickle"));


			// 通过 gets 方法获取 CAS token（令牌）
			CASValue casValue = mcc.gets("Mickle");

			// 输出 CAS token（令牌） 值
			System.out.println("CAS value in cache - " + casValue);



			// 关闭连接
			mcc.shutdown();

		}catch(Exception ex){
			System.out.println( ex.getMessage() );
		}

	}
}
