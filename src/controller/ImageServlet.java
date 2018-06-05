package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Image;

import dao.ImageDao;

import net.sf.json.JSONObject;

public class ImageServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		String id = request.getParameter("id");
		String newLink = request.getParameter("newLink");
		String status = request.getParameter("status");
		if(newLink != null){		// 修改访问链接
			ImageDao imageDao = new ImageDao();
			imageDao.updateImageLink(newLink, id);	
		}else if(status != null){	// 修改状态
			ImageDao imageDao = new ImageDao();
			imageDao.updateImageStatus(status, id);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", "1");
		jsonObject.put("IMAGE_ID", id);
		response.getWriter().write(jsonObject.toString());
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		
		ImageDao imageDao = new ImageDao();				
		List<Image> imgList = imageDao.queryImagesAvaiable();

		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 1);
			jsonObject.put("data", imgList);
			response.getWriter().write(jsonObject.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
