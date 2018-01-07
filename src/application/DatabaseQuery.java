package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;

public class DatabaseQuery implements Runnable{

	Thread t;
	TestDijkstraAlgorithm ta=new TestDijkstraAlgorithm();
	LinkedList<Vertex> list=new LinkedList<>();
	int temp;
	Statement statement=null;
	PreparedStatement preparedStatement=null;
	DataConnection dc=new DataConnection();
	ResultSet resultSet=null;
	Scanner sc=new Scanner(System.in);
	int id,source,dest;
	Delivery_Pojo pojo;
	static MainController controller;
	ArrayList<Delivery_Pojo> dlist;
	
	public DatabaseQuery(int source,int dest,Delivery_Pojo pojo) {
		// TODO Auto-generated constructor stub
		temp=1;
		this.source=source;
		this.dest=dest;
		this.pojo=pojo;
		start();
	}
	
	public DatabaseQuery(int id,MainController controller) {
		// TODO Auto-generated constructor stub
		temp=2;
		this.id=id;
		this.controller=controller;
		dlist=new ArrayList<>();
		start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(temp==1)
		{
			try
			{

				String insert="insert into Delivery_Status values (?,?,?,?,?,?,?,?,?);";
				preparedStatement=(PreparedStatement) dc.getConnection().prepareStatement(insert);
				preparedStatement.setInt(1, pojo.getId());
				preparedStatement.setString(2, pojo.getFrom());
				preparedStatement.setString(3, pojo.getTo());
				preparedStatement.setString(4, pojo.getDate());
				preparedStatement.setString(5, pojo.getTime());
				preparedStatement.setString(6, pojo.getLocation());
				preparedStatement.setString(7, pojo.getWeight());
				preparedStatement.setString(8, pojo.getPieces());
				preparedStatement.setString(9, pojo.getActivity());
				preparedStatement.executeUpdate();
				preparedStatement=null; 
				dc.closeConnection();
				list=ta.testExcute(source, dest);

				for(Vertex v:list)
				{
					System.out.println(v);
					t.sleep(600);
					insert(v);
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		else if(temp==2)
		{
			try
			{
				dc.getConnection();
				String search="Select * from Delivery_Status where "+Constants.ID+"= ?";
				preparedStatement=(PreparedStatement) dc.getConnection().prepareStatement(search);
				preparedStatement.setInt(1,id);
				resultSet=preparedStatement.executeQuery();

				while(resultSet.next())
				{
					pojo=new Delivery_Pojo();
					pojo.setId(resultSet.getInt(1));
					pojo.setFrom(resultSet.getString(2));
					pojo.setTo(resultSet.getString(3));
					pojo.setDate(resultSet.getString(4));
					pojo.setTime(resultSet.getString(5));
					pojo.setLocation(resultSet.getString(6));
					pojo.setWeight(resultSet.getString(7));
					pojo.setPieces(resultSet.getString(8));
					pojo.setActivity(resultSet.getString(9));
					
					dlist.add(pojo);
				}
				setUI();
			} 
			catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
			dc.closeConnection();
		}
	}


	public void start () {
		System.out.println("Starting thread " );
		if (t == null) {
			t = new Thread (this);
			t.start ();
		}
	}

	public void insert(Vertex v)
	{
		try {

			String insert="insert into Delivery_Status values (?,?,?,?,?,?,?,?,?);";
			preparedStatement=(PreparedStatement) dc.getConnection().prepareStatement(insert);
			preparedStatement.setInt(1, pojo.getId());
			preparedStatement.setString(2, pojo.getFrom());
			preparedStatement.setString(3, pojo.getTo());
			preparedStatement.setString(4, pojo.getDate());
			preparedStatement.setString(5, pojo.getTime());
			preparedStatement.setString(6, Constants.getList().get(Integer.parseInt(v+"")));
			preparedStatement.setString(7, pojo.getWeight());
			preparedStatement.setString(8, pojo.getPieces());
			if(dest==Integer.parseInt(v+""))
			{
				preparedStatement.setString(9, "Delivered");
			}
			else
			{
				preparedStatement.setString(9, "Shipped");
			}
			preparedStatement.executeUpdate();
			preparedStatement=null;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dc.closeConnection();
	}
	
	public void setUI()
	{
		controller.table.setItems(FXCollections.observableArrayList(dlist));
		controller.dateText.setText(dlist.get(0).getDate());
		controller.track_Num.setText(dlist.get(0).getId()+"");
		controller.wt.setText(dlist.get(0).getWeight());
		controller.pieceText.setText(dlist.get(0).getPieces());
		if(dlist.get(dlist.size()-1).getActivity().equals("Delivered"))
		{
			controller.act_delText.setText(dlist.get(dlist.size()-1).getDate()+ " "+dlist.get(dlist.size()-1).getTime());
		}
	}

}
