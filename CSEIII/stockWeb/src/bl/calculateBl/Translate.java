package bl.calculateBl;

import PO.StockPO;
import PO.UserPO;
import VO.StockVO;
import VO.UserVO;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Administrator on 2017/3/10.
 */
public class Translate {
    /**
     * 将位数不满的code填满，如将“12”填满为“000012”
     * @param shortCode
     * @return
     */
    public String shortToLong(String shortCode){
        int length=shortCode.length();
        String longCode="";
        for(int i=0;i<6-length;i++){
            longCode=longCode+"0";
        }
        longCode=longCode+shortCode;

        return longCode;
    }

    /**
     * 将vo包里位数多于po包的code建去，如将“000123”转化为“123”
     * @param longCode
     * @return
     */
    public String longToShort(String longCode){
        int numOfZero=0;
        for (int i = 0; i < longCode.length() ; i++) {
            char c=longCode.charAt(i);
            if(c=='0'){
                numOfZero++;
            }else {
                break;
            }
        }
        String shortCode="";
        shortCode=longCode.substring(numOfZero,longCode.length());

        return shortCode;
    }

    /**
     * 时间上，String转Calendar
     * @param strTime
     * @return
     */
    public Calendar myToCalendar(String strTime){
        if(strTime==""){
            return null;
        }
        String[] str=new String[3];
        str=strTime.split("/");
        int year=Integer.parseInt(str[2])+2000;
        int month=Integer.parseInt(str[0]);
        int date=Integer.parseInt(str[1]);
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DATE,date);

        return calendar;
    }

    /**
     * 时间上，Calendar转String
     * @param calendar
     * @return
     */
    public String myToString(Calendar calendar){
        if(calendar==null){
            return "";
        }

        int year1=calendar.get(Calendar.YEAR);
        int month1=calendar.get(Calendar.MONTH)+1;
        int date1=calendar.get(Calendar.DATE);

        String year2=Integer.toString(year1);
        String year=year2.substring(2,4);
        String month=Integer.toString(month1);
        String date=Integer.toString(date1);

        String time = month+"/"+date+"/"+year;

        return time;
    }

    /**
     * 此方法实现将界面层传来的preDate转化为数据库需要的date的格式，如将“04/07/2013”转化为“4/7/13”
     * @param preDate
     * @return
     */
    public String toDataDate(String preDate){
        if(preDate==""){
            return "";
        }
        String[] str=new String[3];
        str=preDate.split("/");
        int month=Integer.parseInt(str[0]);
        int date=Integer.parseInt(str[1]);
        int year=Integer.parseInt(str[2]);
        String monthStr=String.valueOf(month);
        String dateStr=String.valueOf(date);
        String yearStr="";
        if(str[2].length()==4){
            yearStr=String.valueOf(year-2000);
        }else{
            yearStr=String.valueOf(year);
        }
        String result=monthStr+"/"+dateStr+"/"+yearStr;

        return result;
    }

    /**
     * 此方法实现将数据库格式的日期转化为界面层格式的数据
     * @param dataDate
     * @return
     */
    public String toPreDate(String dataDate){
        if(dataDate==""){
            return "";
        }
        String[] str=new String[3];
        str=dataDate.split("/");
        if(str[0].length()==1){
            str[0]="0"+str[0];
        }
        if(str[1].length()==1){
            str[1]="0"+str[1];
        }
        if(str[2].length()==2){
            str[2]="20"+str[2];
        }
        String result=str[0]+"/"+str[1]+"/"+str[2];

        return result;
    }

    /**
     * 将ArrayList<StockPO>转化为ArrayList<StockVO>
     * @param stockPOs
     * @return
     */
    public ArrayList<StockVO> toVO(ArrayList<StockPO> stockPOs){
        ArrayList<StockVO> stockVOs=new ArrayList<StockVO>();
        if(stockPOs!=null){
            int length=stockPOs.size();
            for (int i = 0; i < length; i++) {
                StockVO temp=new StockVO(stockPOs.get(i));
                stockVOs.add(temp);
            }
        }

        return  stockVOs;
    }

    /**
     * 用户注册时，获取14位的用户ID
     * @return 14位的用户ID
     */
    /*
    public String toUserID(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int date=calendar.get(Calendar.DATE);
        int hour=calendar.get(Calendar.HOUR);
        int minute=calendar.get(Calendar.MINUTE);
        int second=calendar.get(Calendar.SECOND);

        String yearStr=String.valueOf(year);
        String temp="00";
        String monthTemp=String.valueOf(month);
        String monthStr=temp.substring(0,2-monthTemp.length())+monthTemp;
        String dateTemp=String.valueOf(date);
        String dateStr=temp.substring(0,2-dateTemp.length())+dateTemp;
        String hourTemp=String.valueOf(hour);
        String hourStr=temp.substring(0,2-hourTemp.length())+hourTemp;
        String minuteTemp=String.valueOf(minute);
        String minuteStr=temp.substring(0,2-minuteTemp.length())+minuteTemp;
        String secondTemp=String.valueOf(second);
        String secondStr=temp.substring(0,2-secondTemp.length())+secondTemp;

        String userId=yearStr+monthStr+dateStr+hourStr+minuteStr+secondStr;

        return userId;
    }
*/

}
