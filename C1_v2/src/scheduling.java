
import java.util.*;
public class scheduling {

	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		scheduling s = new scheduling();
		System.out.println("\n\n1.FCFS\n2.SJF\n3.Priority\n4.RR\nEnter The Choice:\n");
		Scanner sc = new Scanner(System.in);
		int ch = sc.nextInt();
		process p = new process(1,5,5,3);
 		process p1 = new process(2,4,6,4);
 		process p2 = new process(3,3,7,1);
 		process p3 = new process(4,1,9,7);
 		process p4 = new process(5,2,2,8);
 		process p5 = new process(6,6,3,8);
 		
 		List<process> pro = new ArrayList<>();
 		pro.add(p);
 		pro.add(p1);
 		pro.add(p2);
 		pro.add(p3);
 		pro.add(p4);
 		pro.add(p5);
		switch(ch)
		{
		case 1:
			FCFS f= new FCFS();
			f.process(pro);
			break;
		case 2:
			SJF sj = new SJF();
			sj.process();
			break;
		case 3:
			priority pri = new priority();
			pri.process(pro);
			break;
		case 4:
			round ro = new round();
			ro.process(pro);
			break;
			
		}
	}

}
class round
{
	public void process(List<process> pro)
	{
		
		System.out.println("\n\n1.With Arrival Time\n2.Without Arrival Time\nEnter The Choice: ");
		 Scanner sc = new Scanner(System.in);
		 int ch = sc.nextInt();
		 switch(ch)
		 {
		 	case 1:
		 		
		 		with(pro);
		 		break;
		 	case 2:
		 		without(pro);
		 		
		 		break;
		 }
	 }

	private void without(List<process> pro) {
		
		round r = new round();
		//Collections.sort(pro,r.arr);
		int[] wt = waitingTime(pro);
		int[] tt = new int[pro.size()];
		Arrays.fill(tt,0);
		System.out.println("\nProcess\tWaiting time\tTurnaround Time\n");
		
		for(int i=0;i<pro.size();i++)
		{
			tt[i] = wt[i]+pro.get(i).etime;
			System.out.println("P"+pro.get(i).pid+"\t\t"+wt[i]+"\t\t"+tt[i]);
		}
		
		
	}

	private int[] waitingTime(List<process> pro) {
		int timeSlice=2;
		int total=0;
		int[] rt = new int[pro.size()+1];
		Arrays.fill(rt,0);
		int[] wt = new int[pro.size()+1];
		Arrays.fill(wt,0);
		for(int i=0;i<pro.size();i++)
		{
			
			rt[i] = pro.get(i).etime;
		}
		
		while(true)
		{
			boolean finish=true;
			for(int i=0;i<pro.size();i++)
			{
				if(rt[i]>0)
				{
					finish=false;
					if(rt[i]>timeSlice)
					{
						
						total+=timeSlice;
						rt[i]-=timeSlice;
					}
					else
					{
						total=total+rt[i];
						rt[i]=0;
						wt[i] = total-pro.get(i).etime;
					}
				}
				
			}
			
			if(finish==true)
			{
				break;
			}
		}
		return wt;
		
	}

	private void with(List<process> pro) {
		

		round r = new round();
		Collections.sort(pro,r.arr);
		int[] wt = waitingTime(pro);
		int[] tt = new int[pro.size()];
		Arrays.fill(tt,0);
		System.out.println("\nProcess\tWaiting time\tTurnaround Time\n");
		
		for(int i=0;i<pro.size();i++)
		{
			tt[i] = wt[i]+pro.get(i).etime;
			System.out.println("P"+pro.get(i).pid+"\t\t"+wt[i]+"\t\t"+tt[i]);
		}
		
		
	}
	
	public static Comparator<process> arr = new Comparator<process>()
			{

				@Override
				public int compare(process arg0, process arg1) {
					// TODO Auto-generated method stub
					return arg0.atime-arg1.atime;
				}
			
			};
}
class priority
{
	public void process(List<process> pro)
	{
		
		System.out.println("\n\n1.With Arrival Time\n2.Without Arrival Time\nEnter The Choice: ");
		 Scanner sc = new Scanner(System.in);
		 int ch = sc.nextInt();
		 switch(ch)
		 {
		 	case 1:
		 		
		 		with(pro);
		 		break;
		 	case 2:
		 		without(pro);
		 		
		 		break;
		 }
	 }

