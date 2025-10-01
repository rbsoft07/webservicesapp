#Etapa 1 Primera parte de la construccion para el War de la aplicacion.
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app
#Copiamos el archivo pom.xml a la ruta raiz.
COPY pom.xml /
#Descarga de las dependencias indicadas en el pom.xml 
RUN mvn dependency:go-offline

COPY src ./src
#Limpia y empaqueta la aplicacion saltando las pruebas unitarias.
RUN mvn clean package -DskipTests

#Lista los archivos generados en la ruta target 
RUN ls -la /app/target

#Etapa 2 Construcion de la imagen final 
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build  /app/target/webservicesapp.0.0.1-SNAPSHOT.war /app/webservicesappV1.0.war
#Se expone la aplicacion para ejecutarse en el puerto 9191 Produccion.
EXPOSE 9191

CMD ["java", "-jar", "webservicesappV1.0.war"]
