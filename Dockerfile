# Utilisation d'une image Java
FROM eclipse-temurin:21-jdk-jammy AS builder

# Installation de Maven
RUN apt-get update && \
    apt-get install -y curl unzip && \
    curl -fSL https://dlcdn.apache.org/maven/maven-3/3.9.10/binaries/apache-maven-3.9.10-bin.zip -o maven.zip && \
    unzip maven.zip -d /opt && \
    mv /opt/apache-maven-3.9.10 /opt/maven && \
    rm maven.zip && \
    ln -s /opt/maven/bin/mvn /usr/local/bin/mvn && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Répertoire de travail dans le conteneur
WORKDIR /app

# Optimisation du cache Docker
COPY pom.xml .
COPY src ./src

# Construit l'application Spring Boot et crée le JAR
RUN mvn clean package -Dmaven.test.skip=true

# Réduction de l'image finale avec JRE au lieu de JDK
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copie le JAR exécutable du "builder"
COPY --from=builder /app/target/electricity-business-0.0.1-SNAPSHOT.jar app.jar

# Port configuré dans le application.properties
EXPOSE 8080

# Commande d'exécution de l'application Spring Boot avec le JAR
CMD ["java", "-jar", "app.jar"]

# Pour la production utiliser cette commande pour améliorer la performance au démarrage
# CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
