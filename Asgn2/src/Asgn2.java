import java.io.*;
import java.util.*;
public class Asgn2 
{
	ArrayList<Integer> litaddr = new ArrayList<>();
	ArrayList<String> littab = new ArrayList<>();
	ArrayList<Integer> symaddr = new ArrayList<>();
	ArrayList<Integer> pooltab = new ArrayList<>();
	int lc=0;
	void readFiles()throws IOException
	{
		BufferedReader br2 = new BufferedReader(new FileReader("sym.txt"));
		BufferedReader br3 = new BufferedReader(new FileReader("lit.txt"));
		BufferedReader br4 = new BufferedReader(new FileReader("pool.txt"));
		
		String line="";
		while((line=br2.readLine())!=null)
		{
			String split[]=line.split("\\s+");
			symaddr.add(Integer.parseInt(split[0]));
		}
		while((line=br3.readLine())!=null)
		{
			String split[]=line.split("\\s+");
			litaddr.add(Integer.parseInt(split[0]));
		}
		while((line=br4.readLine())!=null)
		{
			//String split[]=line.split("\\s+");
			pooltab.add(Integer.parseInt(line));
		}
		
		br2.close();
		br3.close();
		br4.close();
	}
	
	void generateMC() throws IOException
	{
		BufferedReader br1 = new BufferedReader(new FileReader("output.txt"));
		
		//BufferedWriter bw = new BufferedWriter (new FileWriter ("var2.txt"));
		
		String line="";
		while((line = br1.readLine())!=null)
		{
			String split[]= line.split("\\s+");
			String arr[]= split[0].replace("(", "").replace(")", "").split("\\,");
			if(arr[0].equals("AD") && arr[1].equals("1"))
			{
				String arr2[]= split[1].replace("(", "").replace(")", "").split("\\,");
				lc = Integer.parseInt(arr2[1]);
			}
			else if(arr[0].equals("DL"))
			{
				String arr2[]=split[1].replace("(", "").replace(")", "").split("\\,");
				if(arr[1].equals("2"))
				{
					System.out.println("00 00 "+String.format("%03d",Integer.parseInt(arr2[1])) );
				}
			}
			else if(arr[0].equals("IS"))
			{
				System.out.print(String.format("%02d",Integer.parseInt(arr[1]))+" ");
				if(split.length == 1)
					System.out.print("00 000");
				for(int i=1;i<split.length;i++)
				{
					String arr2[]=split[i].replace("(", "").replace(")", "").split("\\,");
					if(split.length == 2)
						System.out.print("00 ");
					if(arr2[0].equals("L"))
					{
						System.out.print(litaddr.get(Integer.parseInt(arr2[1]))+" ");
					}
					else if(arr2[0].equals("S"))
					{
						System.out.print(symaddr.get(Integer.parseInt(arr2[1]))+" ");
					}
					else if(arr2[0].equals("C"))
					{
						System.out.print(Integer.parseInt(arr2[1])+" ");
					}
					else
						System.out.print("0"+arr2[0]+" ");
				}
				System.out.println();
								
			}
		}
		br1.close();
		
	}
	public static void main(String args[])throws IOException
	{
		Asgn2 obj = new Asgn2();
		obj.readFiles();
		obj.generateMC();
	}
}
