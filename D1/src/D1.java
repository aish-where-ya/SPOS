import java.io.*;
import java.util.*;
public class D1 
{
	public void lru(int arr[], int nframes)
	{
		int num = arr.length;
		int hit=0,miss=0;
		ArrayList<Integer> frame = new ArrayList<>(nframes);
		ArrayList<Integer> index = new ArrayList<>(nframes);
		//System.out.println(frame.size());
		for(int i=0;i<num;i++)
		{
			if (!frame.contains(arr[i]))
			{
				if(frame.size()<nframes)
				{
					frame.add(arr[i]);
					index.add(i);
				}
				else
				{
					int min = getMin(index);
					frame.remove(min);
					frame.add(min,arr[i]);
					index.remove(min);
					index.add(min,i);
				}
				miss++;
			}
			else
			{
				if(frame.size()<nframes)
				{
					int a = frame.indexOf(arr[i]);
					index.remove(a);
					index.add(a,i);
				}
				else
				{
					int a = frame.indexOf(arr[i]);
					index.remove(a);
					index.add(a,i);
				}
				hit++;
			}
			for(Integer elem : frame)
			{
				System.out.print(elem);
			}
			System.out.println();
		}
		System.out.println("Hits : "+hit);
		System.out.println("Miss : "+miss);
	}
	
	public int getMin(ArrayList<Integer> index)
	{
		int min = Integer.MAX_VALUE;
		for(int i=0;i<index.size();i++) 
		{
			int val = index.get(i);
			if(val<min)
				min=val;
		}
		return index.indexOf(min);
	}
	
	public void optimal(int arr[], int nframes)
	{
		ArrayList<Integer> frame = new ArrayList<>(nframes);
		ArrayList<Integer> index = new ArrayList<>(nframes);
		int hit=0, miss=0;
		int num = arr.length;
		for(int i=0;i<num;i++)
		{
			if(!frame.contains(arr[i]))
			{
				if(frame.size()<nframes)
				{
					int nextAcc = getNextAccess(arr,i);//frame.subList(i+1, num).indexOf(arr[i]);
					
					frame.add(arr[i]);
					index.add(nextAcc);
				}
				else
				{
					int nextAcc = getNextAccess(arr,i);//frame.subList(i+1, num).indexOf(arr[i]);
					
					int max = index.indexOf(getMax(index));
					frame.remove(max);
					frame.add(max, arr[i]);
					index.remove(max);
					index.add(max,nextAcc);
				}
				miss++;
			}
			else
			{
				if(frame.size()<nframes)
				{
					int nextAcc = getNextAccess(arr,i);//frame.subList(i+1, num).indexOf(arr[i]);
					
					int ind = frame.indexOf(arr[i]);
					index.remove(ind);
					index.add(ind,nextAcc);
				}
				else
				{
					int nextAcc = getNextAccess(arr,i);//frame.subList(i+1, num).indexOf(arr[i]);
					
					int ind = frame.indexOf(arr[i]);
					index.remove(ind);
					index.add(ind,nextAcc);
				}
				hit++;
			}
			
			for(Integer elem : frame)
				System.out.println(elem);
			System.out.println();			
		}
		System.out.println("Hits : "+hit);
		System.out.println("Miss : "+miss);
	}	

	public int getNextAccess(int arr[], int cPos)
	{
		int elem= arr[cPos];
		for(int i=cPos+1;i<arr.length;i++)
		{
			if(arr[i] == elem)
				return i;
		}
		return Integer.MAX_VALUE;
	}
	
	public int getMax(ArrayList<Integer> index)
	{
		int max= Integer.MIN_VALUE;
		int val;
		for(int i=0;i<index.size();i++)
		{
			val = index.get(i);
			if(val> max)
				max= val;
		}
		return max;
	}
	
	public void fifo(int arr[], int nframes)
	{
		
	}
	public static void main (String args[])throws IOException
	{
		D1 obj = new D1();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter number of pages");
		int num = Integer.parseInt(br.readLine());
		System.out.println("Enter reference string ");
		int arr[] =new int[num];
		for(int i=0;i<num;i++)
			arr[i]= Integer.parseInt(br.readLine());
		System.out.println("Enter number of frames");
		int nframes= Integer.parseInt(br.readLine());
		System.out.println("Enter choice \n 1. lru\n2. optimal\n3. fifo");
		int choice = Integer.parseInt(br.readLine());
		
		switch(choice)
		{
		case 1:
			obj.lru(arr,nframes);
			break;
		
		case 2:
			obj.optimal(arr,nframes);
			break;
			
		case 3:
			obj.fifo(arr,nframes);
			break;
			
			default:
				System.out.println("Wrong choice");
				break;
		}
	}

}
