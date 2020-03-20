import json
import sys
import matplotlib.pyplot as plt
import pyart
from kafka import KafkaConsumer
from kafka import KafkaProducer
# import tempfile
from datetime import datetime
import os
import time
import nexradaws

def plot_radar(filename):
    print('Inside radar plot')
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
    plot_name = '1.png'
    print('Saving plot to', plot_name)
    plt.savefig("output/"+plot_name)
    print('Plot saved')
    outputString = 'success'

    return outputString,plot_name

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
    conn = nexradaws.NexradAwsInterface()
    result=[]
    try:
        for message in consumer:
            print ("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition,message.offset, message.key,message.value))
            print(message.value)
            res = message.value['pp']

            years = res['year']
            months = res['month']
            days = res['day']
            radars = res['radarID']
            userID = res['userID']

            print(years, months, days, radars)
            scans = conn.get_avail_scans(years, months, days, radars)

            # templocation = tempfile.mkdtemp()
            results = conn.download(scans[0:1], "output/")
            print(results.success)
            data = []
            for scan in results.iter_success():
                print("{} volume scan time {}".format(
                    scan.radar_id, scan.scan_time))
                data.append(scan.filepath)

            for filename in data:
                outputString, plot_name = plot_radar(filename)

            print('-------')
            print(outputString,plot_name)
            print('-------')

            producer = KafkaProducer(
                bootstrap_servers=bootstrap_servers,
                retries=5)
            ack = producer.send('postprocess-messagehandler', b'success')
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
