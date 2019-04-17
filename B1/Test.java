import java.io.*;
public class Test
{
	public native int add(int n1,int n2);
	public native int sub(int n1,int n2);
	public static void main(String args[])throws IOException
	{
		Test t =new Test();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("1. Add 2. Subtract \n Enter your choice:");
		int ch = Integer.parseInt(br.readLine());
		switch(ch)
		{
			case 1:
			System.out.println("Enter 2 nos.");
			int n1= Integer.parseInt(br.readLine());
			int n2= Integer.parseInt(br.readLine());
			System.out.println("Addition is : "+ t.add(n1,n2));
			break;
			
			case 2:
			System.out.println("Enter 2 nos.");
			int m1= Integer.parseInt(br.readLine());
			int m2= Integer.parseInt(br.readLine());
			System.out.println("Difference is : "+ t.sub(m1,m2));
			break;
			
			default:
			System.out.println("wrong choice");
			break;
		}
	}
	static
	{
	System.loadLibrary("Test");
	}
}
