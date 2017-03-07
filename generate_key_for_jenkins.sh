#!/usr/bin/env bash

# generate ssh keys for jenkins
ssh-keygen -b 1024 -t dsa -f hosts/roles/jenkins/files/id_rsa

# the public key is also used in the postgres playbook
cp hosts/roles/jenkins/files/id_rsa.pub hosts/roles/postgres/files/authorized_keys