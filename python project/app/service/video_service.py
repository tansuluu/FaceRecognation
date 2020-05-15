import cv2
import face_recognition
import app.repository.orm_query as orm_query
import app.services.file.file_service as file_service
import app.services.multimedia.ffmpeg as ffmpeg

import os
directory = "../upload-dir/frame/"

#
# def get_file_size_in_bytes(file_path):
#     size = os.path.getsize(file_path)
#     return size


def validateVideo(stage):
    """
    Validates that video contains subscriber face and his passport, and saves optimal frame(where both face and passport are clearly seen) into filesystem as image.
    :param stage: Stage entity
    """

    from app import log
    try:

        snapshot, found = extractOptimalSnapshotFromVideo(path_to_modified_video, stage)
        log.info("validateVideo stage snapshot extracted " + str(stage.id))
    except Exception as e:
        log.exception("Failed to validate following video " + str(e))


def extractOptimalSnapshotFromVideo(pathToVideo, process):

    from app import log

    cap = cv2.VideoCapture(pathToVideo)

    log.debug("Searching optimal snapshot on video " + pathToVideo)

    length = int(cap.get(cv2.CAP_PROP_FRAME_COUNT))
    step = int(length / 30)
    if step == 0:
        step = 1
    i = -1
    frames = []
    names = []
    while True:
        # Capture frame-by-frame
        ret, frame = cap.read()

        if frame is None:
            break
        i += 1
        if i % step != 0:
            continue

        if ret is True:
            face_locations = face_recognition.face_locations(frame, number_of_times_to_upsample=1, model="hog")
            faces = []

            if len(face_locations) > 1:
                # sort faces, from the biggest one on the left to smallest on the right
                faces.sort(reverse=True, key=lambda f: f.size)

                if len(faces) > 0 and frame.any():
                    cv2.imwrite(directory + i+".jpg", frame)
                    names.append(i+".jpg")
                    frames.append(frame)

    # for i in range(0, len(names)):
    #     max_size = 0
    #     size = get_file_size_in_bytes(directory+names[i])
    #     if max_size < size:
    #         max_size = size
    #     if int(max_size/2) > size:
    #         _delete_temp_file(directory+names[i])
    cap.release()
    cv2.destroyAllWindows()

    return frames


def get_frames_from_video(path_to_video, frames_count_to_get):
    frames = []

    cap = cv2.VideoCapture(path_to_video)

    frame_count = cap.get(cv2.CAP_PROP_FRAME_COUNT)
    frames_per_sec = cap.get(cv2.CAP_PROP_FPS)
    if frame_count == 0 or frames_per_sec == 0:
        raise Exception("Empty video(no frames).")

    step = int(frame_count / frames_count_to_get)
    start_frame = int(step / 2)
    for fr_pos in range(start_frame, int(frame_count), step):
        # set frame position
        cap.set(cv2.CAP_PROP_POS_FRAMES, fr_pos)
        ret, frame = cap.read()
        if ret:
            frames.append(frame)

    # When everything done, release the capture
    cap.release()
    cv2.destroyAllWindows()

    return frames


def _delete_temp_file(path_to_file):
    import os
    from app import log
    try:
        os.remove(path_to_file)
        log.debug("Deleted " + path_to_file)
    except Exception as e:
        log.error("Failed to delete temp video file {}: {}".format(path_to_file, e))
