# Multi-stage build para optimizar la imagen final
FROM tomcat:10.1-jdk17-temurin-jammy AS builder

# Metadata
LABEL maintainer="tu-email@example.com"
LABEL description="API REST Java con Tomcat"

# Argumentos de build
ARG WAR_FILE=target/*.war
ARG APP_NAME=api

# Remover aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el WAR al webapps de Tomcat
COPY ${WAR_FILE} /usr/local/tomcat/webapps/${APP_NAME}.war

# Imagen final - más ligera
FROM tomcat:10.1-jdk17-temurin-jammy

# Variables de entorno para optimización
ENV CATALINA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC -Djava.security.egd=file:/dev/./urandom"
ENV JAVA_OPTS="-Djava.awt.headless=true"

# Argumentos
ARG APP_NAME=api

# Remover aplicaciones default
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el WAR desde el builder
COPY --from=builder /usr/local/tomcat/webapps/${APP_NAME}.war /usr/local/tomcat/webapps/ROOT.war

# Configurar servidor.xml para producción (opcional)
# COPY server.xml /usr/local/tomcat/conf/server.xml

# Exponer puerto (Render usa la variable PORT)
EXPOSE 8080

# Health check
#HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
 # CMD curl -f http://localhost:8080/ || exit 1

# Comando de inicio
CMD ["catalina.sh", "run"]