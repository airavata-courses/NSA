#!/bin/bash
cd NSA
sudo kubectl delete deployment postprocess-deployment
sudo kubectl delete svc postprocess-service
sudo kubectl apply -f postprocess-service.yml
sudo kubectl apply -f postprocess_config.yml
sudo kubectl apply -f postprocess.yml

echo "Done"
