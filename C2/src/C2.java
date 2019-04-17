import java.io.*;
public class C2 
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/*System.out.println("Enter number of processes");
		int n = Integer.parseInt(br.readLine());
		System.out.println("Enter number of resources");
		int m = Integer.parseInt(br.readLine());*/
		int n=5,m=3;
		
		int need[][]=new int[n][m];
		int max[][] = new int[n][m];
		int allocation[][]=new int[n][m];
		int available[] = new int[m];
		int work[]= new int[m];
		boolean finish[] = new boolean[n];
		
		available= new int[]{3,3,2};
		work= new int[]{3,3,2};
		max = new int[][] { { 7, 5, 3 }, //P0 
            { 3, 2, 2 }, //P1 
            { 9, 0, 2 }, //P2 
            { 2, 2, 2 }, //P3  
            { 4, 3, 3 } };
            
         allocation = new int[][] { { 0, 1, 0 }, //P0    
                { 2, 0, 0 }, //P1 
                { 3, 0, 2 }, //P2 
                { 2, 1, 1 }, //P3 
                { 0, 0, 2 } }; //P4 
		
		/*System.out.println("Enter available resources");
		for(int i=0;i<m;i++)
		{
			available[i]=Integer.parseInt(br.readLine());
			work[i]=available[i];
		}
		
		System.out.println("Enter maximum resource matrix");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				max[i][j]=Integer.parseInt(br.readLine());
			}
		}
		
		System.out.println("Enter allocation resource matrix");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				allocation[i][j]=Integer.parseInt(br.readLine());
			}
		}*/
		
		System.out.println("Need matrix calculated");
		
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				need[i][j]=max[i][j] - allocation[i][j];
				System.out.print(need[i][j]);
			}
			System.out.println();
		}
		
		for(int i=0;i<n;i++)
			finish[i]= false;
		
		int safeseq[] = new int[n];
		int count=0;
		
		while(count<n)
		{
			//System.out.print("here");
			boolean found=false;
			for(int i=0;i<n;i++)
			{
				//System.out.print("here2");
				if(finish[i] == false)
				{
					int j=0;
					for(j=0;j<m;j++)
					{
						if(need[i][j]> work[j])
							break;
					}
					if(j==m)
					{
						for(int k=0;k<m;k++)
							work[k]+= allocation[i][k];
						
						safeseq[count]= i;
						count++;
						finish[i]=true;
						found = true;
					}
				}
			}
			if(found == false)
			{
				System.out.println("Deadlock");
				break;
			}
				
		}
		if(count < n)
			System.out.println("Unsafe");
		else
		{
			System.out.println("Safe Sequence");
			for(int i=0;i<n;i++)
				System.out.print(safeseq[i]+"-->");
		}
		
	}
}
