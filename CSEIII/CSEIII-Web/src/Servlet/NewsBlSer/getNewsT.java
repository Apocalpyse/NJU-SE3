package Servlet.NewsBlSer;

import VO.marketVO.NewsVO;
import bl.newsBl.News;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chenjin on 2017/5/25.
 */
public class getNewsT extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private News news;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getNewsT(){
		super();
		news=new News();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String newsID=request.getParameter("newsID");

		NewsVO newsVO = null;
		try {
			newsVO=news.getNewsT(newsID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("newsID",newsVO.getNewsID() );
				obj.put("title",newsVO.getTitle());
				obj.put("author",newsVO.getClassify());
				obj.put("time", newsVO.getTime());
				obj.put("view",newsVO.getView());
				obj.put("praise",newsVO.getPraise());
				obj.put("criticize",newsVO.getCriticize());
				obj.put("content",newsVO.getContent());
				obj.put("comment",newsVO.getComment());
				obj.put("commentAccount",newsVO.getCommentAccount());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			PrintWriter pw = response.getWriter();
			pw.write(obj.toString());
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
