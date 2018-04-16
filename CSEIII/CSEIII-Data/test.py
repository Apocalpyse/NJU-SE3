import tushare as ts
from sqlalchemy import create_engine
import MySQLdb
import sys

reload(sys)
sys.setdefaultencoding("utf-8")

df=ts.get_stock_basics()
print df