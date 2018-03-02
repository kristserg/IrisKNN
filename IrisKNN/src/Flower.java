
public class Flower implements Comparable<Flower>
{
	private double sepalLength;
	private double sepalWidth;
	private double petalLength;
	private double petalWidth;
	private String type;
	private double md;
	
	public Flower(double sl, double sw, double pl, double pw)
	{
		this.sepalLength = sl;
		this.sepalWidth = sw;
		this.petalLength = pl;
		this.petalWidth = pw;
	}
	
	public Flower(double sl, double sw, double pl, double pw, String t)
	{
		this.sepalLength = sl;
		this.sepalWidth = sw;
		this.petalLength = pl;
		this.petalWidth = pw;
		this.type = t;
	}
	
	public double getSepalLength()
	{
		return this.sepalLength;
	}
	
	public double getSepalWidth()
	{
		return this.sepalWidth;
	}
	
	public double getPetalLength()
	{
		return this.petalLength;
	}
	
	public double getPetalWidth()
	{
		return this.petalWidth;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public double getDist()
	{
		return this.md;
	}
	
	public void setType(String t)
	{
		this.type = t;
	}
	
	public void setMd(double md)
	{
		this.md = md;
	}

	@Override
	public int compareTo(Flower f1)
	{
		double a = this.getDist();
		double b = f1.getDist();
		if(a > b)
		{
			return 1;
		}
		if(a < b)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

	/*public void ManhattanDist(Flower f1) 
	{
		this.md = Math.abs(this.getSepalLength() - f1.getSepalLength()) + Math.abs(this.getSepalWidth() - f1.getSepalWidth())
				+ Math.abs(this.getPetalLength() - f1.getPetalLength()) + Math.abs(this.getPetalWidth() - f1.getPetalWidth());
		System.out.println("Dist: " + this.md);
	}*/
}
