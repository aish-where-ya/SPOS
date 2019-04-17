import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class initialize
{
	Map<String,Integer> reg = new LinkedHashMap<>();
	Map<String,Integer> condCode = new LinkedHashMap<>();
	Map<String,optab> opTab = new LinkedHashMap<>();
	
	
	public Map<String, Integer> getReg() {
		reg.put("AREG",1);
		reg.put("BREG",2);
		reg.put("CREG",3);
		reg.put("DREG",4);
		return reg;
	}

	public void setReg(LinkedHashMap<String, Integer> reg) {
		this.reg = reg;
	}

	public Map<String, Integer> getCondCode() {
		condCode.put("LT", 1);
		condCode.put("LE", 2);
		condCode.put("EQ", 3);
		condCode.put("GT", 4);
		condCode.put("GE", 5);
		condCode.put("ANY", 6);
		return condCode;
	}
	
	public Map<String,optab> getOpcode()
	{
		optab op1 = new optab("STOP","IS",0);
		opTab.put(op1.getMnemonic(), op1);
		optab op2 = new optab("ADD","IS",1);
		opTab.put(op2.getMnemonic(), op2);
		optab op3 = new optab("SUB","IS",2);
		opTab.put(op3.getMnemonic(), op3);
		optab op4 = new optab("MULT","IS",3);
		opTab.put(op4.getMnemonic(), op4);
		optab op5 = new optab("MOVER","IS",4);
		opTab.put(op5.getMnemonic(), op5);
		optab op6 = new optab("MOVEM","IS",5);
		opTab.put(op6.getMnemonic(), op6);
		optab op7 = new optab("COMP","IS",6);
		opTab.put(op7.getMnemonic(), op7);
		optab op8 = new optab("BC","IS",7);
		opTab.put(op8.getMnemonic(), op8);
		optab op9 = new optab("DIV","IS",8);
		opTab.put(op9.getMnemonic(), op9);
		optab op10 = new optab("READ","IS",9);
		opTab.put(op10.getMnemonic(), op10);
		optab op11 = new optab("PRINT","IS",10);
		opTab.put(op11.getMnemonic(), op11);
		optab op12 = new optab("START","AD",1);
		opTab.put(op12.getMnemonic(), op12);
		optab op13 = new optab("END","AD",2);
		opTab.put(op13.getMnemonic(), op13);
		optab op14 = new optab("ORIGIN","AD",3);
		opTab.put(op14.getMnemonic(), op14);
		optab op15 = new optab("EQU","AD",4);
		opTab.put(op15.getMnemonic(), op15);
		optab op16 = new optab("LTORG","AD",5);
		opTab.put(op16.getMnemonic(), op16);
		optab op17 = new optab("DC","DL",1);
		opTab.put(op17.getMnemonic(), op17);
		optab op18 = new optab("DS","DL",2);
		opTab.put(op18.getMnemonic(), op18);
		return opTab;
	}
}
class optab
{
	String mnemonic;
	String mclass;
	int code;
	
	
	public optab() {
	}
	public optab(String mnemonic, String mclass, int code) {
		this.mnemonic = mnemonic;
		this.mclass = mclass;
		this.code = code;
	}
	public String getMnemonic() {
		return mnemonic;
	}
	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}
	public String getMclass() {
		return mclass;
	}
	public void setMclass(String mclass) {
		this.mclass = mclass;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	
}
class Process
{
	int lc=0;
	initialize init = new initialize();
	Map<String,Integer> sym = new LinkedHashMap<>();
	Map<String,Integer> lit = new LinkedHashMap<>();
	List<Map<String,Integer>> sym_lit = new ArrayList<>();
	List<Integer> array_ls = new ArrayList<Integer>();
	public Map<String, Integer> getLit() {
		return lit;
	}
	public void setLit(Map<String, Integer> lit) {
		this.lit = lit;
	}
	public List<Map<String,Integer>> generate(List<String> lines)
	{
		Iterator it = lines.iterator();
		
		int i=1;
		while(it.hasNext())
		{
			
			
			String line = it.next().toString();
			String[] array_line = line.split("\\s+");
			//System.out.println(array_line[0]+" "+array_line.length);
			//System.out.println(array_line[]);
			//System.out.println(array_line[0]+" "+array_line[1]+" "+array_line[2]+" "+array_line[3] + "      " + lc);
			
			if(array_line[1].contains("="))
			{
				//System.out.println("HI1");
				//System.out.println("HI"+);
				//lc++;
				String temp = array_line[1];
//				if(lit.containsKey(array_line[1]))
//				{
//					//System.out.println("HIJI");
//					
//					temp = temp+"'";
//				}
				lit.put(temp, lc);
				
				//lc++;
			}
			if(!array_line[0].equals(""))
			{
				
				//System.out.println("JI = "+array_line[0] +" "+lc);
				sym.put(array_line[0],lc);
				//System.out.println(array_line[1]);
				try
				{
					if(array_line[3].contains("="))
					{
						if(lit.containsKey(array_line[3]))
						{
							
							array_line[3] = array_line[3]+"'";
						}
						
						lit.put(array_line[3],0);
					}
				}
				catch(Exception e)
				{
					
				}
				if(array_line[1].equals("DS"))
					{
						
						lc+=Integer.parseInt(array_line[2]);
					}
					
					else if(array_line[1].equals("EQU"))
					{
						String exp = array_line[2];
						if(exp.contains("+"))
						{
							String arr[] = exp.split("\\" + "+");
							int val = sym.get(arr[0]);
							//lc = val+Integer.parseInt(arr[1]);
							sym.put(array_line[0],val+Integer.parseInt(arr[1]));
							//lc++;
							
						}
						else if(exp.contains("-"))
						{
							String arr[] = exp.split("\\" + "-");
							int val = sym.get(arr[0]);
							//lc = val-Integer.parseInt(arr[1]);
							//lc++;
							sym.put(array_line[0],val-Integer.parseInt(arr[1]));
						}
						else
						{
							sym.put(array_line[0], sym.get(array_line[2]));
						}
					}
					else
					{
						lc+=1;
						//System.out.println(array_line[1] + "  " + lc);
					}
			}
			else
			{
				
				try
				{
					if(array_line[3].contains("="))
					{
						lit.put(array_line[3],0);
					}
				}
				catch(Exception e)
				{
					
				}
				if((array_line[1].equals("START")) || array_line[1].equals("ORIGIN"))
					{
					
						String exp = array_line[2];
						if(exp.contains("+"))
						{
							
							String arr[] = exp.split("\\" + "+");
					
							int val = sym.get(arr[0]);
							lc = val+Integer.parseInt(arr[1]);
						}
						else if(exp.contains("-"))
						{
							String arr[] = exp.split("\\" + "-");
							int val = sym.get(arr[0]);
							lc = val-Integer.parseInt(arr[1]);
						}
						else
						{
							lc+=Integer.parseInt(array_line[2]);
						}
					}
				else if(!(array_line[1].equals("LTORG")) && !(array_line[1].equals("END")))
				{
					lc+=1;
				}
			}
			i++;
			System.out.println(lc);
		}
		
		sym_lit.add(sym);
		sym_lit.add(lit);
		return sym_lit;
	}
	
