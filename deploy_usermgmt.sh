#!/bin/bash
cd NSA
sudo kubectl delete deployment  usermanagement-deployment
sudo kubectl delete svc usermanagement-service
sudo kubectl apply -f usermanagement.yml
