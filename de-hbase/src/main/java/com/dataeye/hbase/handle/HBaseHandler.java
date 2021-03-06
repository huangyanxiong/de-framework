package com.dataeye.hbase.handle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import com.dataeye.hbase.HBaseConnUtil;
import com.dataeye.hbase.util.ProfileManager;

public class HBaseHandler {

	private static String tableName = ProfileManager.get().getProperty("dedp.hbase.table");
	private static String columnFamily = ProfileManager.get().getProperty("dedp.hbase.columnFamily");

	public static boolean handle(CopyOnWriteArrayList<String> lineList) {

		boolean isSuccess = false;
		try {

			List<String> columns = Arrays.asList(columnFamily.split(","));
			createTable(tableName, columns);

			for (String line : lineList) {
				String[] lines = line.split("#");
				if (lines.length <= 1){
					continue;
				}
				String rowKey = null;
				Map<byte[], byte[]> qualifierValueMap = new HashMap<byte[], byte[]>();
				for (int i = 0; i < lines.length; i++) {
					if (i==0){
						rowKey = lines[i];
					}else{
						String[] KV = lines[i].split(":");
						if (KV.length == 2){
							qualifierValueMap.put(KV[0].getBytes(), KV[1].getBytes());
						}
					}					
				}
				if (rowKey != null && qualifierValueMap.size() > 0){
					addRecord(tableName, rowKey, columnFamily, qualifierValueMap);
				}
			}
			isSuccess = true;
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 创建表
	 * 
	 * @throws Exception
	 */
	public static void createTable(String tableName, List<String> columnList) {
		Connection conn = null;
		try {
			conn = HBaseConnUtil.getInstance();
			HBaseAdmin hBaseAdmin = (HBaseAdmin) conn.getAdmin();

			if (hBaseAdmin.tableExists(tableName)) {
				System.out.println("table is exists !");
			} else {
				HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
				// 添加列簇
				for (String column : columnList) {
					desc.addFamily(new HColumnDescriptor(column));
				}
				hBaseAdmin.createTable(desc);
				System.out.println("create success !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询时，会每个cell代表一个列簇中的一个区域， 例如：有一个列簇为 test_1
	 * 1.如果存储数据时没有存储列修饰符，则cell代表整个列簇的内容，查询出的就是该行下整个列簇的内容
	 * 2.如果存储数据时有存储列修饰符，则每个列簇下的列修饰符各有一个cell
	 * 
	 */
	public static void query(String tableName) {

		Connection conn = null;
		HTable table = null;
		ResultScanner scann = null;
		try {
			conn = HBaseConnUtil.getInstance();
			table = (HTable) conn.getTable(TableName.valueOf(tableName));

			scann = table.getScanner(new Scan());
			/**
			 * 循环读取按行区分： 读取结果为： 该表RowKey为：1445320222118 列簇为：test_1
			 * 值为：这是第一行第一列的数据 列簇为：test_2 值为：这是第一行第二列的数据 列簇为：test_3
			 * 值为：这是第一行第三列的数据 ==========================================
			 * 该表RowKey为：1445320222120 列簇为：test_1 值为：这是第二行第一列的数据 列簇为：test_2
			 * 值为：这是第二行第二列的数据 列簇为：test_3 值为：这是第二行第三列的数据
			 * ==========================================
			 */
			for (Result rs : scann) {
				System.out.println("该表RowKey为：" + new String(rs.getRow()));
				/**
				 * 这边循环是按cell进行循环
				 */
				for (Cell cell : rs.rawCells()) {
					System.out.println("列簇为：" + new String(CellUtil.cloneFamily(cell)));
					System.out.println("列修饰符为：" + new String(CellUtil.cloneQualifier(cell)));
					System.out.println("值为：" + new String(CellUtil.cloneValue(cell)));
				}
				System.out.println("==========================================");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != scann) {
				scann.close();
			}
			if (null != table) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}			
		}

	}

	/**
	 * 根据RowKey查询单行
	 */
	public static void queryByRowKey(String tableName, String rowKey) {
		Connection conn = null;
		HTable table = null;
		try {
			conn = HBaseConnUtil.getInstance();
			table = (HTable) conn.getTable(TableName.valueOf(tableName));
			Result rs = table.get(new Get(rowKey.getBytes()));
			System.out.println("result: ");
			for (Cell cell : rs.rawCells()) {
				System.out.println("列簇为：" + new String(CellUtil.cloneFamily(cell)));
				System.out.println("值为：" + new String(CellUtil.cloneValue(cell)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (null != table) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 插入数据
	 * 
	 * @throws Exception
	 */
	public static void addRecord(String tableName, String rowKey, String family, Map<byte[], byte[]> qualifierValueMap) {
		Connection conn = null;
		HTable hTable = null;
		try {
			conn = HBaseConnUtil.getInstance();
			hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			// 一个PUT代表一行，构造函数传入的是RowKey
			Put put = new Put(Bytes.toBytes(rowKey));
			byte[] familyBytes = Bytes.toBytes(family);
			for (Entry<byte[], byte[]> entry : qualifierValueMap.entrySet()) {
				put.addColumn(familyBytes, entry.getKey(), entry.getValue());
			}
			hTable.put(put);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != hTable) {
				try {
					hTable.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	/**
	 * 批量插入数据
	 * 
	 * @throws Exception
	 */
	public static void addRecords(String tableName, String family, Map<String, Map<byte[], byte[]>> rows) {
		Connection conn = null;
		HTable hTable = null;
		try {
			conn = HBaseConnUtil.getInstance();
			hTable = (HTable) conn.getTable(TableName.valueOf(tableName));
			List<Put> putList = new ArrayList<Put>();
			for (Map.Entry<String, Map<byte[], byte[]>> entrys : rows.entrySet()) {
				// 一个PUT代表一行，构造函数传入的是RowKey
				Put put = new Put(Bytes.toBytes(entrys.getKey()));
				byte[] familyBytes = Bytes.toBytes(family);
				for (Entry<byte[], byte[]> entry : entrys.getValue().entrySet()) {
					put.addColumn(familyBytes, entry.getKey(), entry.getValue());
				}
				putList.add(put);
			}
			hTable.put(putList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != hTable) {
				try {
					hTable.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 删除指定名称的列簇
	 */
	public static void deleteFamily(String tableName, String column) {

		Connection conn = null;
		HBaseAdmin admin = null;
		try {
			conn = HBaseConnUtil.getInstance();
			admin = (HBaseAdmin) conn.getAdmin();
			admin.deleteColumn(tableName.getBytes(), column);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除指定行
	 */
	public static void deleteRow(String tableName, String rowKey) {

		Connection conn = null;
		HTable table = null;
		try {
			conn = HBaseConnUtil.getInstance();
			table = (HTable) conn.getTable(TableName.valueOf(tableName));
			table.delete(new Delete(rowKey.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != table) {
					table.close();
				}
				if (null != conn) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 删除指定表名
	 */
	public static void deleteTable(String tableName) {

		Connection conn = null;
		HBaseAdmin admin = null;
		try {
			conn = HBaseConnUtil.getInstance();
			admin = (HBaseAdmin) conn.getAdmin();
			// 在删除一张表前，要使其失效
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != conn) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
