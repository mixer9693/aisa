FROM postgres:14.2-alpine
RUN mkdir -p /docker-entrypoint-initdb.d
COPY scripts/init.sql /docker-entrypoint-initdb.d/