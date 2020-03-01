from pykafka import KafkaClient
import nexradaws
import sys
import tempfile
from datetime import datetime
import json
# import time

# time.sleep(20)
def data_retrieval(client):
    # consumer = KafkaConsumer(consumer_timeout_ms=15000)
    print("Creating kafka consumer")
    try:
        # client = KafkaClient(
        #     hosts=bootstrap_servers,
        #     )
        topic = client.topics['messagehandler-dataretrieval']
        consumer = topic.get_simple_consumer()
    except:
        print("Could not create kafka consumer. Exiting.")
        sys.exit()
    if consumer is not None:
        print("Created kafka consumer")
    conn = nexradaws.NexradAwsInterface()

    # try:
    message = consumer.consume()
    while message:
        print('mesage.value',message.value)
        #arr = message.value.strip('\"[]').split()
        msg_val = message.value.decode("utf-8")
        years = msg_val['year']
        months = msg_val['month']
        days = msg_val['day']
        radars = msg_val['radarID']
        userName = msg_val['userName']

        # years = message.value['year']
        # months = message.value['month']
        # days = message.value['day']
        # radars = message.value['radarID']
        # userName = message.value['userName']

        print(years, months, days, radars)
        scans = conn.get_avail_scans(years, months, days, radars)

        templocation = tempfile.mkdtemp()
        results = conn.download(scans[0:1], templocation)
        print(results.success)
        data = []
        for scan in results.iter_success():
            print("{} volume scan time {}".format(scan.radar_id,scan.scan_time))
            data.append(scan.filepath)
        jsonObj = {"scans": data,"pp":{"userName":userName,"month":months,"day":days,"year":years,"radar":radars,"output":"","status":"success"}}
        

        print("{} downloads failed.".format(results.failed_count))
        if results.failed_count == 0:
            jsonSession = {"userName":userName,"month":months,"day":days,"year":years,"radar":radars,"output":"","status":"success"}
        else:
            jsonSession = {"userName":userName,"month":months,"day":days,"year":years,"radar":radars,"output":"","status":"failed"}

        print(jsonObj)

        topic = client.topics['dataretrieval-postprocess']
        producer = topic.get_sync_producer()
        producer.produce(bytes(json.dumps(jsonObj), 'utf-8'))


        # producer = KafkaProducer(
        #     bootstrap_servers = bootstrap_servers,
        #     retries = 5,
        #     value_serializer=lambda m: json.dumps(m).encode('ascii'))
        # ack = producer.send('dataretrieval-postprocess', jsonObj)
        # metadata = ack.get()
        # print(metadata.topic)
        # print(metadata.partition)

        # sess_producer = KafkaProducer(
        #     bootstrap_servers = bootstrap_servers,
        #     retries = 5,
        #     value_serializer=lambda m: json.dumps(m).encode('ascii'))

        # sess_ack = sess_producer.send('dataretrieval-sessionmgmt', jsonSession)
        # metadata_sess = sess_ack.get()
        # print(metadata_sess.topic)
        # print(metadata_sess.partition)
        producer.produce(bytes(json.dumps(jsonSession), 'utf-8'))            

    # except KeyboardInterrupt:
    #     sys.exit()
    # print('main')
    if consumer is not None:
        consumer.close()

    

def test_kafka():
    print("Attempting to connect to kafka")
    


# def main():
print("Welcome to data_retrival")
bootstrap_servers = 'kafka:9092'
temp = False
while temp == False:
    try:
        client = KafkaClient(hosts=bootstrap_servers)
        topic = client.topics['test']
        consumer = topic.get_simple_consumer()
        # if consumer1 is not None:
        # consumer1.close()
        print("Connected to kafka")
        temp = True

    except:
        print("Failed kafka test")
        temp =  False
    print("Failed connecting to kafka. Retrying.")
    # time.sleep(2)

data_retrieval(client)

# main()
