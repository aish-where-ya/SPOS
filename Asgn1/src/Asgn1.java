import java.io.*;
import java.util.*;
public class Asgn1 
{
	Hashtable<String, Mnemonic_Table> is = new Hashtable<>();
	Hashtable<String, Integer> regs = new Hashtable<>();
	
	ArrayList<String> symtab = new ArrayList<>();
	ArrayList<Integer> symaddr = new ArrayList<>();
	ArrayList<String> littab = new ArrayList<>();
	ArrayList<Integer> litaddr = new ArrayList<>();
	ArrayList<Integer> pooltab = new ArrayList<>();
	int lc=0;
	
	Asgn1()
	{
		is.put("STOP", new Mnemonic_Table("IS",0,"STOP"));
		is.put("ADD", new Mnemonic_Table("IS",1,"ADD"));
		is.put("SUB", new Mnemonic_Table("IS",2,"SUB"));
		is.put("MULT", new Mnemonic_Table("IS",3,"MULT"));
		is.put("MOVER", new Mnemonic_Table("IS",4,"MOVER"));
		is.put("MOVEM", new Mnemonic_Table("IS",5,"MOVEM"));
		is.put("COMP", new Mnemonic_Table("IS",6,"COMP"));
		is.put("BC", new Mnemonic_Table("IS",7,"BC"));
		is.put("DIV", new Mnemonic_Table("IS",8,"DIV"));
		is.put("READ", new Mnemonic_Table("IS",9,"READ"));
		is.put("PRINT", new Mnemonic_Table("IS",10,"PRINT"));
		
		is.put("START",new Mnemonic_Table("AD",1,"START"));
		is.put("END",new Mnemonic_Table("AD",2,"END"));
		is.put("ORIGIN",new Mnemonic_Table("AD",3,"ORIGIN"));
		is.put("EQU",new Mnemonic_Table("AD",4,"EQU"));
		is.put("LTORG",new Mnemonic_Table("AD",5,"LTORG"));
		
		is.put("DS", new Mnemonic_Table("DL",1,"DS"));
		is.put("DC", new Mnemonic_Table("DL",2,"DC"));
			
		regs.put("AREG", 1);
		regs.put("BREG", 2);
		regs.put("CREG", 3);
		regs.put("DREG", 4);
		
	}
	
