/*
 * stu表的模型
 */
package com.model1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.*;

public class StuModel extends AbstractTableModel{

	//rowData用来存放行数据,columnNames用来存放列名
	Vector rowData, columnNames;

	//定义操作数据库所需
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	
	//初始化
	public void init(String sql) {
		if(sql.equals("")) {
			sql = "select * from stu";
		}
		
		//处理中间部分
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
		
		//从数据库里取数据
		try {
			//1.加载驱动
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//2.建立连接
			ct = DriverManager.getConnection
					("jdbc:sqlserver://localhost:1433;databaseName=students", "sa", "hjd321");			
			//3.输入sql语句
			ps = ct.prepareStatement(sql);
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
	}
	
	//通过传递的sql语句来获得数据模型
	public StuModel(String sql) {
		this.init(sql);
	}
	
	//构造函数
	public StuModel() {
		this.init("");
	}
	
	//根据用户输入的sql语句添加数据
	public void addStu(String sql) {
		
	}
	
	//得到共有多少列
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return this.columnNames.size();
	}

	//得到共有多少行
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.rowData.size();
	}

	//得到某行某列的数据
	public Object getValueAt(int row, int column) {
		// TODO 自动生成的方法存根
		return ((Vector)this.rowData.get(row)).get(column);
		}
	
	//得到列名
	public String getColumnName(int column) {
		// TODO 自动生成的方法存根
		return (String)this.columnNames.get(column);
	}
}








