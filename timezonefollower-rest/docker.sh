#!/bin/zsh

aws ecr get-login-password --region us-west-1 | docker login --username AWS --password-stdin 919870867230.dkr.ecr.us-west-1.amazonaws.com
docker build . -t 919870867230.dkr.ecr.us-west-1.amazonaws.com/timezone_follower:latest
docker push 919870867230.dkr.ecr.us-west-1.amazonaws.com/timezone_follower:latest