from kafka import KafkaProducer
from kafka import KafkaConsumer
import nexradaws
import sys
import tempfile
from datetime import datetime
import json

def main():
    bootstrap_servers = ['kafka:9092']
    consumer = None
    # while consumer is None:
        # try:
    consumer = KafkaConsumer(
        'messagehandler-dataretrieval',
        bootstrap_servers=bootstrap_servers,
        auto_offset_reset='earliest',
        enable_auto_commit=True,
        group_id='group1',
        value_deserializer=lambda x: json.loads(x.decode('utf-8')))
    print("Connected to Kafka Broker")
    conn = nexradaws.NexradAwsInterface()

    try:
        for message in consumer:
            print('mesage.value', message.value)
            #arr = message.value.strip('\"[]').split()
            years = message.value['year']
            months = message.value['month']
            days = message.value['day']
            radars = message.value['radarID']
            userName = message.value['userName']

            print(years, months, days, radars)
            scans = conn.get_avail_scans(years, months, days, radars)

            templocation = tempfile.mkdtemp()
            results = conn.download(scans[0:1], templocation)
            print(results.success)
            data = []
            for scan in results.iter_success():
                print("{} volume scan time {}".format(
                    scan.radar_id, scan.scan_time))
                data.append(scan.filepath)
            jsonObj = {"scans": data, "pp": {"userName": userName, "month": months,
                                            "day": days, "year": years, "radar": radars, "output": "", "status": "success"}}

            print("{} downloads failed.".format(results.failed_count))
            if results.failed_count == 0:
                jsonSession = {"userName": userName, "month": months, "day": days,
                            "year": years, "radar": radars, "output": "", "status": "success"}
            else:
                jsonSession = {"userName": userName, "month": months, "day": days,
                            "year": years, "radar": radars, "output": "", "status": "failed"}

            print(jsonObj)

            producer = KafkaProducer(
                bootstrap_servers=bootstrap_servers,
                retries=5,
                value_serializer=lambda m: json.dumps(m).encode('ascii'))
            ack = producer.send('dataretrieval-postprocess', jsonObj)
            metadata = ack.get()
            print(metadata.topic)
            print(metadata.partition)

            sess_producer = KafkaProducer(
                bootstrap_servers=bootstrap_servers,
                retries=5,
                value_serializer=lambda m: json.dumps(m).encode('ascii'))
            sess_ack = sess_producer.send('dataretrieval-sessionmgmt', jsonSession)
            metadata_sess = sess_ack.get()
            print(metadata_sess.topic)
            print(metadata_sess.partition)


    except:
        print("Message Error")
        sys.exit()

main()
