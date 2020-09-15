#!/usr/bin/env bash
#Get all nodes
# kubectl get nodes --no-headers=true | awk '/ip-10/{print $1}'
#taint all nodes
kubectl taint nodes --all slow:NoExecute
# untaint
#kubectl taint nodes --all slow:NoExecute-