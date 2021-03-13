/*
 * �����ݿ���ȡ��ѧ����Ϣ
 */
package com.model1;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.sql.*;

public class Test2 extends JFrame{

	//rowData�������������
	//columnNames�����������
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	//����������ݿ�����
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Test2 test2 = new Test2();
	}
	
	//���캯��
	public Test2() {
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
			ps = ct.prepareStatement("select * from stu");
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
		
		//��ʼ��JTable
		jt = new JTable(rowData, columnNames);
		
		//��ʼ��JScrollPane
		jsp = new JScrollPane(jt);
		
		//��jsp���뵽JFrame
		this.add(jsp);
		
		//���ô�������
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}











