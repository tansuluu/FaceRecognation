import face_recognition
from PIL import Image, ImageDraw
import numpy as np

from app.repository.native_query import save_request_result, update_request, get_request_process, \
    get_people

directory = "../upload-dir/"


def recognize(request):
    request_processes = get_request_process(request.id)
    if len(request_processes) < 1:
        return
    known_face_encodings, known_face_names, known_face_id = get_known_faces(request)
    for process in request_processes:
        unknown_image = face_recognition.load_image_file(directory + process.file_name)

        # Find all the faces and face encodings in the unknown image
        face_locations = face_recognition.face_locations(unknown_image)
        face_encodings = face_recognition.face_encodings(unknown_image, face_locations)

        # Convert the image to a PIL-format image so that we can draw on top of it with the Pillow library
        pil_image = Image.fromarray(unknown_image)
        # Create a Pillow ImageDraw Draw instance to draw with
        draw = ImageDraw.Draw(pil_image)

        # Loop through each face found in the unknown image
        for (top, right, bottom, left), face_encoding in zip(face_locations, face_encodings):
            # See if the face is a match for the known face(s)
            matches = face_recognition.compare_faces(known_face_encodings, face_encoding)

            name = "Unknown"

            # Or instead, use the known face with the smallest distance to the new face
            face_distances = face_recognition.face_distance(known_face_encodings, face_encoding)
            best_match_index = np.argmin(face_distances)
            if matches[best_match_index]:
                name = known_face_names[best_match_index]
                person_id = known_face_id[best_match_index]
                # Draw a box around the face using the Pillow module
                draw.rectangle(((left, top), (right, bottom)), outline=(0, 0, 255))

                # Draw a label with a name below the face
                text_width, text_height = draw.textsize(name)
                draw.rectangle(((left, bottom - text_height), (right, bottom)), fill=(0, 0, 255), outline=(0, 0, 255))
                draw.text((left + 6, bottom - text_height), name, fill=(255, 255, 255, 255))
                pil_image.save(directory + "result_" + process.file_name)

                result = int((1 - face_distances[best_match_index]+.15)*100)
                if result > 100:
                    result = 99
                save_request_result("result_" + process.file_name, process.id, result, person_id)

        update_request("FINISHED", request.id)


def get_known_faces(request):
    import pickle

    known_face_encodings=[]
    known_face_names=[]
    known_face_id=[]

    people = get_people(request)
    for person in people:
        face_data = pickle.loads(person.face_encodings)
        known_face_encodings.append(face_data)
        known_face_names.append(person.surname + " " + person.name)
        known_face_id.append(person.id)

    return known_face_encodings, known_face_names, known_face_id

