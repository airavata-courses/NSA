from kafka import KafkaConsumer
from kafka import KafkaProducer
import json
import sys
import matplotlib.pyplot as plt
import pyart
import os

bootstrap_servers = ['localhost:9092']
consumer = KafkaConsumer(
    'dataretrieval-postprocess',
     bootstrap_servers=bootstrap_servers,
     auto_offset_reset='earliest',
     enable_auto_commit=True,
     group_id='group1',
     value_deserializer=lambda x: json.loads(x.decode('utf-8')))

result=[]
try:
    for message in consumer:
        print ("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition,message.offset, message.key,message.value))
        print(type(message))

        # result = json.loads(message.value)
        result = message.value['scans']

        counter = 0

        for filename in result:
            radar = pyart.io.read_nexrad_archive(filename)
            display = pyart.graph.RadarDisplay(radar)
            fig = plt.figure(figsize=(6, 5))

            # plot super resolution reflectivity
            ax = fig.add_subplot(111)
            display.plot('reflectivity', 0, title='NEXRAD Reflectivity',vmin=-32, vmax=64, colorbar_label='', ax=ax)
            display.plot_range_ring(radar.range['data'][-1] / 1000., ax=ax)
            display.set_limits(xlim=(-500, 500), ylim=(-500, 500), ax=ax)
            counter += 1
            src_folder = os.path.join('..','nwp_ui','src')
            plot_name = os.path.join(src_folder, str(counter) + '.png')
            print('saving plot to', plot_name)
            plt.savefig(plot_name)

        # outputString = os.path.join(os.getcwd(),"output", str(counter) + '.png')
        outputString = 'success'
        producer = KafkaProducer(
            bootstrap_servers=bootstrap_servers,
            retries=5,
            value_serializer=lambda m: json.dumps(m).encode('utf-8'))
        ack = producer.send('postprocess-messagehandler', value=outputString)
        print('sent', outputString, 'to postprocess-messagehandler')


except KeyboardInterrupt:
    sys.exit()
print('main')


if consumer is not None:
    consumer.close()

metadata = ack.get()
print(metadata.topic)
print(metadata.partition)
