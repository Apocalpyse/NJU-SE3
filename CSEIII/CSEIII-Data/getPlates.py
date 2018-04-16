import tushare as ts;
from sqlalchemy import create_engine
import MySQLdb

df=ts.get_index()
db = MySQLdb.connect("localhost","root","2578","CSEIII-Data" )
cursor = db.cursor()
engine=create_engine('mysql://root:2578@127.0.0.1/CSEIII-Data?charset=utf8')
print df

name='plates'
sql = "CREATE TABLE " + name + """ (
           code VARCHAR(20) NOT NULL,
           name VARCHAR(20) NOT NULL,
           change VARCHAR(20) NOT NULL,
           open VARCHAR(20) NOT NULL,
           preClose VARCHAR(20) NOT NULL,
           close VARCHAR(20) NOT NULL,
           high VARCHAR(20) NOT NULL,
           low VARCHAR(20) NOT NULL,
           volume VARCHAR(20) NOT NULL,
           amount VARCHAR(20) NOT NULL)"""
cursor.execute(sql)
df.to_sql(name, engine, if_exists='append')