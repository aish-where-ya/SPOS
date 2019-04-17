
import java.io.*;
import java.util.*;
public class Pass1 
{

    static ArrayList<mntf> mnt = new ArrayList<>();
    static ArrayList<String>mdt = new ArrayList<>();
    static HashMap<String, ArrayList<String>> pntab = new HashMap<>();
    static ArrayList<String>kptab = new ArrayList<>();
   
    public static void Pass1() throws Exception
    {
       
        BufferedReader br = new BufferedReader(new FileReader("input1.asm"));
        String line = "";
        while((line = br.readLine()) != null)
        {
            String split[] = line.split("\\s+");
            if(split[1].equals("MACRO"))
            {
               
                ArrayList<String> pnt = new ArrayList<>();
                line = br.readLine();
                int k = 2,pp=0,kp=0;
               
                String sp[] = line.split("\\s+");
                mntf mn = new mntf();
                mn.name = sp[1];
                mn.kpdtp = kptab.size();
                mn.mdtp = mdt.size();
                //System.out.println();
                while(k < sp.length)
                {
                    String s = "";
                    if(sp[k].contains("="))
                    {
                        //Keyword parameter present
                    	s = sp[k].substring(sp[k].indexOf("&")+1,sp[k].indexOf("="));
                        kptab.add(sp[k]);
                        kp++;
                       
                    }
                    else
                    {
                        //Positional Parameter present
                    	s = sp[k].substring(sp[k].indexOf("&")+1,sp[k].indexOf(","));
                   
                        pp++;
                    }
                    pnt.add(s);
                    //System.out.println(s);
                    k++;
                }
                //Setting the macro and putting it in pntab
               
                mn.setKp(kp);
                mn.setPp(pp);
                mnt.add(mn);
                pntab.put(mn.name,pnt);
                //Reading macro and putting the definition in mdt
                while((line = br.readLine())!=null)
                {
                    String s = "";
                    String def[] = line.split("\\s+");
                    if(def[1].equals("MEND"))
                    {
                         mdt.add("MEND");
                         break;
                    }
                   
                    s = def[1]+" ";
                    k=2;
                    while(k < def.length)
                    {
                        if(def[k].contains("&"))
                        {
                        	s += "(P,"+ pnt.indexOf(def[k].replace("&", "").replace(",",""))+")    ";
                           
                        }
                        else
                        {
                             s += def[k];
                        }
                        k++;
                    }
                    mdt.add(s);
                }
            }
           
        }
        print();
    }
    public static void print() 
    {
        for(int i=0;i<mnt.size();i++) 
        {
            mntf m = mnt.get(i);
            //System.out.println(m.getName()+" "+m.getKp()+" "+m.getPp()+" "+m.getKpdtp()+" "+m.getMdtp());
            ArrayList<String> p = pntab.get(m.getName());
            System.out.println("Pntab for "+m.getName());
            for(int k=0;k<p.size();k++) 
            {
                System.out.println(p.get(k));
            }
        }
        for(int i=0;i<mdt.size();i++) 
        {
            System.out.println(mdt.get(i));
        }
    }
   
    public static void main(String[] args) throws Exception
    {
            Pass1();
            //p.Pass1();

    }

}