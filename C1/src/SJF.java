import java.io.*;
import java.util.*;
class Process 
{ 
    int pid;
    int bt; 
    int art;
    BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
    public Process(int pid, int bt, int art) 
    { 
        this.pid = pid; 
        this.bt = bt; 
        this.art = art; 
    } 
    
    public void accept() throws NumberFormatException, IOException
    {
    	this.pid=Integer.parseInt(br.readLine());
		this.bt=Integer.parseInt(br.readLine());
		this.art=Integer.parseInt(br.readLine());
    }
} 
public class SJF 
{
	static void findWaitingTime(Process proc[], int n, int wt[]) 
	{ 
		int rt[] = new int[n]; 
		for (int i = 0; i < n; i++) 
			rt[i] = proc[i].bt; 

		int complete = 0, t = 0, minm = Integer.MAX_VALUE; 
		int shortest = 0, finish_time; 
		boolean check = false; 

		while (complete != n) 
		{
			for (int j = 0; j < n; j++)  
			{ 
				if ((proc[j].art <= t) && (rt[j] < minm) && rt[j] > 0) 
				{ 
					minm = rt[j]; 
					shortest = j; 
					check = true; 
				} 
			} 

			if (check == false) 
			{ 
				t++; 
				continue; 
			} 
 
			rt[shortest]--; 
			minm = rt[shortest]; 
			if (minm == 0) 
				minm = Integer.MAX_VALUE; 

			if (rt[shortest] == 0) 
			{ 			
				complete++; 
				check = false; 
				finish_time = t + 1;
				wt[shortest] = finish_time - proc[shortest].bt - proc[shortest].art; 

				if (wt[shortest] < 0) 
					wt[shortest] = 0; 
			} 
			t++; 
		} 
	} 

	static void findTurnAroundTime(Process proc[], int n, int wt[], int tat[]) 
	{  
		// bt[i] + wt[i] 
		for (int i = 0; i < n; i++) 
			tat[i] = proc[i].bt + wt[i]; 
	} 

	static void findavgTime(Process proc[], int n) 
	{ 
		int wt[] = new int[n], tat[] = new int[n]; 
		int  total_wt = 0, total_tat = 0; 
 
		findWaitingTime(proc, n, wt); 
		findTurnAroundTime(proc, n, wt, tat); 

		System.out.println("Processes " +  " Burst time " +  " Waiting time "+  " Arrival time " +  " Turn around time"); 

		for (int i = 0; i < n; i++) 
		{ 
			total_wt = total_wt + wt[i]; 
			total_tat = total_tat + tat[i]; 
			System.out.println(" " + proc[i].pid + "\t\t" + proc[i].bt + "\t\t " + wt[i] + "\t\t" + proc[i].art+ "\t\t" + tat[i]); 
		} 

		System.out.println("Average waiting time = " + (float)total_wt / (float)n); 
		System.out.println("Average turn around time = " + (float)total_tat / (float)n); 
	} 

	public static void main(String[] args) throws IOException
	{ 
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of processes");
		int n = Integer.parseInt(br.readLine());
		Process proc[] = new Process[n];
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter process id, burst time and arrival time");
			/*proc[i].pid = Integer.parseInt(br.readLine());
			proc[i].bt = Integer.parseInt(br.readLine());
			proc[i].art = Integer.parseInt(br.readLine());*/
			
			int pid = sc.nextInt();
			int bt = sc.nextInt();
			int art = sc.nextInt();
			proc[i]=new Process(pid,bt,art);
		}
		
		findavgTime(proc,n ); 
	} 
}
