FROM postgres
ENV POSTGRES_USER=foo
ENV POSTGRES_PASSWORD=foobar
ENV POSTGRES_DB=orders
EXPOSE 5432
VOLUME $HOME/docker/volumes/postgres:/var/lib/postgresql/data
ADD DBcreate.sql /docker-entrypoint-initdb.d/