	public void generate_IC()throws IOException
	{
		BufferedReader br = new BufferedReader( new FileReader("input.asm"));
		BufferedWriter bw = new BufferedWriter( new FileWriter("output.txt"));
		pooltab.add(0,0);
		String line;
		while((line=br.readLine())!= null)
		{
			//String line = br.readLine();
			String split[] = line.split("\\s+");
			//System.out.println(line);
			//System.out.println(split +" "+split.length);
			//System.out.println(split[0]+" "+split[1]+" "+split[2] );
			if (split[0].length() >0)
			{
				if(!symtab.contains(split[0]))
				{
					symtab.add(split[0]);
					symaddr.add(lc);
				}
				else
				{
					int index = symtab.indexOf(split[0]);
					symaddr.remove(index);
					symaddr.add(index,lc);
				}
			}
			if(split[1].equals("START"))
			{
				
				lc = Integer.parseInt(split[2]);
				Mnemonic_Table mt = is.get("START");
				//int op = mt.getOpcode(); 
				bw.write("("+mt.type+","+mt.opcode+")\t(C,"+lc+")\n");
			}
			else if(split[1].equals("END"))
			{
				if(litaddr.contains(0))
				{
					int i = pooltab.get(pooltab.size()-1);
					for(;i<littab.size();i++)
					{
						if(litaddr.get(i) == 0)
						{
							litaddr.remove(i);
							litaddr.add(i,lc);
							lc++;
						}
					}
				}
				Mnemonic_Table mt = is.get("END");
				bw.write("("+mt.type+","+mt.opcode+")\n");
			}
			else if(split[1].equals("LTORG"))
			{
				if(litaddr.contains(0))
				{
					int i = pooltab.get(pooltab.size()-1);
					for(;i<littab.size();i++)
					{
						if(litaddr.get(i) == 0)
						{
							litaddr.remove(i);
							litaddr.add(i,lc);
							lc++;
						}
					}
				}
				pooltab.add(littab.size());
				Mnemonic_Table mt = is.get("LTORG");
				bw.write("("+mt.type+","+mt.opcode+")\n");
			}
			else if (split[1].equals("ORIGIN"))
			{
				if(split[2].contains("+") || split[2].contains("-"))
					lc = getAddress(split[2]);
				else
					lc = symaddr.get(symtab.indexOf(split[2]));
				Mnemonic_Table mt = is.get("ORIGIN");
				bw.write("("+mt.type+","+mt.opcode+")\t(C,"+lc+")\n");
			}
			else if(split[1].equals("EQU"))
			{
				int val;
				if(split[2].contains("+") || split[2].contains("-"))
					val = getAddress(split[2]);
				else
					val = symaddr.get(symtab.indexOf(split[2]));
				if(!symtab.contains(split[0]))
				{
					symtab.add(split[0]);
					symaddr.add(val);
				}
				else
				{
					int index = symtab.indexOf(split[0]);
					symaddr.remove(index);
					symaddr.add(index, val);
				}
				Mnemonic_Table mt = is.get("EQU");
				bw.write("("+mt.type+","+mt.opcode+")\t(C,"+val+")\n");
			}
			else if (split[1].equals("DC"))
			{
				if(!symtab.contains(split[0]))
				{
					symtab.add(split[0]);
					symaddr.add(lc);
				}
				else
				{
					int index = symtab.indexOf(split[0]);
					symaddr.remove(index);
					symaddr.add(index,lc);
				}
				Mnemonic_Table mt = is.get("DC");
				int val = Integer.parseInt(split[2]);
				bw.write("("+mt.type+","+mt.opcode+")\t(C,"+val+")\n");
			}
			else if (split[1].equals("DS"))
			{
				if(!symtab.contains(split[0]))
				{
					symtab.add(split[0]);
					symaddr.add(lc);
				}
				else
				{
					int index = symtab.indexOf(split[0]);
					symaddr.remove(index);
					symaddr.add(index,lc);
				}
				Mnemonic_Table mt = is.get("DS");
				int val = Integer.parseInt(split[2]);
				bw.write("("+mt.type+","+mt.opcode+")\t(C,"+val+")\n");
				lc+=val;
			}
			else
			{
				Mnemonic_Table mt = is.get(split[1]);
				bw.write("("+mt.type+","+mt.opcode+")\t");
				/*if(!symtab.contains(split[0]))
				{
					symtab.add(split[0]);
					symaddr.add(lc);
				}
				else
				{
					int index = symtab.indexOf(split[0]);
					symaddr.remove(index);
					symaddr.add(index,lc);
				}*/
				if(split.length>2)
				{
					//System.out.println(split[2]);
					String split2[]= split[2].split(",");
					//System.out.println(split2[0]);
					if(regs.containsKey(split2[0]))
					{
						int reg=regs.get(split2[0]);
						bw.write("("+reg+")");
					}
					else
					{
						bw.write("(S,"+symtab.indexOf(split2[0])+")");
					}
				}
				if(split.length>3)	
				{
					if(split[3].contains("="))
					{
						split[3].replace("=","").replace("'", "");
						littab.add(split[3]);
						litaddr.add(0);
						bw.write("(L,"+littab.indexOf(split[3])+")");
					}
					else if(symtab.contains(split[3]))
					{
						bw.write("(S,"+symtab.indexOf(split[3])+")");
					}
					else
					{
						symtab.add(split[3]);
						symaddr.add(0);
						bw.write("(S,"+symtab.indexOf(split[3])+")");
					}
					bw.newLine();
				}
				else
					bw.newLine();
				
			}
			lc++;
		}
		
		bw.flush();
		bw.close();
		System.out.println();
		BufferedWriter bw1 = new BufferedWriter(new FileWriter("sym.txt"));
		BufferedWriter bw2 = new BufferedWriter(new FileWriter("lit.txt"));
		BufferedWriter bw3 = new BufferedWriter(new FileWriter("pool.txt"));
		
		System.out.println(symtab.size());
		System.out.println(symaddr.size());
		System.out.println(littab.size());
		System.out.println(litaddr.size());
		System.out.println(pooltab.size());
		
		System.out.println("Symtab");
		for(int i=0;i<symtab.size();i++)
		{
			bw1.write(symaddr.get(i)+"\t"+symtab.get(i)+"\n");
			System.out.println(symaddr.get(i)+"\t"+symtab.get(i)+"\n");
		}
		System.out.println("Littab");
		for(int i=0;i<littab.size();i++)
		{
			bw2.write(litaddr.get(i)+"\t"+littab.get(i)+"\n");
			System.out.println(litaddr.get(i)+"\t"+littab.get(i)+"\n");
		}
			
		
		System.out.println("Pooltab");
		for(int i=0;i<pooltab.size();i++)
		{
			bw3.write(pooltab.get(i)+"\n");
			System.out.println(pooltab.get(i)+"\n");
		}
			
		
		bw1.flush();
		bw2.flush();
		bw3.flush();
		bw1.close();
		bw2.close();
		bw3.close();
	}
	
	int getAddress(String str)
	{
		int rval=0;
		if(str.contains("+"))
		{
			String nstr[] = str.split("\\+");
			int index = symaddr.get(symtab.indexOf(nstr[0]));
			int val = Integer.parseInt(nstr[1]);
			rval = index+val;
		}
		else if(str.contains("-"))
		{
			String nstr[]=str.split("\\-");
			int index = symaddr.get(symtab.indexOf(nstr[0]));
			int val = Integer.parseInt(nstr[1]);
			rval = index+val;
		}
		return rval;
	}
	
	public static void main(String args[]) throws IOException
	{
		Asgn1 obj = new Asgn1();
		obj.generate_IC();
	}
}
