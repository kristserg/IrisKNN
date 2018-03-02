import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;


public class KNN 
{	
	static void ManhattanDist(Flower f1, Flower f2) 
	{
		f1.setMd(Math.abs(f1.getSepalLength() - f2.getSepalLength()) + Math.abs(f1.getSepalWidth() - f2.getSepalWidth())
				+ Math.abs(f1.getPetalLength() - f2.getPetalLength()) + Math.abs(f1.getPetalWidth() - f2.getPetalWidth()));
		//System.out.println("Dist: " + f1.md);
	}
	
	public static void solve(int k) throws FileNotFoundException, IOException
	{
		File file = new File("F:/Java/workspace/IrisKNN/src/Iris.txt");
		Flower[] flowers = new Flower[150];
		Flower[] forTesting = new Flower[150];
		Flower[] forTraining = new Flower[150];
		int index = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) 
		{
		    String line;
		    while ((line = br.readLine()) != null) 
		    {
		       String[] data = line.split(",");
		       double sl = Double.parseDouble(data[0]);
		       double sw = Double.parseDouble(data[1]);
		       double pl = Double.parseDouble(data[2]);
		       double pw = Double.parseDouble(data[3]);
		       Flower f1 = new Flower(sl, sw, pl, pw, data[4]);
		       Flower f2 = new Flower(sl, sw, pl, pw, data[4]);
		       Flower forTest = new Flower(sl, sw, pl, pw);
		       flowers[index] = f1;
		       forTesting[index] = forTest;
		       forTraining[index] = f2;
		       index++;
		    }
		}
		
		Random r = new Random();
		Flower[] testing = new Flower[30]; //20 % for testing
		List<Integer> indexes = new ArrayList<Integer>();
		
		for(int i = 0; i < 30; i++)
		{
			index = r.nextInt(150);
			while(indexes.contains(index))
			{
				index = r.nextInt(150);
			}
			indexes.add(index);
			testing[i] = forTesting[index];
			forTraining[index] = null;
		}
		
		int correctGuesses = 0;
		
		for(int c = 0; c < 30; c++)
		{
			Flower chosen = testing[c];
			PriorityQueue<Flower> pq = new PriorityQueue<Flower>();
			
			for(int i = 0; i < 150; i++)
			{
				if(forTraining[i] != null)
				{
					ManhattanDist(forTraining[i], chosen);
					pq.add(forTraining[i]);
				}
			}
			
			int countSetosa = 0;
			int countVersicolor = 0;
			int countVirginica = 0;
			
			for(int i = 0; i < k; i++)
			{
				Flower fl = pq.poll();
				if(fl.getType().equals("Iris-setosa"))
				{
					countSetosa++;
				}
				else if(fl.getType().equals("Iris-versicolor"))
				{
					countVersicolor++;
				}
				else if(fl.getType().equals("Iris-virginica"))
				{
					countVirginica++;
				}
			}
			
			/*System.out.println("Setosa: " + countSetosa);
			System.out.println("Versicolor: " + countVersicolor);
			System.out.println("Virginica: " + countVirginica);*/
			
			if(countSetosa >= countVersicolor && countSetosa >= countVirginica)
			{
				chosen.setType("Iris-setosa");
			}
			
			else if(countVersicolor >= countSetosa && countVersicolor >= countVirginica)
			{
				chosen.setType("Iris-versicolor");
			}
			
			else if(countVirginica >= countVersicolor && countVirginica >= countSetosa)
			{
				chosen.setType("Iris-virginica");
			}
			/*System.out.println("chosen: " + "SL: " + chosen.getSepalLength() + " SW: " + chosen.getSepalWidth() +
					" PL: " + chosen.getPetalLength() + " PW: " + chosen.getPetalWidth() + 
					" T: " + chosen.getType());
			System.out.println("real: " + "SL: " + flowers[indexes.get(c)].getSepalLength() + " SW: " + 
					flowers[indexes.get(c)].getSepalWidth() + " PL: " + flowers[indexes.get(c)].getPetalLength() + 
					" PW: " + flowers[indexes.get(c)].getPetalWidth() + " T: " + flowers[indexes.get(c)].getType());*/
			if(chosen.getType().equals(flowers[indexes.get(c)].getType()))
			{
				correctGuesses++;
			}
			while(!pq.isEmpty())
			{
				pq.poll();
			}
		}

		System.out.println("Percent of correct guesses: " + (correctGuesses / 30.0) * 100);
	}
}
