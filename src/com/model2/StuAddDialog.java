/*
 * 添加学生信息
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
	//定义一些控件
	JPanel jp1,jp2,jp3;
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	
	//构造函数
	//owner是对话框的父窗口
	//title是窗口名
	//modal指定是模式窗口（即不做操作无法点击其它地方），还是非模式窗口
	public StuAddDialog(Frame owner, String title, boolean modal) {
		//调用父类构造方法，达到模式对话框效果
		super(owner, title, modal);
		
		//组件初始化
		jl1 = new JLabel("学号");
		jl2 = new JLabel("姓名");
		jl3 = new JLabel("性别");
		jl4 = new JLabel("年龄");
		jl5 = new JLabel("籍贯");
		jl6 = new JLabel("系别");
		
		jtf1 = new JTextField(10);
		jtf2 = new JTextField(10);
		jtf3 = new JTextField(10);
		jtf4 = new JTextField(10);
		jtf5 = new JTextField(10);
		jtf6 = new JTextField(10);
		
		jb1 = new JButton("添加");
		jb2 = new JButton("取消");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		//设置布局
		jp1.setLayout(new GridLayout(6,1));	//网格布局：5行一列
		jp2.setLayout(new GridLayout(6,1));
		
		//添加组件
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
		
		//将组件加入到Dialog
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);	
		
		//注册监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		//设置窗口属性
		this.setSize(300,200);
		this.setVisible(true);
	}

	//监听处理
	public void actionPerformed(ActionEvent arg0) {
		//用户点击添加按钮
		if(arg0.getSource() == jb1) {
			StuModel temp = new StuModel();
			String sql = "insert into stu values(?,?,?,?,?,?)";
			String[] paras = {jtf1.getText(), jtf2.getText(), 
					jtf3.getText(), jtf4.getText(), 
					jtf5.getText(), jtf6.getText()};
			//如果添加失败
			if(!temp.updStu(sql, paras)) {
				//提示
				JOptionPane.showMessageDialog(this, "添加失败");
			}
			//关闭当前对话框
			this.dispose();
		}
		//用户点击取消按钮
		else if(arg0.getSource() == jb2) {
			//关闭当前对话框
			this.dispose();
		}
	}
}















