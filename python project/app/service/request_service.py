from app.repository.native_query import get_request, update_request
from app.service.face_service import recognize


def request_process():
    from app import log

    try:
        request = get_request()
        if len(request) < 1:
            print("No requests to process")
            return
        update_request("IN_PROGRESS", request[0].id)
        print("Face recognition started for " + str(request[0].id))
        log("Face recognition started for " + str(request[0].id))
        recognize(request[0])
        print("Face recognition finished for " + str(request[0].id))
        log("Face recognition finished for " + str(request[0].id))
    except Exception as e:
        log("Exception in request_process " + str(e))
