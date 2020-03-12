FROM python:3.7.3

ADD postprocessing.py /
ADD pyart-master /


RUN pip install --upgrade pip
ADD requirements.txt /
RUN pip3 install -r requirements.txt

RUN python /pyart-master/setup.py build
RUN python /pyart-master/setup.py install --user

ENV PYTHONUNBUFFERED 0
CMD [ "python3", "-u" , "./postprocessing.py" ]
