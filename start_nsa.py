import subprocess
import os
import time

project_dir = os.getcwd()
kafka_dir = os.path.join(project_dir, 'kafka')
user_interface_dir = os.path.join(project_dir, 'nwp_ui')
data_retrieval_dir = os.path.join(project_dir, 'data-retrieval')
post_processing_dir = os.path.join(project_dir, 'postprocessing')

kafka_command = [
    'docker-compose', 'up', '-d', '--force-recreate'
]

api_gateway_command = [
    'java', '-jar', 'api_gateway.jar'
]

user_management_command = [
    'java', '-jar', 'user_management.jar'
]

user_interface_command = [
   'yarn', 'start'

]

data_retrieval_command = [
    'python3', 'dataretrieval.py'
]

post_processing_command = [
    'python3', 'postprocessing.py'
]

os.chdir(kafka_dir)
kafka = subprocess.Popen(kafka_command, stdout=subprocess.PIPE)
os.chdir(project_dir)

_ = kafka.wait()
print('Waiting for 5 sec for kafka to spool')
time.sleep(5)

api_gateway = subprocess.Popen(api_gateway_command, stdout=subprocess.PIPE)
print('Waiting for 5 sec for api_gateway to spool')
time.sleep(5)

user_management = subprocess.Popen(user_management_command, stdout=subprocess.PIPE)
print('Waiting for 5 sec for user_management to spool')
time.sleep(5)

os.chdir(data_retrieval_dir)
data_retrieval = subprocess.Popen(data_retrieval_command, stdout=subprocess.PIPE)
print('Waiting for 5 sec for data_retrieval to spool')
time.sleep(5)
os.chdir(project_dir)

os.chdir(post_processing_dir)
post_processing = subprocess.Popen(post_processing_command, stdout=subprocess.PIPE)
print('Waiting for 5 sec for post_processing to spool')
time.sleep(5)
os.chdir(project_dir)


os.chdir(user_interface_dir)
print('Starting user-interface')
user_interface = subprocess.Popen(user_interface_command, stdout=subprocess.PIPE)
print('Started user-interface')
print('Go to localhost:3000 to start forecasting')

