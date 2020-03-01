def update_request(status, id):
    from app import db
    query = 'update REQUEST set STATUS = :stageStatus where id= :id '
    db.engine.execute(query, {'stageStatus': status, 'id': id})
    db.session.close()

def get_request():
    from app import db
    queryPassFront = 'SELECT * FROM user order by created_date desc limit 1'
    result = db.engine.execute(queryPassFront).fetchall()
    db.session.close()