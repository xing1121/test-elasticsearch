package com.sf.elasticsearch.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sf.elasticsearch.entity.Employee;

public class Demo00 {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static void main(String[] args) {
		RestClient restClient = null;

		try {
			//创建客户端
			restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
			
			//构建参数
			Employee employee = new Employee("John", "Smith", 25, "I love to go rock climbing", new String[]{"sports", "music" });
			String employeeStr = objectMapper.writeValueAsString(employee);
			
			//params是追加在uri请求地址尾部的请求参数
			Map<String, String> params = Collections.singletonMap("pretty", "true");
			
			//entity是在请求体中的请求参数
			HttpEntity entity = new NStringEntity(employeeStr, ContentType.APPLICATION_JSON);
			
			//执行请求获取响应
			Response response = restClient.performRequest("PUT", "/megacorp/employee/1", params, entity );
			
			//输出响应相关
			System.out.println("响应：" + response);
			System.out.println("响应实体：" + response.getEntity());
			InputStream is = response.getEntity().getContent();

			int length = 0;
			byte[] buffer = new byte[1024];
			while ((length = is.read(buffer)) != -1) {
				is.read(buffer, 0, length);
			}
			String string = new String(buffer);
			System.out.println("响应内容转为字符串：" + string);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				restClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
