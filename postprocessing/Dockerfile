FROM python:3.7.3

ADD postprocessing.py /

RUN pip install --upgrade pip
ADD requirements.txt /
RUN pip install matplotlib
RUN pip install pika
RUN pip install nexradaws
RUN pip install numpy
RUN pip install arm-pyart
RUN pip install netCDF4
RUN pip install scipy
RUN pip install pandas
RUN pip3 install -r requirements.txt

ENV PYTHONUNBUFFERED 0
CMD [ "python3", "-u" , "./postprocessing.py" ]
