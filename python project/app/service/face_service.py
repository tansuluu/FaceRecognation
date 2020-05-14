import cv2
import face_recognition
from PIL import Image, ImageDraw
import numpy as np

from app.repository.native_query import save_request_result, update_request

# CURRENT_PATH = "C:/Users/tanya/Desktop/FaceRecognation/upload-dir/"
# obama_image = face_recognition.load_image_file("C:/Users/tanya/Desktop/33.jpg")
# obama_face_encoding = face_recognition.face_encodings(obama_image)[0]
#
# ttt = face_recognition.load_image_file("C:/Users/tanya/Desktop/5.jpg")
# ttt_encoding = face_recognition.face_encodings(ttt)[0]
#
# # Load a second sample picture and learn how to recognize it.
# biden_image = face_recognition.load_image_file("C:/Users/tanya/Desktop/6.jpg")
# biden_face_encoding = face_recognition.face_encodings(biden_image)[0]
#
# # Create arrays of known face encodings and their names
# known_face_encodings = [
#     obama_face_encoding,
#     ttt_encoding,
#     biden_face_encoding
# ]
# known_face_names = [
#     "Zyndanbaeva Asema",
#     "Sultanov Nadyr",
#     "Myrzaeva Tansuluu"
# ]


def recognize(file_name, request_id):
    # Load an image with an unknown face

    unknown_image = face_recognition.load_image_file(CURRENT_PATH + file_name)

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

        # If a match was found in known_face_encodings, just use the first one.
        # if True in matches:
        #     first_match_index = matches.index(True)
        #     name = known_face_names[first_match_index]

        # Or instead, use the known face with the smallest distance to the new face
        face_distances = face_recognition.face_distance(known_face_encodings, face_encoding)
        best_match_index = np.argmin(face_distances)
        if matches[best_match_index] and face_distances[best_match_index] < .5:
            name = known_face_names[best_match_index]

            # Draw a box around the face using the Pillow module
            draw.rectangle(((left, top), (right, bottom)), outline=(0, 0, 255))

            # Draw a label with a name below the face
            text_width, text_height = draw.textsize(name)
            draw.rectangle(((left, bottom - text_height), (right, bottom)), fill=(0, 0, 255), outline=(0, 0, 255))
            draw.text((left + 6, bottom - text_height), name, fill=(255, 255, 255, 255))
            pil_image.save(CURRENT_PATH + "result_" + file_name)
            save_request_result("result_" + file_name, request_id, int((1 - face_distances[best_match_index]+.15)*100), name, 'com16')
            update_request("SUCCESS", request_id)
            return

    update_request("UNKNOWN", request_id)

