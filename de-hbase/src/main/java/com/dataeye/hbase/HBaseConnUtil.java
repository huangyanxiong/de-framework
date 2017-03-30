package com.dataeye.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import com.dataeye.hbase.util.ProfileManager;

public class HBaseConnUtil {

	private static volatile Connection connection  = null;

	private static String hbase_zookeeper_quorum = ProfileManager.get().getProperty("hbase_zookeeper_quorum");
	private static String hbase_zookeeper_property_clientPort = ProfileManager.get().getProperty("hbase_zookeeper_property_clientPort");

	private HBaseConnUtil() {
	}

	public static Connection getInstance() {
		if (connection == null) {
			synchronized (HBaseConnUtil.class) {
				if (connection == null) {
					
					Configuration configuration = HBaseConfiguration.create();
					configuration.set("hbase.zookeeper.quorum", hbase_zookeeper_quorum);
					configuration.set("hbase.zookeeper.property.clientPort", hbase_zookeeper_property_clientPort);

					try {
						connection = ConnectionFactory.createConnection(configuration);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return connection;
	}

	public static void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		HBaseConnUtil.getInstance();
	}
}
