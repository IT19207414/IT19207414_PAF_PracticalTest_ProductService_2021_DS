package com;
import java.sql.*;
public class Product
{
	private Connection connect()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/productdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			
			//For testing
			System.out.println("Successfully Connected");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return con;
	}
	public String readProduct()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>pid</th><th>pame</th><th>ptype</th><th>description</th><th>price</th>" +
					"<th>quantity</th>" +
					"<th>projId</th>" +
					"<th>Update</th><th>Remove</th></tr>";
			String query = "select * from product";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			
			while (rs.next())
			{
				String pid = Integer.toString(rs.getInt("pid"));
				String pname = rs.getString("pname");
				String ptype=rs.getString("ptype");
				String description=rs.getString("description");
				String price = rs.getString("price");
				String quantity = rs.getString("quantity");
				String projId = rs.getString("projId");
				// Add into the html table
				output += "<tr><td>" + pid + "</td>";
				output += "<td>" + pname + "</td>";
				output += "<td>" + ptype + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + quantity + "</td>";
				output += "<td>" + projId + "</td>";
				// buttons
				output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
				+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='"
				+ pid + "'>" + "</td></tr>";
			}
				con.close();
			// Complete the html table
				output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the product.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String insertProduct(String pname,String ptype,String description, String price, String quantity,String projId)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
			return "Error while connecting to the database for inserting.";
			}
		// create a prepared statement
			String query = " insert into product(`pid`,`pname`,`ptype`,`description`,`price`,`quantity`,`projId`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pname);
			preparedStmt.setString(3, ptype);
			preparedStmt.setString(4, description);
			preparedStmt.setString(5, price);
			preparedStmt.setString(6, quantity);
			preparedStmt.setString(7, projId);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProducts = readProduct();
			output = "{\"status\":\"success\", \"data\": \"" +newProducts + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while product the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateProduct(String pid, String pname,String ptype,String description, String price, String quantity,String projId)
	{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE product SET pname=?,ptype=?,description=?,price=?,quantity=?,projId=? WHERE pid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, pname);
				preparedStmt.setString(2, ptype);
				preparedStmt.setString(3, description);
				preparedStmt.setDouble(4, Double.parseDouble(price));
				preparedStmt.setInt(5, Integer.parseInt(quantity));
				preparedStmt.setInt(6, Integer.parseInt(projId));
				preparedStmt.setInt(7, Integer.parseInt(pid));
				preparedStmt.execute();
				con.close();
				String newProducts = readProduct();
				output = "{\"status\":\"success\", \"data\": \"" + newProducts + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while updating the Product.\"}";
				System.err.println(e.getMessage());
			}
		return output;
	}
	public String deleteProduct(String pid)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from product where pid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProducts = readProduct();
			output = "{\"status\":\"success\", \"data\": \"" + newProducts + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the product.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}