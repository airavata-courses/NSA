#!/bin/bash
sudo apt-get update
sudo apt-get install -y kubectl
sudo rm -rf NSA
git clone -b kubernetes https://github.com/airavata-courses/NSA.git
cd NSA
sudo kubectl delete deployment kafka-broker0
sudo kubectl delete deployment mysqldb
sudo kubectl delete deployment zookeeper-deployment-1
sudo kubectl delete deployment adminer-deployment
sudo kubectl delete svc kafka
sudo kubectl delete svc mysql
sudo kubectl delete svc mysqldb
sudo kubectl delete svc zoo1
sudo kubectl delete svc adminer-service
sudo kubectl apply -f zookeeperDeployment.yaml
sudo kubectl apply -f kafkaService.yaml
sudo kubectl apply -f kafkaDeployment.yaml
sudo kubectl apply -f adminer.yml
sudo kubectl apply -f mysqlDeployment.yml

echo "Done"
