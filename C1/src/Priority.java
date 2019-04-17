import java.io.*;
import java.util.*;
class Process1 
{ 
    int pid; 
    int bt; 
    int pri; 
      
    public Process1(int pid, int bt, int pri) 
    { 
        this.pid = pid; 
        this.bt = bt; 
        this.pri = pri; 
    } 
} 
public class Priority 
{
	static void findWaitingTime(Process1 proc[], int n,int wt[]) 
	{
		wt[0] = 0; 
		for (int  i = 1; i < n ; i++ ) 
			wt[i] =  proc[i-1].bt + wt[i-1] ; 
	} 

	static void findTurnAroundTime( Process1 proc[], int n, int wt[], int tat[]) 
	{ 
		for (int  i = 0; i < n ; i++) 
			tat[i] = proc[i].bt + wt[i]; 
	}
	 
	boolean comparison(Process1 a, Process1 b) 
	{ 
	    return (a.pri > b.pri); 
	}
	
	static void findavgTime(Process1 proc[], int n) 
	{ 
	    int wt[] = new int[n];
	    int tat[] = new int[n];
	    int total_wt = 0, total_tat = 0; 
	    
	    findWaitingTime(proc, n, wt); 
	    findTurnAroundTime(proc, n, wt, tat); 
	    System.out.println( "\nProcesses  "+" Burst time  "+ " Waiting time  "+" Turn around time\n"); 
	  
	    for (int  i=0; i<n; i++) 
	    { 
	        total_wt = total_wt + wt[i]; 
	        total_tat = total_tat + tat[i]; 
	        System.out.println( "   " + proc[i].pid + "\t\t"+ proc[i].bt + "\t    " + wt[i] +"\t\t  " +tat[i]); 
	    } 
	  
	    System.out.println( "\nAverage waiting time = "+ (float)total_wt / (float)n); 
	    System.out.println( "\nAverage turn around time = "+ (float)total_tat / (float)n); 
	} 
	public static void main (String args[])throws IOException
	{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of processes");
		int n = Integer.parseInt(br.readLine());
		Process1 proc[] = new Process1[n];
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter process id, burst time and priority");
			/*proc[i].pid = Integer.parseInt(br.readLine());
			proc[i].bt = Integer.parseInt(br.readLine());
			proc[i].art = Integer.parseInt(br.readLine());*/
			
			int pid = sc.nextInt();
			int bt = sc.nextInt();
			int pri = sc.nextInt();
			proc[i]=new Process1(pid,bt,pri);
		}
		
		findavgTime(proc,n );
	}
}
