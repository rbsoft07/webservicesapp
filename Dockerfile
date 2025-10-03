# Imagen base de Tomcat con JDK 17
FROM amazoncorretto:17-alpine-jdk

# Metadata
LABEL maintainer="rbsoft07@gmail.com"
LABEL version="1.0"
LABEL description="API REST Java - Producción"


# Copiar el WAR compilado y desplegarlo como ROOT
# Esto hace que tu API esté disponible en http://localhost:8080/ directamente
COPY target/webservicesapp.jar ROOT.jar


# Exponer puerto 8080 (Render usará la variable PORT automáticamente)
EXPOSE 9191

ENTRYPOINT ["java", "-jar", "ROOT.jar"]