	private void with(List<process> pro) {
		priority p = new priority();
		Collections.sort(pro,p.arr);
		int st[] = new int[pro.size()+1];
		int ct[] = new int[pro.size()+1];
		int wt[] = new int[pro.size()+1];
		int tt[] = new int[pro.size()+1];
		
		Arrays.fill(st, 0);
		Arrays.fill(ct, 0);
		Arrays.fill(wt, 0);
		Arrays.fill(tt, 0);
		int pa = pro.get(0).atime;
		for(int i=0;i<pro.size();i++)
		{
				
				st[pro.get(i).pid] = pa;
				ct[pro.get(i).pid] = st[pro.get(i).pid]+pro.get(i).etime;
				pa = ct[pro.get(i).pid];
		}
		System.out.println("\nProcess\tStart Time\tCompletion Time\tWaiting time\tTurnaround Time\n");
		for(int i=0;i<pro.size();i++)
		{
			wt[pro.get(i).pid] = st[pro.get(i).pid]-pro.get(i).atime;
			tt[pro.get(i).pid] = wt[pro.get(i).pid]+pro.get(i).etime;
			
			
			System.out.println("P"+pro.get(i).pid+"\t\t"+st[pro.get(i).pid]+"\t\t"+ct[pro.get(i).pid]+"\t\t"+wt[pro.get(i).pid]+"\t\t"+tt[pro.get(i).pid]);
		}
		
		
		
		
	}
	
	private void without(List<process> pro) {
		priority p = new priority();
		Collections.sort(pro,p.prio);
		
		int st[] = new int[pro.size()+1];
		int ct[] = new int[pro.size()+1];
		int wt[] = new int[pro.size()+1];
		int tt[] = new int[pro.size()+1];
		
		Arrays.fill(st, 0);
		Arrays.fill(ct, 0);
		Arrays.fill(wt, 0);
		Arrays.fill(tt, 0);
		int pa = 0;
		for(int i=0;i<pro.size();i++)
		{
				
				st[pro.get(i).pid] = pa;
				ct[pro.get(i).pid] = st[pro.get(i).pid]+pro.get(i).etime;
				pa = ct[pro.get(i).pid];
		}
		
		System.out.println("\nProcess\tStart Time\tCompletion Time\tWaiting time\tTurnaround Time\n");
		for(int i=0;i<pro.size();i++)
		{
			wt[pro.get(i).pid] = st[pro.get(i).pid];
			tt[pro.get(i).pid] = wt[pro.get(i).pid]+pro.get(i).etime;
			
			System.out.println("P"+pro.get(i).pid+"\t\t"+st[pro.get(i).pid]+"\t\t"+ct[pro.get(i).pid]+"\t\t"+wt[pro.get(i).pid]+"\t\t"+tt[pro.get(i).pid]);
		}
	}
	
	public static Comparator<process> arr = new Comparator<process>()
	{

		@Override
		public int compare(process arg0, process arg1) {
			// TODO Auto-generated method stub
			return arg0.atime-arg1.atime;
		}
	
	};
	
	public static Comparator<process> prio = new Comparator<process>()
	{

		@Override
		public int compare(process arg0, process arg1) {
			// TODO Auto-generated method stub
			return arg0.pri-arg1.pri;
		}
	
	};
}
class FCFS
{
	public void process(List<process> d)
	{
		FCFS f= new FCFS();
		Collections.sort(d,f.prio);
		System.out.println("\nProcess\tArrival Time\tExecution Time\tWaiting\tTurnaround\n");
		for(int i=0;i<d.size();i++)
		{
			int wait = (d.get(i).etime-d.get(i).atime);
			if(wait<0) wait=0;
			System.out.println("P"+d.get(i).pid+"\t\t"+d.get(i).atime+ "\t\t"+d.get(i).etime+"\t"+wait+"\t"+wait);
		}
		
		 
		 
	}
	
