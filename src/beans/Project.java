package beans;

import java.text.ParseException;

import dao.ProjectDao;

import utils.DateTime;

public class Project {
	// 项目概况
	private String Project_ID;
	private String Project_Name;
	private String Project_BeginDate;
	private String Project_EndDate;
	private String Project_TotalPeriod;			// 项目的阶段数, 平遥项目为7个阶段
	private String Project_CurrentPeriod;
	
	// 是否逾期
	private String Project_Expired;
	// 是否在首页显示
	private int Project_Finished;
	
	// 项目负责人(姓名 + ID)
	private String Project_MainLeader;
	private String Project_MainLeaderID;
	private String Project_MainLeaderPhone;
	private String Project_DeputyLeader;
	private String Project_DeputyLeaderID;
	private String Project_DeputyLeaderPhone;
	
	// 项目上传的文件
	private String Project_Add_Filename;
	private String Project_Add_Filepath;
	
	public Project(){}
	
	public Project(String Project_ID, String Project_Name, 
			String Project_BeginDate, String Project_EndDate, 
			String Project_TotalPeriod, String Project_CurrentPeriod,
			String Project_MainLeader, String Project_MainLeaderID, String Project_MainLeaderPhone, 
			String Project_DeputyLeader, String Project_DeputyLeaderID, String Project_DeputyLeaderPhone){
		
		super();
		this.Project_ID = Project_ID;
		this.Project_Name = Project_Name;
		this.Project_BeginDate = Project_BeginDate;
		this.Project_EndDate = Project_EndDate;
		this.Project_TotalPeriod = Project_TotalPeriod;
		this.Project_CurrentPeriod = Project_CurrentPeriod;
		
		
		this.Project_MainLeader = Project_MainLeader;
		this.Project_MainLeaderID = Project_MainLeaderID;
		this.Project_MainLeaderPhone = Project_MainLeaderPhone;
		this.Project_DeputyLeader = Project_DeputyLeader;
		this.Project_DeputyLeaderID = Project_DeputyLeaderID;
		this.Project_DeputyLeaderPhone = Project_DeputyLeaderPhone;
	}
	
	public int isExpired() throws ParseException{
		int res = -1;
		
		// 1. 拿到当前时间(项目开始的第几周), 需要借助项目的开始时间去计算
		DateTime dt = new DateTime();
		String beginDate = this.getProject_BeginDate();
		int passedDays = dt.getPassedDays(beginDate);	// 即当前距离项目开始过去了多少天
		System.out.println("passedDays: " + passedDays);
		
		// 2. 拿到当前预期进度(当前进行到项目的第几阶段了，对应应该是几个周的工作量)
		String curPeriod = this.getProject_CurrentPeriod();
		System.out.println("curPeriod: " + curPeriod);
		
		
		// 3. 判断(利用数据库查询当前阶段的预期周数，如果当前周数多余该预期两周或以上，则判断任务逾期严重，一周则判断)
		ProjectDao proDao = new ProjectDao();
		String p_id = this.getProject_ID();
		String needWeek = proDao.getReasonableWeek(p_id , curPeriod);
		System.out.println("needWeek: " + needWeek);
		int needDays = Integer.parseInt(needWeek);		// 即当前阶段应该在项目开始多少天内做完
		needDays = needDays * 7;
		
		int diffDays = passedDays - needDays;
		System.out.println("diffDays: " + diffDays);
		
		if(passedDays - needDays < 0){
			res = 0;
			this.setProject_Expired("0");	// 正常
		}
		else if(0 <= passedDays - needDays && passedDays - needDays <= 7){
			res = 1;	// 轻度逾期
			this.setProject_Expired("1");
		}else if(7 < passedDays - needDays &&  passedDays - needDays <= 14){
			res = 2;	// 重度逾期
			this.setProject_Expired("2");
		}else if(passedDays - needDays > 14){
			res = 3;	// 严重逾期
			this.setProject_Expired("3");
		}else {
			res = 99;	// 数据无效
			this.setProject_Expired("99");
		}
		
		return res;
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

	public String getProject_BeginDate() {
		return Project_BeginDate;
	}

	public void setProject_BeginDate(String project_BeginDate) {
		Project_BeginDate = project_BeginDate;
	}

	public String getProject_EndDate() {
		return Project_EndDate;
	}

	public void setProject_EndDate(String project_EndDate) {
		Project_EndDate = project_EndDate;
	}

	public String getProject_TotalPeriod() {
		return Project_TotalPeriod;
	}

	public void setProject_TotalPeriod(String project_TotalPeriod) {
		Project_TotalPeriod = project_TotalPeriod;
	}

	public String getProject_MainLeader() {
		return Project_MainLeader;
	}

	public void setProject_MainLeader(String project_MainLeader) {
		Project_MainLeader = project_MainLeader;
	}

	public String getProject_MainLeaderID() {
		return Project_MainLeaderID;
	}

	public void setProject_MainLeaderID(String project_MainLeaderID) {
		Project_MainLeaderID = project_MainLeaderID;
	}

	public String getProject_DeputyLeader() {
		return Project_DeputyLeader;
	}

	public void setProject_DeputyLeader(String project_DeputyLeader) {
		Project_DeputyLeader = project_DeputyLeader;
	}

	public String getProject_DeputyLeaderID() {
		return Project_DeputyLeaderID;
	}

	public void setProject_DeputyLeaderID(String project_DeputyLeaderID) {
		Project_DeputyLeaderID = project_DeputyLeaderID;
	}

	public String getProject_Add_Filename() {
		return Project_Add_Filename;
	}

	public void setProject_Add_Filename(String project_Add_Filename) {
		Project_Add_Filename = project_Add_Filename;
	}

	public String getProject_Add_Filepath() {
		return Project_Add_Filepath;
	}

	public void setProject_Add_Filepath(String project_Add_Filepath) {
		Project_Add_Filepath = project_Add_Filepath;
	}

	public String getProject_CurrentPeriod() {
		return Project_CurrentPeriod;
	}

	public void setProject_CurrentPeriod(String project_CurrentPeriod) {
		Project_CurrentPeriod = project_CurrentPeriod;
	}

	public String getProject_Expired() {
		return Project_Expired;
	}

	public void setProject_Expired(String project_Expired) {
		Project_Expired = project_Expired;
	}

	public String getProject_MainLeaderPhone() {
		return Project_MainLeaderPhone;
	}

	public void setProject_MainLeaderPhone(String project_MainLeaderPhone) {
		Project_MainLeaderPhone = project_MainLeaderPhone;
	}

	public String getProject_DeputyLeaderPhone() {
		return Project_DeputyLeaderPhone;
	}

	public void setProject_DeputyLeaderPhone(String project_DeputyLeaderPhone) {
		Project_DeputyLeaderPhone = project_DeputyLeaderPhone;
	}

	
	public int getProject_Finished() {
		return Project_Finished;
	}

	public void setProject_Finished(int project_Finished) {
		Project_Finished = project_Finished;
	}
	
	
}
