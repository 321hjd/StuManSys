/*
 * ���һ��mini�汾��ѧ������ϵͳ����model1ģʽ
 * 1.дһ����ר����ɶ����ݿ�Ĳ���
 * 2.���һ��ѧ��
 */
package com.model1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class Test3 extends JFrame implements ActionListener{
	
	//����һЩ�ؼ�
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	
	//����һ������ģ�Ͷ���
	StuModel sm;
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Test3 test3 = new Test3();
	}
	
	//���캯��
	public Test3() {
		//��ʼ�����
		//����jp1
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("��ѯ");
		jl1 = new JLabel("����������");
		//�Ѹ���������뵽jp1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		//����jp2
		jp2 = new JPanel();
		jb2 = new JButton("���");
		jb3 = new JButton("�޸�");
		jb4 = new JButton("ɾ��");
		//�Ѹ���������뵽jp2
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//�м䲿�֣����ݿ������
		//��ʼ������ģ�Ͷ���
		sm = new StuModel();
		
		//��ʼ��JTable
		jt = new JTable(sm);
		//��ʼ��JScrollPane
		jsp = new JScrollPane(jt);
		
		//ע�����
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		
		//�Ѹ������뵽JFrame
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		
		//���ô�������
		this.setSize(400, 300);
		this.setTitle("ѧ������ϵͳ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	//��������
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		//�û������ѯ
		if(arg0.getSource() == jb1) {
			System.out.println("�û�ϣ����ѯ");
			
			//��ɲ�ѯ����(trim��������������)
			String name = this.jtf.getText().trim();
			//дһ��sql���
			String sql = "select * from stu where stuName = '"+name+"'"; 
			//�����µ�����ģ���࣬������
			
			//ע�⣺��������Ҫ����д��Ӧ��дΪʵ���������������������ڴ�й¶
			//�б���ָ������ڴ棬��δʹ�ã�ϵͳ�޷�������Դ
//			StuModel sm = new StuModel(sql);
			
			sm = new StuModel(sql);
			
			//����JTable
			jt.setModel(sm);
		}
		//���û�������
		else if(arg0.getSource() == jb2) {
			StuAddDialog sa = new StuAddDialog(this, "���ѧ��", true);
			//���»���µ�����ģ��
			//�����µ�����ģ���࣬������
			sm = new StuModel();
			//����JTable
			jt.setModel(sm);
		}
		//�û�����޸�
		else if(arg0.getSource() == jb3) {
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1) {
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			//��ʽ�޸ĶԻ���
			new StuUpdDialog(this, "�޸�ѧ��", true, sm, rowNum);
			//�����µ�����ģ���࣬������
			sm = new StuModel();
			//����JTable
			jt.setModel(sm);
		}
		//�û����ɾ��
		else if(arg0.getSource() == jb4) {
			//1.�õ�ѧ����id
			//getSelectedRow()�����û�ѡ�е��У���һ��ûѡ���򷵻�-1
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1) {
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			//2.�õ�ѧ�����(��rowNum�е�0��)
			String stuId = (String)sm.getValueAt(rowNum, 0);
			//3.�������ݿ⣬���ɾ������
			//����������ݿ�����
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			try {
				//1.��������
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//2.��������
				ct = DriverManager.getConnection
						("jdbc:sqlserver://localhost:1433;databaseName=students", "sa", "hjd321");			
				//3.����sql���
				ps = ct.prepareStatement("delete from stu where stuId = ?");
				
				//4.����ɾ������
				ps.setString(1,stuId);
				
				//5.ִ�в���
				ps.executeUpdate();
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
			//�����µ�����ģ���࣬������
			sm = new StuModel();
			//����JTable
			jt.setModel(sm);
		}	
	}

}











