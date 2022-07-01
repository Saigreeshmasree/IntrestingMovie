import java.sql.*;
import java.util.Scanner;

public class IntrestingMovies 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		try
		{
			//Connecting database....
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521","Greeshu","Greeshu");
			if(con != null)
			{
				System.out.println("Connected Successfully!!!");
				
				 //Creating new Database....
				/*
				String query = "CREATE DATABASE MOVIES";
				Statement stmt = con.createStatement();
				stmt.executeUpdate(query);
				System.out.println("DataBase is created!!!");
				*/
				
				/*
				//Creating new Table...
				String query1 = "CREATE TABLE Movie(MovieName Varchar(50),LeadActor VARCHAR(30),LeadActress VARCHAR(30),YearOfRelease Number(4),Director VARCHAR(30))";
				Statement stmt1 = con.createStatement();
				stmt1.executeUpdate(query1);
				System.out.println("Table Created...!!!");*/
				int choice;
				
				
				//Adding data into table...
				do
				{  
					System.out.println("\nSelect one option for Insertion:\n1.Add Movie Details\n2.Exit Insertion");
					choice = sc.nextInt();
					sc.nextLine();
					switch(choice)
					{
					   case 1:
					   {
						   System.out.println("Enter the Details of the movie:");
						   System.out.println("Enter name of the Movie:");
						   String movieName = sc.nextLine();
						   System.out.println("Enter name of the Lead Actor:");
						   String leadActorName = sc.nextLine();
						   System.out.println("Enter name of the Lead Actress:");
						   String leadActressName = sc.nextLine();
						   System.out.println("Enter the year in which the movie was released:");
						   int yearOfRelease = sc.nextInt();
						   sc.nextLine();
						   System.out.println("Enter name of the Director:");
						   String director = sc.nextLine();
						
						   String query2 = "Insert INTO Movie VALUES(?,?,?,?,?)";
						   PreparedStatement pstmt= con.prepareStatement(query2);
						   pstmt.setString(1, movieName);
						   pstmt.setString(2, leadActorName);
						   pstmt.setString(3, leadActressName);
						   pstmt.setInt(4,yearOfRelease);
						   pstmt.setString(5, director);
						
						   int rowsInserted = pstmt.executeUpdate();
						   if(rowsInserted>0)
							   System.out.println("The data of the movie is inserted successfully");
						   else
							   System.out.println("The data was unable to insert");
						   break;
						}
					    case 2:
					    {
					        System.out.println("About to exit the insertion of Movie details");
					        break;
					    }
					    default:
					    {
					        System.out.println("Invalid choice,try again...");
					        break;
					    }
					}
				}while(choice!=2);
				
				
				//Retrieving all Movie details...
				System.out.println("\nRetreiving all The movie details\n");
				String query3 = "SELECT * FROM Movie";
				PreparedStatement pstmt2 = con.prepareStatement(query3);
				ResultSet rs = pstmt2.executeQuery();
				int movieCount = 0;
				while(rs.next())
				{
					movieCount++;
					System.out.println("The Details of movie "+movieCount+" are:");
					System.out.println("Name of the Movie:"+rs.getString(1));
					System.out.println("Name of the LeadActor:"+rs.getString(2));
					System.out.println("Name of the LeadActress:"+rs.getString(3));
					System.out.println("The year in which the movie was released:"+rs.getInt(4));
					System.out.println("Name of the Director:"+rs.getString(5)+"\n");
				}
				if(movieCount==0)
					System.out.println("Details of the movie are not present...");
				
				
				//Retrieving movie names of actor specified...
				System.out.println("Selection of Movies Based on ActorName");
				System.out.println("Enter Name of the Actor:");
				String actorName = sc.nextLine();
				String query4 = "SELECT * FROM Movie WHERE LeadActor=?";
				PreparedStatement pstmt3 = con.prepareStatement(query4);
				pstmt3.setString(1,actorName);
				ResultSet rs2 = pstmt3.executeQuery();
				int actorMovies = 0;
				while(rs2.next())
				{
					actorMovies++;
					if(actorMovies==1)
						System.out.println("The movies acted by the "+actorName+" are:");
					System.out.println(rs2.getString(1));
				}
				if(actorMovies==0)
					System.out.println("Sorry!!,The Actor movies are not present...");
				
			}
			else
				System.out.println("Failed to connect to JDBC");
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}

}
