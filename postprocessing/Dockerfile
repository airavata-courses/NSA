FROM python:3-onbuild

ADD postprocessing.py /
ADD pyart-master /


RUN pip install --upgrade pip
ADD requirements.txt /
RUN pip3 install -r requirements.txt

RUN python /pyart-master/setup.py build
RUN python /pyart-master/setup.py install --user

RUN echo "RUN DONE"

CMD [ "python3", "./postprocessing.py" ]
