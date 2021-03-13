/*
 * ѧ������ϵͳ����model2ģʽ
 */
package com.model2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class StuManager extends JFrame implements ActionListener{
	
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
		StuManager test3 = new StuManager();
	}
	
	//���캯��
	public StuManager() {
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
		sm.queryStu();
		
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
		//�û������ѯ
		if(arg0.getSource() == jb1) {
			//��ɲ�ѯ����(trim������ȥ�ı��еĿո�)
			String name = this.jtf.getText().trim();
			//дһ��sql���
			String sql = "select * from stu where stuName = ?"; 
			String[] paras = {name};
			//�����µ�����ģ���࣬������
			
			//ע�⣺��������Ҫ����д��Ӧ��дΪʵ���������������������ڴ�й¶
			//�б���ָ������ڴ棬��δʹ�ã�ϵͳ�޷�������Դ
//			StuModel sm = new StuModel();
			
			sm = new StuModel();
			sm.queryStu(sql, paras);
			
			//����JTable
			jt.setModel(sm);
		}
		//���û�������
		else if(arg0.getSource() == jb2) {
			StuAddDialog sa = new StuAddDialog(this, "���ѧ��", true);
			//���»���µ�����ģ��
			//�����µ�����ģ���࣬������
			sm = new StuModel();
			sm.queryStu();
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
			sm.queryStu();
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
			
			//3.����һ��sql
			String sql = "delete from stu where stuId=?";
			String[] paras = {stuId};
			StuModel temp = new StuModel();
			temp.updStu(sql, paras);
			
			//4.�����µ�����ģ���࣬������
			sm = new StuModel();
			sm.queryStu();
			//����JTable
			jt.setModel(sm);
		}	
	}

}











