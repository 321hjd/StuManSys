/*
 * 从数据库中取出学生信息
 */
package com.model1;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.sql.*;

public class Test2 extends JFrame{

	//rowData用来存放行数据
	//columnNames用来存放列名
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	//定义操作数据库所需
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Test2 test2 = new Test2();
	}
	
	//构造函数
	public Test2() {
		//初始化JTable所需列名和行数据变量
		columnNames = new Vector();
		rowData = new Vector();
		
		//设置列名
		columnNames.add("学号");
		columnNames.add("名字");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");
		
		//从数据库李取数据
		try {
			//1.加载驱动
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//2.建立连接
			ct = DriverManager.getConnection
					("jdbc:sqlserver://localhost:1433;databaseName=students", "sa", "hjd321");			
			//3.输入sql语句
			ps = ct.prepareStatement("select * from stu");
			//4.查询
			rs = ps.executeQuery();
			//5.读取
			while(rs.next()) {
				//rowData可以存放多行
				Vector hang = new Vector();
				hang.add(rs.getString("stuId"));
				hang.add(rs.getString("stuName"));
				hang.add(rs.getString("stuSex"));
				hang.add(rs.getInt("stuAge"));
				hang.add(rs.getString("stuJg"));
				hang.add(rs.getString("stuDept"));
				
				//加入到rowData
				rowData.add(hang);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//关闭资源（先开后关）
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(ct != null) ct.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		//初始化JTable
		jt = new JTable(rowData, columnNames);
		
		//初始化JScrollPane
		jsp = new JScrollPane(jt);
		
		//把jsp放入到JFrame
		this.add(jsp);
		
		//设置窗口属性
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}











