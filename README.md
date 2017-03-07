# README

This project is a proof of concept on how to automate postgres db updates.

It works with virtualized hosts running on a virtualbox. It has three servers:

* jenkins server (private IP: 172.17.177.21)
* one database server (private IP: 172.17.177.22)
* one controller server for initial setup via ansible (private IP: 172.17.177.11)

## How to start

* start script `generate_key_for_jenkins.sh`
* do `cd hosts`
* do `vagrant up --provision` and wait for setup

