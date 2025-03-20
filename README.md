# Hello World!

```sh
docker run -d --name rabbitmq \
  -p 5672:5672 -p 15672:15672 \
  rabbitmq:3-management
```

```sh
docker ps -a | grep rabbitmq
```

```sh
docker logs -f rabbitmq
```