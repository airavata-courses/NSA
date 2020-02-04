from kafka import KafkaConsumer
import sys
import matplotlib.pyplot as plt
import pyart
bootstrap_servers = ['localhost:9092']
topicName = 'post_process'
consumer = KafkaConsumer(topicName, group_id = 'group1',bootstrap_servers = bootstrap_servers,auto_offset_reset = 'earliest')
result=[]
try:
    for message in consumer:
        print ("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition,message.offset, message.key,message.value))

        import json

        result = json.loads(message.value)
        result = result['scans']

        for filename in result:
            radar = pyart.io.read_nexrad_archive(filename)
            display = pyart.graph.RadarDisplay(radar)
            fig = plt.figure(figsize=(16, 12))

            ax = fig.add_subplot(111)
            display = pyart.graph.RadarDisplay(radar)
            display.plot('reflectivity', 0, ax=ax, title='NEXRAD')
            display.set_limits((-150, 150), (-150, 150), ax=ax)
        plt.show()

        '''
        fig = plt.figure(figsize=(16, 12))
        for i, scan in enumerate(result, start=1):
            ax = fig.add_subplot(2, 2, i)
            radar = scan.open_pyart()
            display = pyart.graph.RadarDisplay(radar)
            display.plot('reflectivity', 0, ax=ax, title="{} {}".format(scan.radar_id, scan.scan_time))
            display.set_limits((-150, 150), (-150, 150), ax=ax)
        '''
except KeyboardInterrupt:
    sys.exit()
print('main')


if consumer is not None:
    consumer.close()
