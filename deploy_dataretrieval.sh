#!/bin/bash
cd NSA
sudo kubectl delete deployment dataretrieval-deployment
sudo kubectl delete svc dataretrieval-service
sudo kubectl apply -f dataretrieval.yml
echo "Done"
