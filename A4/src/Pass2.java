import java.io.*;
import java.util.*;
public class Pass2 {

    ArrayList<mntf> mnt = new ArrayList<>();
    ArrayList<String>mdt = new ArrayList<>();
    HashMap<String, ArrayList<String>> pntab = new HashMap<>();
    ArrayList<String>kptab = new ArrayList<>();
    PassOne p;
    public Pass2() throws Exception {
        p = new Pass1();
        p.PassOne();
        mnt = p.mnt;
        mdt = p.mdt;
        pntab = p.pntab;
        kptab = p.kptab;
       
    }
   
    void passtwo() throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input1.asm"));
        String line = "";
       
        int flag=0;
        while((line=br.readLine())!=null){
            String[] split = line.split("\\s+");
            if(split[1].equals("START")){
                flag=1;
            }
            if(flag==1){
                if(pntab.containsKey(split[1])){
                    ArrayList<String> apt = new ArrayList<>();
                   
                    int index = getIndexMnt(split[1]);
                    int kpdtp = mnt.get(index).getKpdtp();
                    //fill default values
                   
                    int pp = mnt.get(index).getPp();
                    int kp = mnt.get(index).getKp();
                    int i=0;
                    for(i=0;i<pp;i++){
                        apt.add("0");
                    }
                    int j=0;
                    for(i=pp;i<pp+kp;i++){
                        String kpara = kptab.get(kpdtp+j);
kpara=kpara.substring(kpara.indexOf("=")+1,kpara.length()).replace(",","");
                       
                        if(kpara.length()>1){
                            apt.add(kpara);
                        }else{
                            apt.add("0");
                        }
                        j++;
                    }
                   
                    int k=2,l=0;
                    while(k<split.length){
                        if(l<pp){
                            apt.remove(l);
                            apt.add(l,split[k].replace(",", ""));
                        }else{
                          String para =  split[k].substring(split[k].indexOf("&")+1,split[k].indexOf("="));
                            String value = split[k].substring(split[k].indexOf("=")+1,split[k].length()).replace(",", "");
                           
                  int in = pntab.get(split[1]).indexOf(para);
                            apt.remove(in);
                            apt.add(in,value);
                        }
                        l++;
                        k++;
                    }
                   
                    int mdtp = mnt.get(index).getMdtp();
                   
                    while(true){
                        String inst = mdt.get(mdtp);
                        if(inst.contains("MEND")){
                            break;
                        }
                       
                        String[] sp = inst.split("\\s+");
                       
                        k=1;
                        while(k<sp.length && sp[k].contains("P")){
                             String val =  apt.get(Integer.parseInt(sp[k].substring(sp[k].indexOf(",")+1,sp[k].indexOf(")"))));
                             sp[k] = val;
                             k++;
                        }
                        System.out.println(sp[0]+" "+sp[1]+" "+sp[2]);
                        mdtp++;
                    }
                    System.out.println("APTAB:");
                    for(i=0;i<apt.size();i++){
                        System.out.println(apt.get(i));
                    }
                }
            }
        }
    }
   
    public int getIndexMnt(String mn){
        int temp=0;
        while(!mnt.get(temp).getName().equals(mn)){
            temp++;
        }
        return temp;
    }
    public static void main(String[] args) throws Exception {
        PassTwo p = new PassTwo();
        p.passtwo();
    }

}