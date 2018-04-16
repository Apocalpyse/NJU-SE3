import tushare as ts;
from sqlalchemy import create_engine
import MySQLdb
import sys

reload(sys)
sys.setdefaultencoding("utf-8")

df=ts.get_stock_basics()
print df
db=MySQLdb.connect("localhost","root","2578","CSEIII-Data")
cursor=db.cursor()
engine=create_engine('mysql://root:2578@127.0.0.1/CSEIII-Data?charset=utf8')
name="stocksbasic"
sql = "CREATE TABLE "+name+""" (
            code  VARCHAR(20) NOT NULL,
            name VARCHAR(20) NOT NULL,
            industry VARCHAR(20) NOT NULL,
            area VARCHAR(20) NOT NULL,
            pe VARCHAR(20) NOT NULL,
            outstanding VARCHAR(20) ,
            totals VARCHAR(20) ,
            totalAssets VARCHAR(20) ,
            liquidAssets VARCHAR(20) ,
            fixedAssets VARCHAR(20) ,
            reserved VARCHAR(20) ,
            reservedPerShare VARCHAR(20) ,
            esp VARCHAR(20) ,
            bvps VARCHAR(20) ,
            pb VARCHAR(20) ,
            timeToMarket VARCHAR(20) NOT NULL,
            undp VARCHAR(20) ,
            perundp VARCHAR(20) ,
            rev VARCHAR(20) ,
            profit VARCHAR(20) ,
            gpr VARCHAR(20) ,
            npr VARCHAR(20) ,
            holders VARCHAR(20) )"""
cursor.execute(sql)
df.to_sql(name, engine, if_exists='append')