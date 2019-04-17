import java.io.*;
import java.util.*;
public class mntf 
{
	String name;
	public int kpdtp, mdtp,pntp;
	mntf()
	{
		name="";
		kpdtp=0;
		mdtp=0;
	}
	public void setName(String name)
	{
		this.name =name;
	}
	mntf(String name, int kpdtb, int mdtp)
	{
		this.name=name;
		this.kpdtp = kpdtb;
		this.mdtp=mdtp;
	}
	public void setPp(int pp) 
	{
		this.pntp = pp;
		
	}
	public void setKp(int kp) 
	{
		this.kpdtp=kp;
		
	}
	public String getName() 
	{
		return this.name;
	}
	public int getKpdtp() 
	{
		return this.kpdtp;
	}
	public int getPp() 
	{
		return this.pntp;
	}
	public int getMdtp() 
	{
		return this.mdtp;
	}
	public int getKp() 
	{
		return this.kpdtp;
	}
	
}
