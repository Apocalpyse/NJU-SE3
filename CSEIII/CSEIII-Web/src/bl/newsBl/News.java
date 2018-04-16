package bl.newsBl;

import PO.marketPO.NewsPO;
import PO.marketPO.SimpleNewsPO;
import VO.marketVO.NewsVO;
import VO.marketVO.SimpleNewsVO;
import dataSer.NewsDataSer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by A on 2017/5/21.
 */
public class News {
    public static void main(String[] args){
        News news=new News();
        ArrayList<NewsVO> arrayList=null;
        try {
            arrayList=news.getNewsM();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(arrayList.get(0).getView());
        System.out.println(arrayList.get(1).getView());
        System.out.println(arrayList.get(2).getView());
    }



    public ArrayList<NewsVO> getNewsD(String date) throws Exception {
        NewsDataSer newsDataSer = null;
        ArrayList<NewsPO> arrayList=newsDataSer.getNewsD(date);
        ArrayList<NewsVO> arrayList1 = new ArrayList<NewsVO>();
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new NewsVO(arrayList.get(i).getNewsID(),arrayList.get(i).getTitle(),arrayList.get(i).getClassify(),
                    arrayList.get(i).getTime(),arrayList.get(i).getView(),arrayList.get(i).getPraise(),
                    arrayList.get(i).getCriticize(),arrayList.get(i).getContent(),arrayList.get(i).getComment(),arrayList.get(i).getCommentAccount()));
        }
        return arrayList1;
    }


    public NewsVO getNewsT(String newsID) throws Exception {
        NewsDataSer newsDataSer = null;
        NewsPO newsPO=newsDataSer.getNewsT(newsID);
        return new NewsVO(newsPO.getNewsID(),newsPO.getTitle(),newsPO.getClassify(),newsPO.getTime(),
                newsPO.getView(),newsPO.getPraise(),newsPO.getCriticize(),newsPO.getContent(),newsPO.getComment(),newsPO.getCommentAccount());
    }


    public ArrayList<NewsVO> getNewsM() throws Exception {
        NewsDataSer newsDataSer = null;
        ArrayList<NewsPO> arrayList=newsDataSer.getNewsM();
        /*ArrayList<NewsPO> arrayList =new  ArrayList<NewsPO>();
        ArrayList<String> stringArrayList = new ArrayList<String>();

        stringArrayList.add("huhu");
        stringArrayList.add("huh");

        arrayList.add(new NewsPO("0","hu","fhu","hu","fhu",123,123,123,"hu",stringArrayList,stringArrayList));
        arrayList.add(new NewsPO("1","hu","fhu","hu","fhu",125,123,123,"hu",stringArrayList,stringArrayList));
        arrayList.add(new NewsPO("0","hu","fhu","hu","fhu",124,123,123,"hu",stringArrayList,stringArrayList));
        */
        ArrayList<NewsVO> arrayList1 = new ArrayList<NewsVO>();

        Collections.sort(arrayList, new Comparator<NewsPO>() {
            @Override
            public int compare(NewsPO o1, NewsPO o2) {
                NewsPO stu1=(NewsPO) o1;
                NewsPO stu2=(NewsPO)o2;
                if(stu1.getView()<stu2.getView()){
                    return 1;
                }else if(stu1.getView()==stu2.getView()){
                    return 0;
                }else{
                    return -1;
                }
            }
        });

        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new NewsVO(arrayList.get(i).getNewsID(),arrayList.get(i).getTitle(),arrayList.get(i).getClassify(),
                    arrayList.get(i).getTime(),arrayList.get(i).getView(),arrayList.get(i).getPraise(),
                    arrayList.get(i).getCriticize(),arrayList.get(i).getContent(),arrayList.get(i).getComment(),arrayList.get(i).getCommentAccount()));
        }
        return arrayList1;

    }


    public boolean addPraise(String newsID) throws Exception {
        NewsDataSer newsDataSer=null;
        boolean result=newsDataSer.addPraise(newsID);
        return result;
    }


    public boolean addCriticize(String newsID) throws Exception {
        NewsDataSer newsDataSer=null;
        boolean result=newsDataSer.addCriticize(newsID);
        return result;
    }


    public boolean addComment(String newsID, String com, String comAccount) throws Exception {
        NewsDataSer newsDataSer=null;
        boolean result=newsDataSer.addComment(newsID,com,comAccount);
        return result;
    }


    public boolean addCollectionNews(String account, ArrayList<String> newsID) throws Exception {
       NewsDataSer newsDataSer=null;
       boolean result=newsDataSer.addCollectionNews(account,newsID);
       return result;
    }


    public ArrayList<SimpleNewsVO> getCollectionNews(String account) throws Exception {
        NewsDataSer newsDataSer=null;
        ArrayList<SimpleNewsPO> arrayList=newsDataSer.getCollectionNews(account);
        ArrayList<SimpleNewsVO> arrayList1=new ArrayList<SimpleNewsVO>();
        for(int i=0;i<arrayList.size();i++){
            arrayList1.add(new SimpleNewsVO(arrayList.get(i).getNewsID(),arrayList.get(i).getTitle(),arrayList.get(i).getClassify()));
        }
        return arrayList1;
    }


    public boolean deleteCollectionNews(String account, ArrayList<String> newsID) throws Exception {
        NewsDataSer newsDataSer=null;
        boolean result=newsDataSer.deleteCollectionNews(account,newsID);
        return result;
    }
}
