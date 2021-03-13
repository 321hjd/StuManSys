/*
 * ���ѧ����Ϣ
 */
package com.model2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StuAddDialog extends JDialog implements ActionListener {
	//����һЩ�ؼ�
	JPanel jp1,jp2,jp3;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	
	//���캯��
	//owner�ǶԻ���ĸ�����
	//title�Ǵ�����
	//modalָ����ģʽ���ڣ������������޷���������ط��������Ƿ�ģʽ����
	public StuAddDialog(Frame owner, String title, boolean modal) {
		//���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		super(owner, title, modal);
		
		//�����ʼ��
		jl1 = new JLabel("ѧ��");
		jl2 = new JLabel("����");
		jl3 = new JLabel("�Ա�");
		jl4 = new JLabel("����");
		jl5 = new JLabel("����");
		jl6 = new JLabel("ϵ��");
		
		jtf1 = new JTextField(10);
		jtf2 = new JTextField(10);
		jtf3 = new JTextField(10);
		jtf4 = new JTextField(10);
		jtf5 = new JTextField(10);
		jtf6 = new JTextField(10);
		
		jb1 = new JButton("���");
		jb2 = new JButton("ȡ��");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		//���ò���
		jp1.setLayout(new GridLayout(6,1));	//���񲼾֣�5��һ��
		jp2.setLayout(new GridLayout(6,1));
		
		//������
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		//��������뵽Dialog
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);	
		
		//ע�����
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		//���ô�������
		this.setSize(300,200);
		this.setVisible(true);
	}

	//��������
	public void actionPerformed(ActionEvent arg0) {
		//�û������Ӱ�ť
		if(arg0.getSource() == jb1) {
			StuModel temp = new StuModel();
			String sql = "insert into stu values(?,?,?,?,?,?)";
			String[] paras = {jtf1.getText(), jtf2.getText(), 
					jtf3.getText(), jtf4.getText(), 
					jtf5.getText(), jtf6.getText()};
			//������ʧ��
			if(!temp.updStu(sql, paras)) {
				//��ʾ
				JOptionPane.showMessageDialog(this, "���ʧ��");
			}
			//�رյ�ǰ�Ի���
			this.dispose();
		}
		//�û����ȡ����ť
		else if(arg0.getSource() == jb2) {
			//�رյ�ǰ�Ի���
			this.dispose();
		}
	}
}