	public void printLIT()
	{
		int i=1;
		System.out.println();
		Iterator it = lit.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	       
	        String value = pair.getKey().toString().replaceAll("[^0-9]", "");
	        System.out.println(i+". "+value + " \t " + pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	        i++;
	    }
	}
	public void printmap()
	{
		int i=1;
		System.out.println();
		 Iterator it = sym.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(i+". "+pair.getKey() + "\t\t" + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		        i++;
		    }
	}
	
	
}
class IC
{
	public int getKey(Map<String,Integer> map,String k)
	{
		List keys = new ArrayList(map.keySet());
		int j=0;
		for (int i = 0; i < keys.size(); i++) {
		    if(keys.get(i).equals(k))
		    {
		    	j=i;
		    	break;
		    }
		    
		}
		return j;
		
	}
	
	public int getLit(Map<String,Integer>map,String k)
	{
		int i=1;
		int index=0;
	//	System.out.println(k);
		Iterator it = map.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry pair = (Map.Entry)it.next();
			if(pair.getKey().toString().contains(k))
			{
				//System.out.println(pair.getKey());
				index = i;
				break;
			}
			i++;
		}
		return index;
	}
	public void generateIC(Map<String,Integer> map1,Map<String,Integer> lit,List<String> lines)
	{
		//System.out.println("HI");
		Map<String,Integer> map = new LinkedHashMap<>(map1);
		IC ic = new IC();
		initialize init = new initialize();
		Map<String,optab> opmap = init.getOpcode();
		Map<String,Integer> ccode = init.getCondCode();
		Map<String, Integer> reg = init.getReg();
		Iterator it = lines.iterator();
		int ser=1;
		while(it.hasNext())
		{
			String line = it.next().toString();
			String array_line[] = line.split("\\s+");
			if(array_line[1].equals("EQU") || array_line[1].equals("LTORG"))
			{
				System.out.println(ser+". -- -- --");
			}
			if(!array_line[1].equals("EQU") && !array_line[1].equals("LTORG"))
			{
				
			//	System.out.println(first+" "+sec+" "+third);
				String first="";
				int Second = 0;
				String sec="";
				String third="";
				try
				{
					String classa = init.opTab.get(array_line[1]).getMclass();    //for normal. if array_line[1]="='8'" then it will catch
					int code = init.opTab.get(array_line[1]).getCode();
					 first = "("+classa+","+code+")";
				}
				catch(Exception e)  //After LTORG CASE
				{
					
//					System.out.println("HI");
//					System.out.println("HIIII");
					first = "(DL,"+array_line[1].replaceAll("[^0-9]", "")+")";
					sec = "";
					third="";
				}
				if(array_line[1].contains("STOP") || array_line[1].contains("END"))
				{
					sec = "(0)";
					third="00";
				}
				
				
				try
				{
					if(reg.containsKey(array_line[2]))
					{
						Second = reg.get(array_line[2]);
						sec = "("+Second+")";
						
					}
					else
					{
						
						
						try
						{
							Second = Integer.parseInt(array_line[2]);
							sec = ""+"(C,"+Second+")";
						}
						catch(Exception e)
						{

							
							if(array_line[2].contains("+"))
							{
								String[] a = array_line[2].split("\\" + "+");
								if(map.containsKey(a[0]))
								{
									int index = ic.getKey(map,a[0]);
									index = index+1;
									sec = "(S,"+index+")"+"+"+a[1];
								}
								//sec = 
							}
							else if(array_line[2].contains("-"))
							{
								String[] a = array_line[2].split("\\" + "-");
								if(map.containsKey(a[0]))
								{
									int index = ic.getKey(map,a[0]);
									index = index+1;
									sec = "(S,"+index+")"+"-"+a[1];
								}
							}
							else
							{
//								System.out.println(array_line[2]+"HI");
								if(map1.containsKey(array_line[2]))
								{
									int index = ic.getKey(map, array_line[2]);
									sec = "(S,"+index+")";
								}
								if(ccode.containsKey(array_line[2]))
								{
									
									int index = ic.getKey(map,array_line[2]);
									index = index+1;
									sec = "("+index+")";
								}
								
							}
						}
					}
				}
				catch(Exception e)
				{
					
				}
				
				try
				{
					if(!array_line[3].equals(""))
					{
						if(map.containsKey(array_line[3]))
						{
							int index = ic.getKey(map,array_line[3]);
							index = index+1;
							third = "(S,"+index+")";
						}
						
						if(array_line[3].contains("="))
						{
							
							if(lit.containsKey(array_line[3]))
							{
								//System.out.println(array_line[3]);
								int index = ic.getLit(lit, array_line[3]);
								third = "(L,"+index+")";
							}
						}
					}
				}
				catch(Exception e)
				{
					
				}
				System.out.println(ser+". "+first+" "+sec+" "+third);
				
			}
			ser++;
		}
	}
}
public class assembler {

