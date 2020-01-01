docker stop nabootauth-web
docker stop nabootsocket-web
docker stop nabootauth-mysql
docker stop nabootsocket-mysql
docker rm nabootauth-web
docker rm nabootsocket-web
docker rm nabootauth-mysql
docker rm nabootsocket-mysql
docker rmi nabootsocket_web-auth
docker rmi nabootsocket_web-socket
docker rmi $(docker images | grep "<none>" | awk '{print $3}')