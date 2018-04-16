import tushare as ts
from sqlalchemy import create_engine
import MySQLdb

db = MySQLdb.connect("localhost","root","2578","CSEIII-Data" )
cursor = db.cursor()
DB_CONNECT_STRING = 'mysql+mysqldb://root:2578@localhost/CSEIII-Data?charset=utf8'
engine = create_engine(DB_CONNECT_STRING)

df=ts.get_hist_data('sh','1996-8-0','2017-6-14')
name="sh"
sql = "CREATE TABLE " + name + """ (
           date  VARCHAR(20) NOT NULL,
           open VARCHAR(20) NOT NULL,
           high VARCHAR(20) NOT NULL,
           close VARCHAR(20) NOT NULL,
           low VARCHAR(20) NOT NULL,
           volume VARCHAR(20) NOT NULL,
           price_change VARCHAR(20) NOT NULL,
           p_change VARCHAR(20) NOT NULL,
           ma5 VARCHAR(20) NOT NULL,
           ma10 VARCHAR(20) NOT NULL,
           ma20 VARCHAR(20) NOT NULL,
           v_ma5 VARCHAR(20) NOT NULL,
           v_ma10 VARCHAR(20) NOT NULL,
           v_ma20 VARCHAR(20) NOT NULL)"""
cursor.execute(sql)
df.to_sql(name, engine, if_exists='append')

df=ts.get_hist_data('sz','1996-8-0','2017-6-14')
name="sz"
sql = "CREATE TABLE " + name + """ (
           date  VARCHAR(20) NOT NULL,
           open VARCHAR(20) NOT NULL,
           high VARCHAR(20) NOT NULL,
           close VARCHAR(20) NOT NULL,
           low VARCHAR(20) NOT NULL,
           volume VARCHAR(20) NOT NULL,
           price_change VARCHAR(20) NOT NULL,
           p_change VARCHAR(20) NOT NULL,
           ma5 VARCHAR(20) NOT NULL,
           ma10 VARCHAR(20) NOT NULL,
           ma20 VARCHAR(20) NOT NULL,
           v_ma5 VARCHAR(20) NOT NULL,
           v_ma10 VARCHAR(20) NOT NULL,
           v_ma20 VARCHAR(20) NOT NULL)"""
cursor.execute(sql)
df.to_sql(name, engine, if_exists='append')

df=ts.get_hist_data('hs300','1996-8-0','2017-6-14')
name="hs"
sql = "CREATE TABLE " + name + """ (
           date  VARCHAR(20) NOT NULL,
           open VARCHAR(20) NOT NULL,
           high VARCHAR(20) NOT NULL,
           close VARCHAR(20) NOT NULL,
           low VARCHAR(20) NOT NULL,
           volume VARCHAR(20) NOT NULL,
           price_change VARCHAR(20) NOT NULL,
           p_change VARCHAR(20) NOT NULL,
           ma5 VARCHAR(20) NOT NULL,
           ma10 VARCHAR(20) NOT NULL,
           ma20 VARCHAR(20) NOT NULL,
           v_ma5 VARCHAR(20) NOT NULL,
           v_ma10 VARCHAR(20) NOT NULL,
           v_ma20 VARCHAR(20) NOT NULL)"""
cursor.execute(sql)
df.to_sql(name, engine, if_exists='append')

df=ts.get_hist_data('zxb','1996-8-0','2017-6-14')
name="zxb"
sql = "CREATE TABLE " + name + """ (
           date  VARCHAR(20) NOT NULL,
           open VARCHAR(20) NOT NULL,
           high VARCHAR(20) NOT NULL,
           close VARCHAR(20) NOT NULL,
           low VARCHAR(20) NOT NULL,
           volume VARCHAR(20) NOT NULL,
           price_change VARCHAR(20) NOT NULL,
           p_change VARCHAR(20) NOT NULL,
           ma5 VARCHAR(20) NOT NULL,
           ma10 VARCHAR(20) NOT NULL,
           ma20 VARCHAR(20) NOT NULL,
           v_ma5 VARCHAR(20) NOT NULL,
           v_ma10 VARCHAR(20) NOT NULL,
           v_ma20 VARCHAR(20) NOT NULL)"""
cursor.execute(sql)
df.to_sql(name, engine, if_exists='append')

df=ts.get_hist_data('cyb','1996-8-0','2017-6-14')
name="cyb"
sql = "CREATE TABLE " + name + """ (
           date  VARCHAR(20) NOT NULL,
           open VARCHAR(20) NOT NULL,
           high VARCHAR(20) NOT NULL,
           close VARCHAR(20) NOT NULL,
           low VARCHAR(20) NOT NULL,
           volume VARCHAR(20) NOT NULL,
           price_change VARCHAR(20) NOT NULL,
           p_change VARCHAR(20) NOT NULL,
           ma5 VARCHAR(20) NOT NULL,
           ma10 VARCHAR(20) NOT NULL,
           ma20 VARCHAR(20) NOT NULL,
           v_ma5 VARCHAR(20) NOT NULL,
           v_ma10 VARCHAR(20) NOT NULL,
           v_ma20 VARCHAR(20) NOT NULL)"""
cursor.execute(sql)
df.to_sql(name, engine, if_exists='append')