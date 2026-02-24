FROM docker.io/library/eclipse-temurin:21-jre-alpine

ARG USER_NAME=eshop
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

RUN addgroup -g ${USER_GID} ${USER_NAME} \
    && adduser -h /opt/eshop -D -u ${USER_UID} -G ${USER_NAME} ${USER_NAME}

WORKDIR /opt/eshop

COPY --chown=${USER_UID}:${USER_GID} build/libs/*.jar app.jar

USER ${USER_NAME}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]