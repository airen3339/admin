package utils;

import net.sf.json.JSONObject;

public class JudgeReturn {
	
	public JSONObject judgeReturn(JSONObject jsonObject, int res) {
		
		if(res == 1){
			jsonObject.put("status", res);
			jsonObject.put("msg", "success");
		}else if(res == 0){
			jsonObject.put("status", res);
			jsonObject.put("msg", "error");
		}else {
			jsonObject.put("status", res);
			jsonObject.put("msg", "other situation");
		}
		return jsonObject;
	}
	
}
