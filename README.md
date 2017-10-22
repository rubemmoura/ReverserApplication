# ReverserApplication
Aplicação Reverser, com finalidade de inverter uma string utilizando um middleware desenvolvido.



# Para rodar a aplicação dentro do docker:

- Na pasta onde os arquivos .java da aplicação estão, executar o comando: 

      sudo docker run -p 1313:1313 --rm -v $PWD:/app -w /app demo/oracle-java:8 javac ReverserProxy.java
      
      sudo docker run -p 1313:1313 --rm -v $PWD:/app -w /app demo/oracle-java:8 javac NamingServer.java

- Em seguida: 

      sudo docker run -p 1313:1313 --rm -v $PWD:/app -w /app demo/oracle-java:8 java NamingServer

A plicação irá ser executada dentro do docker.
