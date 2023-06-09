#!/usr/bin/env bash


echo
echo "Stopping services..."
docker stop gateway-service discovery-service email-service event-service user-service

echo
echo "Stopping compose network..."
docker-compose down

echo
echo "Done."
