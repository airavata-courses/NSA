FROM python:3-onbuild

ADD dataretrieval.py /
RUN pip3 install --upgrade pip
ADD requirements.txt /
RUN pip3 install -r requirements.txt

CMD [ "python3", "./dataretrieval.py" ]