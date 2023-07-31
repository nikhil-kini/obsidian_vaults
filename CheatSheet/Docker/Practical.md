## Displays system information

    docker info

## Displays the system’s version

    docker version

## Log in to Docker Hub

    docker login



docker run -d -p 8083:80 --name webserver nginx

docker ps

docker images

docker container exec -it webserver bash

docker stop webserver

docker ps -a

docker rm webserver

docker rmi nginx