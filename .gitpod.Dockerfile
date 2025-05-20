# .gitpod.Dockerfile
FROM gitpod/workspace-full

# Instalace závislostí (Java, Android SDK)
RUN sudo apt-get update \
    && sudo apt-get install -y openjdk-17-jdk

ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64
