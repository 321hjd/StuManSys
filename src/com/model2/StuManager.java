/*
 * 学生管理系统——model2模式
 */
package com.model2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class StuManager extends JFrame implements ActionListener{
	
	//定义一些控件
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	
	//定义一个数据模型对象
	StuModel sm;
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		StuManager test3 = new StuManager();
	}
	
	//构造函数
	public StuManager() {
		//初始化组件
		//处理jp1
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("查询");
		jl1 = new JLabel("请输入名字");
		//把各个组件加入到jp1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		//处理jp2
		jp2 = new JPanel();
		jb2 = new JButton("添加");
		jb3 = new JButton("修改");
		jb4 = new JButton("删除");
		//把各个组件加入到jp2
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//中间部分（数据库操作）
		//初始化数据模型对象
		sm = new StuModel();
		sm.queryStu();
		
		//初始化JTable
		jt = new JTable(sm);
		//初始化JScrollPane
		jsp = new JScrollPane(jt);
		
		//注册监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		
		//把各板块放入到JFrame
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		
		//设置窗口属性
		this.setSize(400, 300);
		this.setTitle("学生管理系统");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	//监听处理
	public void actionPerformed(ActionEvent arg0) {
		//用户点击查询
		if(arg0.getSource() == jb1) {
			//完成查询任务(trim用来除去文本中的空格)
			String name = this.jtf.getText().trim();
			//写一个sql语句
			String sql = "select * from stu where stuName = ?"; 
			String[] paras = {name};
			//构建新的数据模型类，并更新
			
			//注意：！！！不要这样写，应该写为实例变量，否则很容易造成内存泄露
			//有变量指向堆区内存，但未使用，系统无法回收资源
//			StuModel sm = new StuModel();
			
			sm = new StuModel();
			sm.queryStu(sql, paras);
			
			//更新JTable
			jt.setModel(sm);
		}
		//当用户点击添加
		else if(arg0.getSource() == jb2) {
			StuAddDialog sa = new StuAddDialog(this, "添加学生", true);
			//重新获得新的数据模型
			//构建新的数据模型类，并更新
			sm = new StuModel();
			sm.queryStu();
			//更新JTable
			jt.setModel(sm);
		}
		//用户点击修改
		else if(arg0.getSource() == jb3) {
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1) {
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			//显式修改对话框
			new StuUpdDialog(this, "修改学生", true, sm, rowNum);
			//构建新的数据模型类，并更新
			sm = new StuModel();
			sm.queryStu();
			//更新JTable
			jt.setModel(sm);
		}
		//用户点击删除
		else if(arg0.getSource() == jb4) {
			//1.得到学生的id
			//getSelectedRow()返回用户选中的行，若一个没选，则返回-1
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1) {
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			
			//2.得到学生编号(第rowNum行第0列)
			String stuId = (String)sm.getValueAt(rowNum, 0);
			
			//3.创建一个sql
			String sql = "delete from stu where stuId=?";
			String[] paras = {stuId};
			StuModel temp = new StuModel();
			temp.updStu(sql, paras);
			
			//4.构建新的数据模型类，并更新
			sm = new StuModel();
			sm.queryStu();
			//更新JTable
			jt.setModel(sm);
		}	
	}

}











