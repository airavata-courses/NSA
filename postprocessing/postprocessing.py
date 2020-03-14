import json
import sys
import matplotlib.pyplot as plt
import pyart
import gzip
import os
import time
import warnings

with warnings.catch_warnings():
    warnings.filterwarnings("ignore",category=DeprecationWarning)
    import pyart,matplotlib

def plot_radar(filename):
    counter = 0
    radar = pyart.io.read_nexrad_archive(filename)
    display = pyart.graph.RadarDisplay(radar)
    fig = plt.figure(figsize=(8, 7))

    # plot super resolution reflectivity and Velocity
    ax1 = fig.add_subplot(221)
    display.plot('reflectivity', 0, title='NEXRAD Reflectivity',vmin=-32, vmax=64, colorbar_label='', ax=ax1)
    display.plot_range_ring(radar.range['data'][-1]/1000., ax=ax1)
    display.set_limits(xlim=(-60, 0), ylim=(-25, 30), ax=ax1)
    ax2 = fig.add_subplot(222)
    display.plot('velocity', 1, title='NEXRAD Velocity',vmin=-50, vmax=50, colorbar_label='', ax=ax2)
    display.plot_range_ring(radar.range['data'][-1]/1000., ax=ax2)
    display.set_limits(xlim=(-60, 0), ylim=(-25, 30), ax=ax2)
    counter += 1
    src_folder = os.path.join('..','nwp_ui','src')
    plot_name = os.path.join(src_folder, str(counter) + '.png')
    print('saving plot to', plot_name)
    plt.savefig(plot_name)
    outputString = 'success'
    return outputString

from kafka import KafkaConsumer
from kafka import KafkaProducer

def compute():
    bootstrap_servers = ['kafka:9092']
    consumer = None
    while consumer is None:
        try:
            consumer = KafkaConsumer(
                'dataretrieval-postprocess',
                bootstrap_servers=bootstrap_servers,
                auto_offset_reset='earliest',
                enable_auto_commit=True,
                group_id='group1',
                value_deserializer=lambda x: json.loads(x.decode('utf-8')))
        except:
            print("Connection to broker failed. Retrying in 1s...")
            time.sleep(1)
    print("Connected to Kafka Broker")
    result=[]
    try:
        for message in consumer:
            print ("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition,message.offset, message.key,message.value))
            print(type(message))

            # result = json.loads(message.value)
            print(message.value)
            result = message.value['scans']
            res = message.value['pp']

            for filename in result:
                outputString = plot_radar(filename)
            producer = KafkaProducer(
                bootstrap_servers=bootstrap_servers,
                retries=5,
                value_serializer=lambda m: json.dumps(m).encode('utf-8'))
            ack = producer.send('postprocess-messagehandler', value=outputString)
            metadata = ack.get()
            print(metadata.topic)
            print(metadata.partition)
            print('sent', outputString, 'to postprocess-messagehandler')
            print('before',res)
            res["jobtype"]="Post-Process"
            res["output"]=plot_name
            print('after',res)


            sess_producer = KafkaProducer(
                bootstrap_servers = bootstrap_servers,
                retries = 5,
                value_serializer=lambda m: json.dumps(m).encode('utf-8'))
            sess_ack = sess_producer.send('postprocess-sessionmgmt', value = res)
            metadata_sess = sess_ack.get()
            print(metadata_sess.topic)
            print(metadata_sess.partition)

    except KeyboardInterrupt:
        sys.exit()
    print('main')

    if consumer is not None:
        consumer.close()

if  __name__ == "__main__":
    print("started post processing service!!")
    compute()