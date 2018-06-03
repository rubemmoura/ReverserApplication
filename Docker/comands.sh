sudo docker run -p 1313:1313 --rm -v $PWD:/app -w /app demo/oracle-java:8 java NamingServer

sudo docker run -p 1316:1316 --rm -v $PWD:/app -w /app demo/oracle-java:8 java BindServer

sudo docker run -p 1314:1314 --rm -v $PWD:/app -w /app demo/oracle-java:8 java ReverserServer

sudo docker run -p 1317:1317 --rm -v $PWD:/app -w /app demo/oracle-java:8 java ReverserServerTwo

sudo docker run -p 1315:1315 --rm -v $PWD:/app -w /app demo/oracle-java:8 java ProxyClient

sudo docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 javac ReverserClient.java

sudo docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 java ReverserClient

# Para solicitar serviço no serverTwo
# reverserProxy = clientProxy.proxy(namingService,0);
# str = reverserProxy.inverter(str,0);
