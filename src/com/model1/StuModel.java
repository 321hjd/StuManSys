/*
 * stu���ģ��
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

	//rowData�������������,columnNames�����������
	Vector rowData, columnNames;

	//����������ݿ�����
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	
	//��ʼ��
	public void init(String sql) {
		if(sql.equals("")) {
			sql = "select * from stu";
		}
		
		//�����м䲿��
		//��ʼ��JTable���������������ݱ���
		columnNames = new Vector();
		rowData = new Vector();
		
		//��������
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");
		
		//�����ݿ���ȡ����
		try {
			//1.��������
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//2.��������
			ct = DriverManager.getConnection
					("jdbc:sqlserver://localhost:1433;databaseName=students", "sa", "hjd321");			
			//3.����sql���
			ps = ct.prepareStatement(sql);
			//4.��ѯ
			rs = ps.executeQuery();
			//5.��ȡ
			while(rs.next()) {
				//rowData���Դ�Ŷ���
				Vector hang = new Vector();
				hang.add(rs.getString("stuId"));
				hang.add(rs.getString("stuName"));
				hang.add(rs.getString("stuSex"));
				hang.add(rs.getInt("stuAge"));
				hang.add(rs.getString("stuJg"));
				hang.add(rs.getString("stuDept"));
				
				//���뵽rowData
				rowData.add(hang);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//�ر���Դ���ȿ���أ�
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(ct != null) ct.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//ͨ�����ݵ�sql������������ģ��
	public StuModel(String sql) {
		this.init(sql);
	}
	
	//���캯��
	public StuModel() {
		this.init("");
	}
	
	//�����û������sql����������
	public void addStu(String sql) {
		
	}
	
	//�õ����ж�����
	public int getColumnCount() {
		// TODO �Զ����ɵķ������
		return this.columnNames.size();
	}

	//�õ����ж�����
	public int getRowCount() {
		// TODO �Զ����ɵķ������
		return this.rowData.size();
	}

	//�õ�ĳ��ĳ�е�����
	public Object getValueAt(int row, int column) {
		// TODO �Զ����ɵķ������
		return ((Vector)this.rowData.get(row)).get(column);
		}
	
	//�õ�����
	public String getColumnName(int column) {
		// TODO �Զ����ɵķ������
		return (String)this.columnNames.get(column);
	}
}








