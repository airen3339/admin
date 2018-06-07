package beans;

public class Problem {
	private String Problem_ID;
	private String Problem_Name;
	private String Problem_Desc;
	private String Problem_Solve;
	private String Problem_Content;
	private String Problem_Owner_ID;
	private String Problem_Owner;
	private String Problem_Last_Modify;
	
	public Problem(String Problem_ID, String Problem_Name, String Problem_Desc, String Problem_Solve, 
			String Problem_Content, String Problem_Owner_ID, String Problem_Owner, String Problem_Last_Modify){
		this.Problem_ID = Problem_ID;
		this.Problem_Name = Problem_Name;
		this.Problem_Desc = Problem_Desc;
		this.Problem_Solve = Problem_Solve;
		this.Problem_Content = Problem_Content;
		this.Problem_Owner_ID = Problem_Owner_ID;
		this.Problem_Owner = Problem_Owner;
		this.Problem_Last_Modify = Problem_Last_Modify;
	}

	public String getProblem_ID() {
		return Problem_ID;
	}

	public void setProblem_ID(String problem_ID) {
		Problem_ID = problem_ID;
	}

	public String getProblem_Name() {
		return Problem_Name;
	}

	public void setProblem_Name(String problem_Name) {
		Problem_Name = problem_Name;
	}

	public String getProblem_Content() {
		return Problem_Content;
	}

	public void setProblem_Content(String problem_Content) {
		Problem_Content = problem_Content;
	}

	public String getProblem_Owner_ID() {
		return Problem_Owner_ID;
	}

	public void setProblem_Owner_ID(String problem_Owner_ID) {
		Problem_Owner_ID = problem_Owner_ID;
	}

	public String getProblem_Owner() {
		return Problem_Owner;
	}

	public void setProblem_Owner(String problem_Owner) {
		Problem_Owner = problem_Owner;
	}

	public String getProblem_Last_Modify() {
		return Problem_Last_Modify;
	}

	public void setProblem_Last_Modify(String problem_Last_Modify) {
		Problem_Last_Modify = problem_Last_Modify;
	}

	
	public String getProblem_Desc() {
		return Problem_Desc;
	}

	public void setProblem_Desc(String problem_Desc) {
		Problem_Desc = problem_Desc;
	}

	public String getProblem_Solve() {
		return Problem_Solve;
	}

	public void setProblem_Solve(String problem_Solve) {
		Problem_Solve = problem_Solve;
	}

	
	
}
