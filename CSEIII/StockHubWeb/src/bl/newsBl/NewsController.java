package bl.newsBl;

import VO.marketVO.NewsVO;
import VO.marketVO.SimpleNewsVO;
import blSer.NewsBlSer;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class NewsController implements NewsBlSer {


    @Override
    public ArrayList<NewsVO> getNewsD(String date) throws Exception {
        News news=new News();
        ArrayList<NewsVO> arrayList=news.getNewsD(date);
        return arrayList;
    }

    @Override
    public NewsVO getNewsT(String newsID) throws Exception {
        News news=new News();
        NewsVO newsVO=news.getNewsT(newsID);
        return newsVO;
    }

    @Override
    public ArrayList<NewsVO> getNewsM() throws Exception {
        News news=new News();
        ArrayList<NewsVO> arrayList=news.getNewsM();
        return arrayList;
    }

    @Override
    public boolean addPraise(String newsID) throws Exception {
        News news=new News();
        boolean result=news.addPraise(newsID);
        return result;
    }

    @Override
    public boolean addCriticize(String newsID) throws Exception {
        News news=new News();
        boolean result=news.addCriticize(newsID);
        return result;
    }

    @Override
    public boolean addView(String newsID) throws Exception {
        return false;
    }


    @Override
    public boolean addComment(String newsID, String com, String comAccount) throws Exception {
        News news=new News();
        boolean result=news.addComment(newsID,com,comAccount);
        return result;
    }


    @Override
    public boolean addCollectionNews(String account, ArrayList<String> newsID) throws Exception {
        return false;
    }

    @Override
    public ArrayList<SimpleNewsVO> getCollectionNews(String account) throws Exception {
        return null;
    }

    @Override
    public boolean deleteCollectionNews(String account, ArrayList<String> newsID) throws Exception {
        return false;
    }

    @Override
    public ArrayList<SimpleNewsVO> getAllNewsVO() throws Exception {
        return null;
    }

    @Override
    public boolean isAlreadyCollection(String account, String newsID) throws Exception {
        return false;
    }
}
