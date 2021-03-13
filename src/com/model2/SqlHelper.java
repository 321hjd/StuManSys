/*
 * 对数据库进行操作的类
 */
package com.model2;

import java.sql.*;

public class SqlHelper {
	//定义操作数据库所需
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;databaseName=students";
	String user = "sa";
	String passwd = "hjd321";
	
	//构造函数（加载驱动）
	public SqlHelper() {
		try {
			//1.加载驱动
			Class.forName(driver);
			//2.得到连接
			ct = DriverManager.getConnection(url, user, passwd);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//关闭数据库资源
	public void close() {
		//关闭资源（先开后关）
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(ct != null) ct.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查询数据库操作(无条件查询时，即查询所有)
	public ResultSet queryExecute() {
		String sql = "select * from stu";
		try {
			//创建PreparedStatement对象
			ps = ct.prepareStatement(sql);
			//查询
			rs = ps.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//关闭资源？？？？（需要关闭吗）
		}
		
		return rs;
	}
	
	//查询数据库操作
	public ResultSet queryExecute(String sql, String[] paras) {
		try {
			//创建PreparedStatement对象
			ps = ct.prepareStatement(sql);
			//给ps的？赋值
			for(int i = 0;i < paras.length;i++) {
				//数据库内部会自动将String转为对应的类型
				ps.setString(i+1, paras[i]);	//一定是i+1，因为从1开始计数
			}
			//查询
			rs = ps.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//关闭资源？？？？（需要关闭吗）
		}
		
		return rs;
	}
	
	//把增删改合在一起
	public boolean updExecute(String sql, String[] paras) {
		//添加是否成功
		boolean b = true;
		
		try {
			//创建PreparedStatement对象
			ps = ct.prepareStatement(sql);
			//给ps的？赋值
			for(int i = 0;i < paras.length;i++) {
				//数据库内部会自动将String转为对应的类型
				ps.setString(i+1, paras[i]);	//一定是i+1，因为从1开始计数
			}
			//执行操作
			if(ps.executeUpdate() != 1) {
				b = false;
			}	
		} catch(Exception e) {
			b = false;
			e.printStackTrace();
		} finally {
			//关闭数据库资源
			this.close();
		}
		
		return b;
	}
}
