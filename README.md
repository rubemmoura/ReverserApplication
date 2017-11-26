# ReverserApplication
Aplicação Reverser, com finalidade de inverter uma string utilizando um middleware desenvolvido.



# Para rodar a aplicação dentro do docker:

- Na pasta onde os arquivos .java da aplicação estão, executar o comando: 

      sudo docker run -p 1313:1313 --rm -v $PWD:/app -w /app demo/oracle-java:8 javac ReverserProxy.java
      
      sudo docker run -p 1313:1313 --rm -v $PWD:/app -w /app demo/oracle-java:8 javac NamingServer.java

- Em seguida: 

      sudo docker run -p 1313:1313 --rm -v $PWD:/app -w /app demo/oracle-java:8 java NamingServer

A plicação irá ser executada dentro do docker e teremos o NamingServer como Microservice.

Foi desenvolvido um Microserviço que irá adicionar o Serviço do servidor no Servidor de Nomes (NamingServer). Com isso, para que o Cliente da aplicação consiga acessar o Servidor da aplicação, executamos o BindServer com os comandos:

      sudo docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 javac BindServer.java
      
      sudo docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 java BindServer

 Após isso, iremos executar agora o Servidor como Microservice através do comando:

      sudo docker run -p 1314:1314 --rm -v $PWD:/app -w /app demo/oracle-java:8 javac ReverserServer.java
      
      sudo docker run -p 1314:1314 --rm -v $PWD:/app -w /app demo/oracle-java:8 java ReverserServer
      
      sudo docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 javac ReverserServerTwo.java
      
      sudo docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 java ReverserServerTwo
      
Com isso, os 2 Servidores das aplicações serão compilados e executados dentro de outros Containers no Docker. 

Em seguida precisamos executar o servidor de Proxy do lado do Cliente com os comandos:

      sudo docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 javac ProxyClient.java
      
      sudo docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 java ProxyClient
    
E por fim, executamos o CLiente com os comandos:
      
      sudo docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 javac ReverserClient.java
      
      sudo docker run --rm -v $PWD:/app -w /app demo/oracle-java:8 java ReverserClient
            
Limitando a quantidade de memória, o comando de execução passa a ser:

      sudo docker run -p 1313:1313 --rm -it -m 50m --memory-swap -1 -v $PWD:/app -w /app demo/oracle-java:8 java NamingServer
      
      sudo docker run --rm -it -m 50m --memory-swap -1 -v $PWD:/app -w /app demo/oracle-java:8 java BindServer
      
      sudo docker run -it -m 50m --memory-swap -1 -p 1314:1314 --rm -v $PWD:/app -w /app demo/oracle-java:8 java ReverserServer
      
      sudo docker run --rm -it -m 50m --memory-swap -1 -v $PWD:/app -w /app demo/oracle-java:8 java ReverserServerTwo
      
      sudo docker run --rm -it -m 50m --memory-swap -1 -v $PWD:/app -w /app demo/oracle-java:8 java ProxyClient
      
      sudo docker run --rm -it -m 50m --memory-swap -1 -v $PWD:/app -w /app demo/oracle-java:8 java ReverserClient

Importate: Para que o BindServer consiga se comunicar com o NamingServer em outro container, é preciso o IP que esse container possui. Para isso executamos o comando:

      docker inspect CONTAINER_ID



