def update_request(status, id):
    from app import db
    query = 'update REQUEST set status = %s  where id = %s'
    db.engine.execute(query, (status,  id))
    db.session.close()


def get_request():
    from app import db
    query = 'SELECT * FROM REQUEST where status="NEW" order by created_date desc limit 1'
    result = db.engine.execute(query).fetchall()
    db.session.close()
    return result


def save_request_result(file_name, process_id, percentage, person_id):
    from app import db
    query = 'insert into request_result(CREATED_DATE, file_name, request_process_id, percentage, person_id) ' \
            'values(current_date ,%s, %s, %s, %s)'
    db.engine.execute(query, (file_name, process_id, str(percentage), person_id))
    db.session.close()


def get_people_to_process():
    from app import db
    query = 'SELECT * FROM PERSON where face_encodings is null order by created_date desc limit 1'
    result = db.engine.execute(query).fetchall()
    db.session.close()
    return result


def update_person(face_encodings, id):
    import pickle
    from app import db
    face_pickled_data = pickle.dumps(face_encodings)
    query = 'update PERSON set face_encodings = %s  where id = %s'
    db.engine.execute(query, (face_pickled_data,  id))
    db.session.close()


def get_people_by_id(id):
    from app import db
    query = 'SELECT * FROM PERSON where id = %s order by created_date desc limit 1'
    result = db.engine.execute(query, (id)).fetchall()
    db.session.close()
    return result


def get_request_process(request_id):
    from app import db
    query = 'SELECT * FROM request_process where request_id = %s order by created_date desc'
    result = db.engine.execute(query, (request_id)).fetchall()
    db.session.close()
    return result


def get_people(request):
    from app import db
    if request.gender is not None and request.person_position is not None:
        query = 'SELECT * FROM PERSON where face_encodings is not null and removed_date is null and gender=%s and person_position=%s order by created_date desc'
        result = db.engine.execute(query, (request.gender, request.person_position)).fetchall()
    elif request.gender is not None and request.person_position is None:
        query = 'SELECT * FROM PERSON where face_encodings is not null and removed_date is null and gender=%s order by created_date desc'
        result = db.engine.execute(query, (request.gender)).fetchall()
    elif request.gender is None and request.person_position is not None:
        query = 'SELECT * FROM PERSON where face_encodings is not null and removed_date is null and person_position=%s order by created_date desc'
        result = db.engine.execute(query, (request.person_position)).fetchall()
    else:
        query = 'SELECT * FROM PERSON where face_encodings is not null and removed_date is null order by created_date desc'
        result = db.engine.execute(query).fetchall()
    db.session.close()
    return result
