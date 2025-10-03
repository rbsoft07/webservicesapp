# Imagen base de Tomcat con JDK 17
FROM tomcat:9.0-jdk17-openjdk

# Metadata
LABEL maintainer="rbsoft07@gmail.com"
LABEL version="1.0"
LABEL description="API REST Java - Producción"


# Copiar el WAR compilado y desplegarlo como ROOT
# Esto hace que tu API esté disponible en http://localhost:8080/ directamente

COPY target/webservicesapp.war /usr/local/tomcat/webapps/app.war


# Exponer puerto 8080 (Render usará la variable PORT automáticamente)
EXPOSE 8080

CMD ["catalina.sh", "run"]