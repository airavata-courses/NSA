FROM python:3-onbuild

ADD dataretrieval.py /
RUN pip install --upgrade pip
ADD requirements.txt /
RUN pip3 install -r requirements.txt
RUN echo "RUN DONE"

CMD [ "python3", "./dataretrieval.py" ]