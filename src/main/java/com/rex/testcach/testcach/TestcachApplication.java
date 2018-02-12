package com.rex.testcach.testcach;

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
			OperationFuture fo = mcc.set("runoob", 900, "Free Education");

			// 查看存储状态
			System.out.println("set status:" + fo.get());

			// 输出值
			System.out.println("runoob value in cache - " + mcc.get("runoob"));


			// 关闭连接
			mcc.shutdown();

		}catch(Exception ex){
			System.out.println( ex.getMessage() );
		}

	}
}
