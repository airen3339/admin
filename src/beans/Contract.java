package beans;

public class Contract {
	
	private String C_ID;
	private String Contract_ID;
	private String Contract_Name;
	private String Contract_BeginDate;
	private String Contract_EndDate;
	private String Contract_Party_A;
	private String Contract_Party_B;
	private String Contract_Value;
	
	public Contract(String C_ID, String Contract_ID, String Contract_Name, String Contract_BeginDate, String Contract_EndDate, String Contract_Party_A, String Contract_Party_B, String Contract_Value){
		this.C_ID = C_ID;
		this.Contract_ID = Contract_ID;
		this.Contract_Name = Contract_Name;
		this.Contract_BeginDate = Contract_BeginDate;
		this.Contract_EndDate = Contract_EndDate;
		this.Contract_Party_A = Contract_Party_A;
		this.Contract_Party_B = Contract_Party_B;
		this.Contract_Value = Contract_Value;
	}

	public String getC_ID() {
		return C_ID;
	}

	public void setC_ID(String c_ID) {
		C_ID = c_ID;
	}

	public String getContract_ID() {
		return Contract_ID;
	}

	public void setContract_ID(String contract_ID) {
		Contract_ID = contract_ID;
	}

	public String getContract_Name() {
		return Contract_Name;
	}

	public void setContract_Name(String contract_Name) {
		Contract_Name = contract_Name;
	}

	public String getContract_BeginDate() {
		return Contract_BeginDate;
	}

	public void setContract_BeginDate(String contract_BeginDate) {
		Contract_BeginDate = contract_BeginDate;
	}

	public String getContract_EndDate() {
		return Contract_EndDate;
	}

	public void setContract_EndDate(String contract_EndDate) {
		Contract_EndDate = contract_EndDate;
	}

	public String getContract_Party_A() {
		return Contract_Party_A;
	}

	public void setContract_Party_A(String contract_Party_A) {
		Contract_Party_A = contract_Party_A;
	}

	public String getContract_Party_B() {
		return Contract_Party_B;
	}

	public void setContract_Party_B(String contract_Party_B) {
		Contract_Party_B = contract_Party_B;
	}

	public String getContract_Value() {
		return Contract_Value;
	}

	public void setContract_Value(String contract_Value) {
		Contract_Value = contract_Value;
	}
	
}
