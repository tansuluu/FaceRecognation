import logging
import os

from flask import Blueprint
from flask import Flask
from flask_sqlalchemy import SQLAlchemy

from app.jobs.jobs import run_schedule
from config import config
# from .routes.main import mod as main_blueprint
mod = Blueprint('home', __name__,
                template_folder='templates',
                static_folder='static')
if __name__ == 'app':
    logging.basicConfig(filename='app.log', level=logging.DEBUG)
    app = Flask(__name__)
    log = app.logger.info
    log("Created App")
    app.secret_key = config.SECRET_KEY

    app.config.update(
        SQLALCHEMY_DATABASE_URI=config.DATABASE_URI,
        SQLALCHEMY_TRACK_MODIFICATIONS=False,
        SQLALCHEMY_POOL_SIZE=200
    )

    db = SQLAlchemy(app)

    os.environ['IMG_DIR'] = "/var/ves/uploads/"
    os.environ["NLS_LANG"] = "AMERICAN_AMERICA.UTF8"
    app.register_blueprint(mod)

    if os.environ.get('job_started') is None:
        run_schedule()
        os.environ['job_started'] = 'STARTED'
