import atexit
import datetime

from apscheduler.schedulers.background import BackgroundScheduler

def privet():
    print("priver")
    from app import db
    queryPassFront = 'SELECT * FROM user order by created_date desc limit 1'
    result = db.engine.execute(queryPassFront).fetchall()
    db.session.close()
    print(result[0].full_name)

# def run_request():



def run_schedule():
    from app import app, log

    if not app.debug:
        scheduler = BackgroundScheduler()
        # scheduler = BlockingScheduler()
        # it is also possible to enable the API directly
        # scheduler.add_jobstore('sqlalchemy', engine=db.engine)

        scheduler.add_job(privet, 'interval', seconds=10, replace_existing=True, id='verify_new_subscribers')

        app.logger.info("Added jobs")
        scheduler.start()
        log("Started scheduler")
        atexit.register(lambda: scheduler.shutdown())

