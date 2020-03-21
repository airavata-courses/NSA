#!/bin/bash
cd NSA
sudo kubectl delete deployment api
sudo kubectl delete deployment apigateway-deployment
sudo kubectl delete svc api
sudo kubectl delete svc apigateway-service
sudo kubectl apply -f apigateway.yml
