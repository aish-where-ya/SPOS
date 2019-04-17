import java.io.*;
import java.util.*;

public class C1 
{
	static BufferedReader br  = new BufferedReader (new InputStreamReader(System.in));
	public static void main(String args[]) throws IOException
	{
		
		C1 obj = new C1();
		int ch;
		
		System.out.println("Enter number of processes");
		int n=Integer.parseInt(br.readLine());
		int pr[] = new int[n];
		int bt[]=new int[n];
		int i=0;
		for(i=0;i<n;i++)
		{
			System.out.println("Enter processes and their burst/waiting time");
			pr[i] = Integer.parseInt(br.readLine());
			bt[i] = Integer.parseInt(br.readLine());			
		}
		obj.fcfs(pr,bt,n);
		/*System.out.println("Enter choice \n1. FCFS\n2. SJF\n3. Priority\n4. Round Robin");
		ch=Integer.parseInt(br.readLine());
		switch(ch)
		{
		case 1:
			System.out.println("FCFS");
			obj.fcfs(pr,bt,n);
			break;
			
		case 2:
			System.out.println("SJF");
			obj.sjf(pr,bt,n);
			break;
			
			default:
				System.out.println("Wrong choice");
		}*/
	}
	
	public void fcfs(int pr[], int bt[], int n)throws IOException
	{		
		int i;
		for(i=0;i<n;i++)
			System.out.println(pr[i]+", ");
		for(i=0;i<n;i++)
			System.out.println(bt[i]+", ");
		
		int wt[] = new int[n];
		i=1;
		wt[0]=0;
		System.out.println("Waiting time");
		System.out.print("0, ");
		for(i=1;i<n;i++)
		{
			wt[i] = wt[i-1]+bt[i-1];
			System.out.print(wt[i]+", ");
			
		}
		System.out.println();
		
		int tt[]=new int[n];		
		System.out.println("Turnaround time");
		for(i=0;i<n;i++)
		{
			tt[i]=wt[i]+bt[i];
			System.out.print(tt[i]+", ");
		}
		System.out.println();
		
		int twt=0,ttt=0;
		for(i=0;i<n;i++)
		{
			twt+=wt[i];
			ttt+=tt[i];
		}
		float avgwt= twt/n;
		float avgtt = ttt/n;
		
		System.out.println("Average waiting time "+avgwt);
		System.out.println("Average turnaround time "+avgtt);
		
	}
	/*public void sjf(int pr[],int bt[], int n)throws IOException
	{
		int i;
		for(i=0;i<n;i++)
			System.out.println(pr[i]+", ");
		for(i=0;i<n;i++)
			System.out.println(bt[i]+", ");
		
		while(true)
		{
			
		}
	}*/
}
