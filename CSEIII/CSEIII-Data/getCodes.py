import tushare as ts
from sqlalchemy import create_engine
import MySQLdb

db = MySQLdb.connect("localhost","root","2578","CSEIII-Data" )
cursor = db.cursor()
DB_CONNECT_STRING = 'mysql+mysqldb://root:2578@localhost/CSEIII-Data?charset=utf8'
engine = create_engine(DB_CONNECT_STRING)

sql="SELECT code FROM stocksbasic "
cursor.execute(sql)

for index in cursor.fetchall():
    df = ts.get_hist_data(str(index).split('(\'')[1].split('\'')[0], "2014-6-14", "2017-6-14")
    if df is None:
        pass
    else:
        name = 's' + str(index).split('(\'')[1].split('\'')[0]
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
            v_ma20 VARCHAR(20) NOT NULL,
            turnover VARCHAR(20) NOT NULL)"""
        cursor.execute(sql)
        df.to_sql(name, engine, if_exists='append')
