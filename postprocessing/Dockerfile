FROM python:3

ADD postprocessing.py /

RUN pip install --upgrade pip

RUN pip3 install matplotlib
RUN pip3 install kafka-python
RUN pip3 install nexradaws
RUN pip3 install numpy==1.16.1
RUN pip3 install arm-pyart
RUN pip3 install netCDF4
RUN pip3 install scipy
RUN pip3 install pandas

ENV PYTHONUNBUFFERED 0
CMD [ "python3", "-u" , "./postprocessing.py" ]
