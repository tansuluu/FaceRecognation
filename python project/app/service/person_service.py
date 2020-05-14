from app.repository.native_query import get_people_to_process, update_person, get_people_by_id
import face_recognition

directory = "../upload-dir/"


def start_process_person():
    from app import log
    import pickle

    person = get_people_to_process()
    try:
        if len(person) < 1:
            print("No person to process")
            return
        image = face_recognition.load_image_file(directory + person[0].file_name)
        encoding = face_recognition.face_encodings(image)[0]
        update_person(encoding, person[0].id)
        # person = get_people_by_id(person[0].id)
        # face_data = pickle.loads(person[0].face_encodings)
    except Exception as e:
        log("Exception in start_process_person " + str(e))

