# Imagen base de Tomcat con JDK 17
FROM tomcat:10.1-jdk17-temurin-jammy

# Metadata
LABEL maintainer="rbsoft07@gmail.com"
LABEL version="1.0"
LABEL description="API REST Java - Producción"


# Variables de entorno optimizadas para producción
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Djava.awt.headless=true"
ENV CATALINA_OPTS="-Xms512m -Xmx1024m -server -XX:+UseG1GC -XX:MaxGCPauseMillis=200"

# Instalar curl para health checks
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*


# Limpiar webapps por defecto de Tomcat

RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar el WAR compilado y desplegarlo como ROOT
# Esto hace que tu API esté disponible en http://localhost:8080/ directamente
COPY target/webservicesapp.war /usr/local/tomcat/webapps/ROOT.war


# Exponer puerto 8080 (Render usará la variable PORT automáticamente)
EXPOSE 9191

# Health check para verificar que el servicio está corriendo
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/ || exit 1

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]