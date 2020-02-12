from kafka import KafkaProducer
import nexradaws
import tempfile
from datetime import datetime
import json
bootstrap_servers = ['localhost:9092']
topicName = 'post_process'
producer = KafkaProducer(bootstrap_servers = bootstrap_servers)
producer = KafkaProducer()

conn = nexradaws.NexradAwsInterface()
years = conn.get_avail_years()
months = conn.get_avail_months('2013')
days = conn.get_avail_days('2013','05')
radars = conn.get_avail_radars('2013','05','31')
scans = conn.get_avail_scans('2016','05','31','KTLX')
radarid = 'KTLX'
start = datetime(2013, 5, 31, 20, 0)
end = datetime(2013, 5, 31, 23, 0)
print("There are {} scans available between {} and {}\n".format(len(scans), start, end))
print(scans[0:4])
templocation = tempfile.mkdtemp()
results = conn.download(scans[0:4], templocation)
print(results.success)
data = []
for scan in results.iter_success():
    print("{} volume scan time {}".format(scan.radar_id,scan.scan_time))
    data.append(scan.filepath)
jsonObj = {"scans": data}

print("{} downloads failed.".format(results.failed_count))

#ack = producer.send(topicName, b'Hello World!!!!!!!!')
ack = producer.send(topicName, json.dumps(jsonObj).encode('utf-8'))
metadata = ack.get()
print(metadata.topic)
print(metadata.partition)
producer = KafkaProducer(bootstrap_servers = bootstrap_servers, retries = 5,value_serializer=lambda m: json.dumps(m).encode('ascii'))