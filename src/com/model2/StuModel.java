/*
 * ��̨�����㣨�����ݿ���в�����
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

	//rowData�������������,columnNames�����������
	Vector rowData, columnNames;
	
	//��ѧ����Ϣ������ɾ�Ĳ���
	public boolean updStu(String sql, String[] paras) {
		//����SqlHelper���������Ĳ����Բ����ǣ����ð�SqlHelper����static��
		//����ǲ����Ļ����ٶȻ����
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.updExecute(sql, paras);
	}

	//��ѯ������������
	public void queryStu() {
		SqlHelper sqlHelper = null;
		
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
			//��ѯ
			sqlHelper = new SqlHelper();
			ResultSet rs = sqlHelper.queryExecute();
			
			//��ȡ
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
			//�ر���Դ
			sqlHelper.close();
		}
	}
	
	//��ѯ
	public void queryStu(String sql, String[] paras) {
		SqlHelper sqlHelper = null;
		
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
			//��ѯ
			sqlHelper = new SqlHelper();
			ResultSet rs = sqlHelper.queryExecute(sql, paras);
			
			//��ȡ
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
			//�ر���Դ
			sqlHelper.close();
		}
	}
	
	//���캯��
	public StuModel() {
		
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








