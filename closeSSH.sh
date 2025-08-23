#!/bin/bash

sudo ufw disable

sudo ufw delete allow 22

sudo systemctl disable ssh

sudo systemctl stop ssh

sudo systemctl status ssh
