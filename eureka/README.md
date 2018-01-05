eureka-ribbon
=============

Deploy on pivotal cf

- cf api <api-url>

- cf login

- cd eureka-server

- cf push

- cf cups eureka-service -p url

- cd eureka-producer

- cf push

- cd eureka-consumer

- cf push

- cf allow-access eureka-consumer eureka-producer --protocol tcp --port 8080

