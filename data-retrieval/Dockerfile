FROM python:3.7.3

RUN pip3 install --upgrade pip
WORKDIR /data_retrieval

ADD dataretrieval.py /data_retrieval
ADD requirements.txt /data_retrieval
RUN pip3 install -r requirements.txt
ENV PYTHONUNBUFFERED 0
CMD [ "python3", "-u", "dataretrieval.py" ]