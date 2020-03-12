FROM python:3.7.3

ADD postprocessing.py /

RUN pip install --upgrade pip

ADD requirements.txt /
RUN pip3 install -r requirements.txt

ENV PYTHONUNBUFFERED 0
CMD [ "python3", "-u" , "./postprocessing.py" ]
