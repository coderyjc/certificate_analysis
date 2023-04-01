"""
Author: Yan Jingcun
Date: 2023-03-11
Time: 16:00:57
Description: 导入excel表格到数据库中
"""

import pandas as pd
from sqlalchemy import create_engine

USER_NAME = "root"
PASSWORD = "333"
IP_ADDRESS = "localhost"
PORT = 3306
DB_NAME = "certificate_analysis"

# 初始化数据库连接
# 按实际情况依次填写MySQL的用户名、密码、IP地址、端口、数据库名
engine = create_engine("mysql+pymysql://{}:{}@{}:{}/{}"
                       .format(USER_NAME, PASSWORD, IP_ADDRESS, PORT, DB_NAME))

xlsx_data = pd.read_excel(io='./data/2013年认定.xlsx', sheet_name=None)

for key, value in xlsx_data.items():
    # 获取列的个数
    len_column = len(list(value))
    # 6列是证书
    if len_column == 6:
        value.to_sql('certificate', engine, if_exists='append',index=False)
    # 7列是面试成绩
    elif len_column == 7:
        value.to_sql('interview_score', engine, if_exists='append',index=False)
    # 11列是笔试成绩
    elif len_column == 11:
        value.to_sql('written_score', engine, if_exists='append',index=False)
    # 17列是认定成绩
    elif len_column == 17:
        value.to_sql('identification', engine, if_exists='append',index=False)
    else: 
        # 返回错误
        pass