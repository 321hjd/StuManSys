/*
 * JTable��ʹ��
 */
package com.model1;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.sql.*;

public class Test1 extends JFrame{

	//rowData�������������
	//columnNames�����������
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Test1 test1 = new Test1();
	}
	
	//���캯��
	public Test1() {
		columnNames = new Vector();
		//��������
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");
		
		rowData = new Vector();
		//rowData���Դ�Ŷ���
		Vector hang = new Vector();
		hang.add("sp001");
		hang.add("�����");
		hang.add("��");
		hang.add("500");
		hang.add("����ɽ");
		hang.add("������");

		//���뵽rowData
		rowData.add(hang);
		
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











