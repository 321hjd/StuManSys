/*
 * �����ݿ���в�������
 */
package com.model2;

import java.sql.*;

public class SqlHelper {
	//����������ݿ�����
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;databaseName=students";
	String user = "sa";
	String passwd = "hjd321";
	
	//���캯��������������
	public SqlHelper() {
		try {
			//1.��������
			Class.forName(driver);
			//2.�õ�����
			ct = DriverManager.getConnection(url, user, passwd);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ر����ݿ���Դ
	public void close() {
		//�ر���Դ���ȿ���أ�
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(ct != null) ct.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//��ѯ���ݿ����(��������ѯʱ������ѯ����)
	public ResultSet queryExecute() {
		String sql = "select * from stu";
		try {
			//����PreparedStatement����
			ps = ct.prepareStatement(sql);
			//��ѯ
			rs = ps.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//�ر���Դ������������Ҫ�ر���
		}
		
		return rs;
	}
	
	//��ѯ���ݿ����
	public ResultSet queryExecute(String sql, String[] paras) {
		try {
			//����PreparedStatement����
			ps = ct.prepareStatement(sql);
			//��ps�ģ���ֵ
			for(int i = 0;i < paras.length;i++) {
				//���ݿ��ڲ����Զ���StringתΪ��Ӧ������
				ps.setString(i+1, paras[i]);	//һ����i+1����Ϊ��1��ʼ����
			}
			//��ѯ
			rs = ps.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//�ر���Դ������������Ҫ�ر���
		}
		
		return rs;
	}
	
	//����ɾ�ĺ���һ��
	public boolean updExecute(String sql, String[] paras) {
		//����Ƿ�ɹ�
		boolean b = true;
		
		try {
			//����PreparedStatement����
			ps = ct.prepareStatement(sql);
			//��ps�ģ���ֵ
			for(int i = 0;i < paras.length;i++) {
				//���ݿ��ڲ����Զ���StringתΪ��Ӧ������
				ps.setString(i+1, paras[i]);	//һ����i+1����Ϊ��1��ʼ����
			}
			//ִ�в���
			if(ps.executeUpdate() != 1) {
				b = false;
			}	
		} catch(Exception e) {
			b = false;
			e.printStackTrace();
		} finally {
			//�ر����ݿ���Դ
			this.close();
		}
		
		return b;
	}
}
