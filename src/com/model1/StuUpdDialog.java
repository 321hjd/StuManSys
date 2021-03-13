/*
 * �޸�ѧ����Ϣ
 */
package com.model1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StuUpdDialog extends JDialog implements ActionListener {
	//����һЩ�ؼ�
	JPanel jp1,jp2,jp3;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	
	//���캯��
	//owner�ǶԻ���ĸ�����
	//title�Ǵ�����
	//modalָ����ģʽ���ڣ������������޷���������ط��������Ƿ�ģʽ����
	public StuUpdDialog(Frame owner, String title, boolean modal, StuModel sm, int rowNums) {
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
		jtf1.setText((String)sm.getValueAt(rowNums, 0));
		//ѧ�Ų����޸�
		jtf1.setEditable(false);	
		jtf2 = new JTextField(10);
		jtf2.setText((String)sm.getValueAt(rowNums, 1));
		jtf3 = new JTextField(10);
		jtf3.setText((String)sm.getValueAt(rowNums, 2));
		jtf4 = new JTextField(10);
		jtf4.setText(sm.getValueAt(rowNums, 3).toString());	//�˴�ΪInteger���ͣ�����ֱ��ǿת
		jtf5 = new JTextField(10);
		jtf5.setText((String)sm.getValueAt(rowNums, 4));
		jtf6 = new JTextField(10);
		jtf6.setText((String)sm.getValueAt(rowNums, 5));
		
		jb1 = new JButton("�޸�");
		jb2 = new JButton("ȡ��");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		//���ò���
		jp1.setLayout(new GridLayout(6,1));	//���񲼾֣�����һ��
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
		
		//���ô�������
		this.setSize(300,200);
		this.setVisible(true);
	}

	//��������
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		//���û����ɾ����ť�����Ӧ
		if(arg0.getSource() == jb1) {
			//����������ݿ�����
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			//�������ݿ�
			try {
				//1.��������
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//2.��������
				ct = DriverManager.getConnection
						("jdbc:sqlserver://localhost:1433;databaseName=students", "sa", "hjd321");			
				//3.����sql���
				String strsql = "update stu set stuName=?, "
						+ "stuSex=?, stuAge=?, stuJg=?, stuDept=? where stuId=?";
				ps = ct.prepareStatement(strsql);
				
				//4.����?��ֵ
				ps.setString(1, jtf2.getText());
				ps.setString(2, jtf3.getText());
				ps.setString(3, jtf4.getText());
				ps.setString(4, jtf5.getText());
				ps.setString(5, jtf6.getText());
				ps.setString(6, jtf1.getText());
				
				//5.ִ�в���
				ps.executeUpdate();
				
				//6.�رնԻ���
				this.dispose();
				
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
	}
}















