import tushare as ts
from sqlalchemy import create_engine
import MySQLdb
import sys

reload(sys)
sys.setdefaultencoding("utf-8")

db = MySQLdb.connect("localhost","root","2578","CSEIII-Data" )
cursor = db.cursor()
DB_CONNECT_STRING = 'mysql+mysqldb://root:2578@localhost/CSEIII-Data?charset=utf8'
engine = create_engine(DB_CONNECT_STRING)

sql="SELECT code FROM codelist "
cursor.execute(sql)

for index in cursor.fetchall():
    df = ts.get_notices(str(index).split('(\'')[1].split('\'')[0])
    if df is None:
        pass
    else:
        name = 'news' + str(index).split('(\'')[1].split('\'')[0]
        df.to_sql(name, engine, if_exists='append')