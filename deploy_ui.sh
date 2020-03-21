#!/bin/bash
cd NSA
sudo kubectl delete deployment  ui
sudo kubectl delete svc ui
sudo sed "s/\$REACT_APP_BACKEND_IP/$(curl ifconfig.me)/g" user_interface.yaml > ui_static.yaml
sudo kubectl apply -f ui_static.yaml
