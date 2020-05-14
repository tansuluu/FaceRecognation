import cv2
import face_recognition
from PIL import Image, ImageDraw
import numpy as np

from app.repository.native_query import save_request_result, update_request, get_request_process, get_all_people

directory = "../upload-dir/"


# known_face_encodings = [ ]
# known_face_names = [
#     "Zyndanbaeva Asema",
#     "Sultanov Nadyr",
#     "Myrzaeva Tansuluu"
# ]


def recognize(request_id):
    request_processes = get_request_process(request_id)
    if len(request_processes) < 1:
        return
    known_face_encodings, known_face_names, known_face_id = get_known_faces()
    for process in request_processes:
        unknown_image = face_recognition.load_image_file(directory + process.file_name)

        # Find all the faces and face encodings in the unknown image
        face_locations = face_recognition.face_locations(unknown_image)
        face_encodings = face_recognition.face_encodings(unknown_image, face_locations)

        # Convert the image to a PIL-format image so that we can draw on top of it with the Pillow library
        # See http://pillow.readthedocs.io/ for more about PIL/Pillow
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
            if matches[best_match_index] and face_distances[best_match_index] < .1:
                name = known_face_names[best_match_index]

                # Draw a box around the face using the Pillow module
                draw.rectangle(((left, top), (right, bottom)), outline=(0, 0, 255))

                # Draw a label with a name below the face
                text_width, text_height = draw.textsize(name)
                draw.rectangle(((left, bottom - text_height), (right, bottom)), fill=(0, 0, 255), outline=(0, 0, 255))
                draw.text((left + 6, bottom - text_height), name, fill=(255, 255, 255, 255))
                pil_image.save(directory + "result_" + process.file_name)
                save_request_result("result_" + file_name, request_id, int((1 - face_distances[best_match_index]+.15)*100), name, 'com16')
                update_request("SUCCESS", request_id)
                return

        update_request("UNKNOWN", request_id)


def get_known_faces():
    import pickle

    known_face_encodings=[]
    known_face_names=[]
    known_face_id=[]

    people = get_all_people()
    for person in people:
        face_data = pickle.loads(person.face_encodings)
        known_face_encodings.append(face_data)
        known_face_names.append(person.surname + " " + person.name)
        known_face_id.append(person.id)

    return known_face_encodings, known_face_names, known_face_id

