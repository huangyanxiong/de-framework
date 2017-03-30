package com.dataeye.hbase.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dataeye.hbase.handle.HBaseHandler;

public class HBaseExample {

	public static void main(String[] args) {
		List<String> columnList = new ArrayList<String>();
		columnList.add("info");
		String tableName = "studd";
		
//		HBaseHandle.createTable(tableName, columnList);
		
		for (int i=0;i<1000000000;i++){
			System.out.println("record " + i + " ... ");
			
			Map<String, Map<byte[], byte[]>> records = new HashMap<String, Map<byte[], byte[]>>();
			
			for (int j=0;j<100;j++){
//				System.out.println(j);
				Map<byte[], byte[]> qualifierValueMap = new HashMap<byte[], byte[]>();
				qualifierValueMap.put("f1".getBytes(), "12345678901234567890111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111".getBytes());
				qualifierValueMap.put("f2".getBytes(), "qwertyuiopasdfghjklzxcvbnmqwertyuiopqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq".getBytes());
				qualifierValueMap.put("f3".getBytes(), "12345678901234567890111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111".getBytes());
				qualifierValueMap.put("f4".getBytes(), "qwertyuiopasdfghjklzxcvbnmqwertyuiopqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq".getBytes());	
				qualifierValueMap.put("f5".getBytes(), "12345678901234567890111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111".getBytes());
				qualifierValueMap.put("f6".getBytes(), "qwertyuiopasdfghjklzxcvbnmqwertyuiopqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq".getBytes());	
				qualifierValueMap.put("f7".getBytes(), "12345678901234567890111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111".getBytes());
				qualifierValueMap.put("f8".getBytes(), "qwertyuiopasdfghjklzxcvbnmqwertyuiopqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq".getBytes());	
				qualifierValueMap.put("f9".getBytes(), "12345678901234567890111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111".getBytes());
				qualifierValueMap.put("f10".getBytes(), "qwertyuiopasdfghjklzxcvbnmqwertyuiopqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq".getBytes());	
				qualifierValueMap.put("f11".getBytes(), "12345678901234567890111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111".getBytes());
				qualifierValueMap.put("f12".getBytes(), "qwertyuiopasdfghjklzxcvbnmqwertyuiopqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq".getBytes());	
				qualifierValueMap.put("f13".getBytes(), "12345678901234567890111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111".getBytes());
				qualifierValueMap.put("f14".getBytes(), "qwertyuiopasdfghjklzxcvbnmqwertyuiopqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq".getBytes());	
				qualifierValueMap.put("f15".getBytes(), "12345678901234567890111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111".getBytes());
				qualifierValueMap.put("f16".getBytes(), "qwertyuiopasdfghjklzxcvbnmqwertyuiopqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq".getBytes());	
				qualifierValueMap.put("f17".getBytes(), "12345678901234567890111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111".getBytes());
				qualifierValueMap.put("f18".getBytes(), "qwertyuiopasdfghjklzxcvbnmqwertyuiopqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq".getBytes());	
				qualifierValueMap.put("f19".getBytes(), "12345678901234567890111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111".getBytes());
				qualifierValueMap.put("f20".getBytes(), "qwertyuiopasdfghjklzxcvbnmqwertyuiopqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq".getBytes());	
				
				records.put(String.valueOf(System.currentTimeMillis()), qualifierValueMap);
			}
			HBaseHandler.addRecords(tableName, "info", records);
		}
	}
}
