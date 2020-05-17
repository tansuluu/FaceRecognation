import atexit
import datetime

from apscheduler.schedulers.background import BackgroundScheduler

from app.service.person_service import start_process_person
from app.service.request_service import request_process


def run_request():
    from app import app, log
    log("in run_request")
    print("in run_request")
    request_process()
    print("after run_request")


def process_person():
    from app import app, log
    log("in process_person")
    print("in process_person")
    start_process_person()
    print("after process_person")

def run_schedule():
    from app import app, log

    if not app.debug:
        scheduler = BackgroundScheduler()
        scheduler.add_job(run_request, 'interval', seconds=20, replace_existing=True, id='verify_new_subscribers')
        scheduler.add_job(process_person, 'interval', seconds=20, replace_existing=True, id='process_person')

        app.logger.info("Added jobs")
        scheduler.start()
        log("Started scheduler")
        atexit.register(lambda: scheduler.shutdown())

