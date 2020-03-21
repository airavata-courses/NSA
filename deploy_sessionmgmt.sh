#!/bin/bash
cd NSA
sudo kubectl delete deployment session-mgmt-dep
sudo kubectl delete svc session-mgmt-svc
sudo kubectl apply -f session-mgmt.yml

echo "Done"
