FROM amazoncorretto:21.0.3-alpine3.19 AS corretto-jdk

RUN apk add --no-cache binutils

COPY ./target/*.jar service.jar
RUN jar xf service.jar

RUN jdeps --ignore-missing-deps -q  \
    --recursive  \
    --multi-release 21  \
    --print-module-deps  \
    --class-path 'BOOT-INF/lib/*'  \
    service.jar > deps.info

ARG CUSTOM_JRE_DIR=/customjre
RUN $JAVA_HOME/bin/jlink \
    --verbose \
   --add-modules $(cat deps.info) \
   --strip-debug \
   --no-man-pages \
   --no-header-files \
   --compress zip-9 \
   --output $CUSTOM_JRE_DIR

FROM alpine:latest

RUN apk add --no-cache fontconfig ttf-dejavu

ENV JAVA_HOME=/jre
ENV PATH=${JAVA_HOME}/bin:${PATH}
ENV HOME=/opt/acs
ENV LOG_HOME=/var/log/acs
ENV SERVER_NAME=acs

ARG CUSTOM_JRE_DIR=/customjre
COPY --from=corretto-jdk $CUSTOM_JRE_DIR $JAVA_HOME

ARG APPLICATION_USER=acs
RUN adduser --no-create-home -D $APPLICATION_USER && \
    mkdir -p $HOME/service && \
    chown -R $APPLICATION_USER $HOME/service
COPY --chown=$APPLICATION_USER:$APPLICATION_USER ./target/*.jar $HOME/service/service.jar

WORKDIR $HOME/service

ENTRYPOINT [ "java", "-jar", "./service.jar" ]