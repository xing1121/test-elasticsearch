package com.sf.elasticsearch.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sf.elasticsearch.entity.ESResponse;

public class Demo01 {

	private static TransportClient client = null;
	// ObjectMapper对象
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 创建客户端
	 */
	@Before
	public void getClient() {
		if (client == null) {
			try {
				Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
						.put("client.transport.sniff", true).build();
				client = TransportClient.builder().settings(settings).build()
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
				System.out.println("开启客户端：" + client);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 *	向库添加索引/更新索引
	 */
	@Test
	public void addIndex() {
		try {
			// http://localhost:9200/twitter/tweet/1
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("user", "kimchy");
			json.put("postDate", new Date());
			json.put("message", "trying  out  Elasticsearch");
			String jsonStr = objectMapper.writeValueAsString(json);
			// 创建了一条索引
			IndexResponse indexResponse = client.prepareIndex("twitter", "tweet", "1").setSource(jsonStr).get();
			// 封装自定义响应对象
			ESResponse esResponse = new ESResponse(indexResponse);
			System.out.println(esResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *	获取索引 
	 */
	@Test
	public void getIndex(){
		//默认为setOperationThreaded(true)，以多线程形式执行
		//设置为false只以单线程执行
		GetResponse  response  =  client.prepareGet("twitter",  "tweet",  "1").setOperationThreaded(false).get();
		String string = response.getSourceAsString();
		System.out.println("Index:"+response.getIndex()+",Type:"+response.getType()+",Id:"+response.getId()+",Version:"+response.getVersion());
		System.out.println(string);
	}

	/**
	 *	从库删除索引 
	 */
	@Test
	public void deleteIndex(){
		DeleteResponse  deleteResponse  =  client.prepareDelete("twitter",  "tweet",  "1").get();
		System.out.println(new ESResponse(deleteResponse));
	}
	
	/**
	 *	更新索引 
	 */
	@Test
	public void updateIndex(){
		try {
			// http://localhost:9200/twitter/tweet/1
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("user", "kimchy");
			json.put("postDate", new Date());
			json.put("message", "哈哈哈哈");
			String jsonStr = objectMapper.writeValueAsString(json);
			// 创建了一条索引
			IndexResponse indexResponse = client.prepareIndex("twitter", "tweet", "1").setSource(jsonStr).get();
			// 封装自定义响应对象
			ESResponse esResponse = new ESResponse(indexResponse);
			System.out.println(esResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭客户端
	 */
	@After
	public void closeClient() {
		if (client != null) {
			client.close();
			System.out.println("客户端关闭");
		}
	}
}
