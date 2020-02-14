FROM python:3

ADD postprocessing.py /
ADD pyart-master /
RUN pip3 install numpy 
RUN pip3 install pandas
RUN pip3 install scipy
RUN pip3 install netCDF4
RUN pip3 install kafka
RUN pip3 install kafka-python
RUN pip3 install matplotlib

RUN python3 /pyart-master/setup.py build
RUN python3 /pyart-master/setup.py install --user

CMD [ "python3", "./postprocessing.py" ]
