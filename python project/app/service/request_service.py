from app.repository.native_query import get_request, update_request
from app.service.face_service import recognize


def request_process():
    # try:
    request = get_request()
    if len(request) < 1:
        print("No requests to process")
        return
    update_request("IN_PROGRESS", request[0].id)
    print("Face recognition started for " + str(request[0].id))
    recognize(request[0].fileName, request[0].id)
    print("Face recognition finished for " + str(request[0].id))
    # except:
    #     print("Error occurred in request_process")
