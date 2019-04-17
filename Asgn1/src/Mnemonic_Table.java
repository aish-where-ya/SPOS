
public class Mnemonic_Table 
{
	String instr, type;
	int opcode;
	Mnemonic_Table(String type, int opcode, String instr)
	{
		this.type=type;
		this.opcode = opcode;
		this.instr=instr;
	}
	int getOpcode()
	{
		return this.opcode;
	}
}