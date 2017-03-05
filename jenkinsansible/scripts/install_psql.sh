#!/usr/bin/env bash

ssh -p 22 vagrant@localhost

sudo yum install postgresql -y

sudo systemctl enable postgresql.service
sudo systemctl start postgresql.service

