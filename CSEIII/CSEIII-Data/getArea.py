import tushare as ts
from sqlalchemy import create_engine

df = ts.get_area_classified()
engine = create_engine('mysql://root:2578@127.0.0.1/CSEIII-Data?charset=utf8')

df.to_sql('area', engine, if_exists='append')