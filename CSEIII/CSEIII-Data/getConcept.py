import tushare as ts;
from sqlalchemy import create_engine

df=ts.get_concept_classified()
engine=create_engine('mysql://root:2578@127.0.0.1/CSEIII-Data?charset=utf8')

df.to_sql('concept', engine, if_exists='append')