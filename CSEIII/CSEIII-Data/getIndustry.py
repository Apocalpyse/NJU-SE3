import tushare as ts;
from sqlalchemy import create_engine

df=ts.get_industry_classified('sina')
engine=create_engine('mysql://root:2578@127.0.0.1/CSEIII-Data?charset=utf8')

df.to_sql('industry',engine,if_exists='append')