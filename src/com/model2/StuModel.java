/*
 * 后台操作层（对数据库进行操作）
 */
package com.model2;

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
	
	//对学生信息进行增删改操作
	public boolean updStu(String sql, String[] paras) {
		//创建SqlHelper（如果程序的并发性不考虑，可用把SqlHelper做成static）
		//如果是并发的话，速度会很慢
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.updExecute(sql, paras);
	}

	//查询（不带条件）
	public void queryStu() {
		SqlHelper sqlHelper = null;
		
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
			//查询
			sqlHelper = new SqlHelper();
			ResultSet rs = sqlHelper.queryExecute();
			
			//读取
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
			//关闭资源
			sqlHelper.close();
		}
	}
	
	//查询
	public void queryStu(String sql, String[] paras) {
		SqlHelper sqlHelper = null;
		
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
			//查询
			sqlHelper = new SqlHelper();
			ResultSet rs = sqlHelper.queryExecute(sql, paras);
			
			//读取
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
			//关闭资源
			sqlHelper.close();
		}
	}
	
	//构造函数
	public StuModel() {
		
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








