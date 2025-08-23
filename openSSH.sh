#!/bin/bash

sudo systemctl start ssh

sudo systemctl enable ssh

sudo ufw allow 22

sudo ufw enable

sudo systemctl status ssh
