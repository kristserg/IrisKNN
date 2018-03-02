import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class IrisSolver 
{
	public static void main(String[] args) 
	{
		System.out.println("Enter k (number between 1 and 119): ");
		Scanner s = new Scanner(System.in);
		int k = s.nextInt();
		while(k < 1 || k > 119)
		{
			System.out.println("k is invalid! Please, enter k (number between 1 and 119): ");
			k = s.nextInt();
		}
		s.close();
		for(int i = 1; i < 11; i++)
		{
			System.out.println("Test " + i + " begins.");
			try 
			{
				KNN.solve(k);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