	public static void main(String[] args) {
		
		
		while(true)
		{
			System.out.println("\n1.Display Assembly code\n2.Display IC\n3.SYMTAB\n4.LITTAB\n5.Exit\n");
			Scanner sc = new Scanner(System.in);
			List<String> lines = new ArrayList<String>();
			
			try {
				File file = new File("/home/TE2/workspace/3239/A1/src/input.txt");
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					stringBuffer.append(line);
					lines.add(line);
					stringBuffer.append("\n");
				}
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
				}
				
			
			
			int ch = sc.nextInt();
			switch(ch)
			{
			case 1:
				Iterator it = lines.iterator();
				while(it.hasNext())
				{
					String line = it.next().toString().replaceAll(",", " ");
					System.out.println(line);
				}
				//System.out.println(lines.size());
				break;
			case 2:
				Process process1 = new Process();
				IC ic = new IC();
				initialize init = new initialize();
				//LinkedHashMap<String,optab> map2 = init.getOpcode();
				List<Map<String,Integer>> map_sym_lit = process1.generate(lines);
				Map<String,Integer> sym = map_sym_lit.get(0);
				Map<String,Integer> lit = map_sym_lit.get(1);
				
				
				//System.out.println(map1.get(0).size());
				ic.generateIC(sym,lit, lines);
				break;
			case 3:
				//System.out.println(lines.size());
				Process process = new Process();
				List<Map<String,Integer>> map = process.generate(lines);
				//System.out.println(map.size());
				process.printmap();
				break;
			case 4:
				Process process2 = new Process();
				List<Map<String,Integer>> map2 = process2.generate(lines);
				process2.printLIT();
				break;
			case 5:
				System.exit(0);
				break;
			}
		}


	}
}