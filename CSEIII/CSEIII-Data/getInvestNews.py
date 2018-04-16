import tushare as ts;
from sqlalchemy import create_engine
import sys

reload(sys)
sys.setdefaultencoding("utf-8")

df=ts.get_latest_news(100, True)
engine=create_engine('mysql://root:2578@127.0.0.1/CSEIII-Data?charset=utf8')
df.to_sql('investnews', engine, if_exists='append')