	public static Comparator<process> prio = new Comparator<process>()
	{

		@Override
		public int compare(process arg0, process arg1) {
			// TODO Auto-generated method stub
			return arg0.atime-arg1.atime;
		}
	
	};
}

class SJF
{
	 public void process()
	 {
		 System.out.println("\n\n1.With Arrival Time\n2.Without Arrival Time\nEnter The Choice: ");
		 Scanner sc = new Scanner(System.in);
		 int ch = sc.nextInt();
		 SJF s = new SJF();
		 process p = new process(1,2,3,4);
	 		process p1 = new process(2,7,4,3);
	 		process p2 = new process(3,6,5,2);
	 		process p3 = new process(4,4,2,5);
	 		process p4 = new process(5,0,7,1);
	 		List<process> pro = new ArrayList<>();
	 		pro.add(p);
	 		pro.add(p1);
	 		pro.add(p2);
	 		pro.add(p3);
	 		pro.add(p4);
		 switch(ch)
		 {
		 	case 1:
		 		
		 		with(pro);
		 		break;
		 	case 2:
		 		without(pro,s);
		 		
		 		break;
		 }
	 }
	 
	 
	 public void with(List<process> p)
	 {
		 
		 
		 int wt[] = waitingTime(p);
		// System.out.println(wt);
		 int tt[] = new int[p.size()];
		 
		 for(int i=0;i<p.size();i++)
		 {
			 //System.out.println(wt[i]);
			 tt[i] = wt[i]+p.get(i).etime;
		 }
		 System.out.println("\nProcess\tWaiting\tTurnaround\n");
		 //System.out.println("\nP1\nP2\nP3\nP4\nP5\n");
		 for(int i=0;i<p.size();i++)
		 {
			 System.out.print("P"+(i+1)+"\t"+wt[i]);
			 System.out.print("\t"+tt[i]+"\n");
			 
		 }
		 
	 }
	 private int[] waitingTime(List<process> p) {
		 int[] bt = new int[p.size()];
		 for(int i=0;i<p.size();i++)
		 {
			 bt[i] = p.get(i).etime;
		 }
		 int min = Integer.MAX_VALUE,shortest=0,t=0;
		 boolean check = false;
		 int complete=0;
		 int finish=0,waiting=0;
		 int[] wt = new int[p.size()];
		 while(complete!=p.size())
		 {
			 for(int i=0;i<p.size();i++)
			 {
				 
				 if(p.get(i).atime<=t && bt[i]<min && bt[i]>0)
				 {
					 min = bt[i];
					 shortest=i;
					 check=true;
				 }
				 
				 if(check==false)
				 {
					 t++;
					 continue;
				 }
				 
				 bt[i]--;
				 
				 min = bt[shortest];
				 if(min==0)
				 {
					 min = Integer.MAX_VALUE; 
				 }
				 
				 if(bt[shortest]==0)
				 {
					 complete++;
					 finish = t+1;
					 wt[shortest] = finish-p.get(shortest).atime-p.get(shortest).etime;
					 if(wt[shortest]<0)
					 {
						 wt[shortest]=0;
					 }
				 }
				 t++;
				
			 }
		 }
		 return wt;
	}

	public void without(List<process> p,SJF s)
	 {
		 Collections.sort(p,s.arr);
		 for(int i=0;i<p.size();i++)
		 {
			 System.out.println("P"+(i+1)+"\t"+p.get(i).etime);
			
			 
		 }
		 
	 }


	public static Comparator<process> arr = new Comparator<process>()
			{

				@Override
				public int compare(process arg0, process arg1) {
					// TODO Auto-generated method stub
					return arg0.etime-arg1.etime;
				}
			
			};
	
}
class process 
{
	int atime;
	int etime;
	int pri;
	int pid;
	public process(int pid,int atime, int etime, int pri) {
		super();
		this.pid=pid;
		this.atime = atime;
		this.etime = etime;
		this.pri = pri;
	}
	
	
}