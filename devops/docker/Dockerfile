FROM alpine:3.7 AS get
MAINTAINER Lucas Alves <luca.lopes97@hotmail.com>

ENV ADMIN_PASSWORD=admin
ENV DATOMIC_PASSWORD=admin
ENV VERSION 0.9.5703.21

RUN wget https://my.datomic.com/downloads/free/${VERSION} -qO /tmp/datomic.zip \
  && unzip /tmp/datomic.zip \
  && rm /tmp/datomic.zip \
  && mv /datomic-free-${VERSION} /datomic

WORKDIR /datomic

RUN cp config/samples/free-transactor-template.properties /datomic/transactor.properties

# Configure host
RUN sed "s/host=localhost/host=0.0.0.0/" -i /datomic/transactor.properties
RUN sed "/host=0.0.0.0/a alt-host=127.0.0.1" -i /datomic/transactor.properties

# Configure remote access
RUN sed "s/# storage-access=local/storage-access=remote/" -i transactor.properties
RUN sed "s/# storage-admin-password=/storage-admin-password=${ADMIN_PASSWORD}/" -i transactor.properties
RUN sed "s/# storage-datomic-password=/storage-datomic-password=${DATOMIC_PASSWORD}/" -i transactor.properties

FROM openjdk:11-jre-slim AS run

COPY --from=get /datomic .

EXPOSE 4334 4335 4336

CMD bin/transactor transactor.properties



