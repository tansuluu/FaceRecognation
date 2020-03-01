import atexit
import datetime

from apscheduler.schedulers.background import BackgroundScheduler

from app.service.request_service import request_process


def privet():
    print("priver")
    from app import db
    queryPassFront = 'SELECT * FROM user order by created_date desc limit 1'
    result = db.engine.execute(queryPassFront).fetchall()
    db.session.close()
    print(result[0].full_name)

def run_request():
    request_process()


def run_schedule():
    from app import app, log

    if not app.debug:
        scheduler = BackgroundScheduler()
        scheduler.add_job(run_request, 'interval', seconds=10, replace_existing=True, id='verify_new_subscribers')

        app.logger.info("Added jobs")
        scheduler.start()
        log("Started scheduler")
        atexit.register(lambda: scheduler.shutdown())

