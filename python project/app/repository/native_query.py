def update_request(status, id):
    from app import db
    query = 'update REQUEST set status = %s  where id = %s'
    db.engine.execute(query, (status,  id))
    db.session.close()


def get_request():
    from app import db
    queryPassFront = 'SELECT * FROM REQUEST where status="NEW" order by created_date desc limit 1'
    result = db.engine.execute(queryPassFront).fetchall()
    db.session.close()
    return result


def save_request_result(file_name, id, percentage,name, group_cl):
    from app import db
    query = 'insert into request_result(CREATED_DATE, file_name, request_id, percentage, full_name, group_class) ' \
            'values(current_date ,%s, %s, %s, %s, %s)'
    db.engine.execute(query, (file_name, id, str(percentage),name, group_cl))
    db.session.close()