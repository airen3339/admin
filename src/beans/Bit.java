package beans;

public class Bit {
	
	private String Project_ID;
	private String Project_Name;
	private String Project_Title;
	private String Project_Desc;
	private String Project_BeginDate;
	private String Project_StopPriceDate;
	private String Project_OpenPriceDate;
	
	public Bit() {}
	
	public Bit(String Project_ID, String Project_Name, String Project_Title, String Project_Desc,
				String Project_BeginDate, String Project_StopPriceDate, String Project_OpenPriceDate) {
		super();
		
		this.Project_ID = Project_ID;
		this.Project_Name = Project_Name;
		this.Project_Title = Project_Title;
		this.Project_Desc = Project_Desc;
		
		this.Project_BeginDate = Project_BeginDate;
		this.Project_StopPriceDate = Project_StopPriceDate;
		this.Project_OpenPriceDate = Project_OpenPriceDate;
	}

	public String getProject_ID() {
		return Project_ID;
	}

	public void setProject_ID(String project_ID) {
		Project_ID = project_ID;
	}

	public String getProject_Name() {
		return Project_Name;
	}

	public void setProject_Name(String project_Name) {
		Project_Name = project_Name;
	}

	public String getProject_Title() {
		return Project_Title;
	}

	public void setProject_Title(String project_Title) {
		Project_Title = project_Title;
	}

	public String getProject_Desc() {
		return Project_Desc;
	}

	public void setProject_Desc(String project_Desc) {
		Project_Desc = project_Desc;
	}

	public String getProject_BeginDate() {
		return Project_BeginDate;
	}

	public void setProject_BeginDate(String project_BeginDate) {
		Project_BeginDate = project_BeginDate;
	}

	public String getProject_StopPriceDate() {
		return Project_StopPriceDate;
	}

	public void setProject_StopPriceDate(String project_StopPriceDate) {
		Project_StopPriceDate = project_StopPriceDate;
	}

	public String getProject_OpenPriceDate() {
		return Project_OpenPriceDate;
	}

	public void setProject_OpenPriceDate(String project_OpenPriceDate) {
		Project_OpenPriceDate = project_OpenPriceDate;
	}
	
	
}
