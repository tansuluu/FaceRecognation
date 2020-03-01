from config.dev import *

DATABASE_URI_FMT = "mysql+pymysql://%(db_user)s:%(db_password)s@%(host)s:%(port)s/%(sid)s"

DATABASE_URI = DATABASE_URI_FMT % {'db_user': USER,
                                   'db_password': PASSWORD,
                                   'host': HOST,
                                   'port': PORT,
                                   'sid': SID}
