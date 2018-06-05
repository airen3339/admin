package service;

import beans.Info;


public class InfoService {
	
	public void IsEmptyInfo(Info info) throws Exception{
		
		if(info == null){								// 如果查询为空，则说明信息不存在
			System.out.println("faild to search the info!");
			throw new Exception("信息不存在");
		}else {
			System.out.println("success to search the info!");
			throw new Exception(info.getInfo_ID());
		}
	}
}
