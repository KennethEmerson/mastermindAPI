# build docker image with :
# docker build -t mastermind_api_dock --build-arg OPENJDK_TAG=8u232 --build-arg SBT_VERSION=1.3.8 .

# run the docker image with:
# docker run -d -p 9000:9000 -t mastermind_api_dock


ARG OPENJDK_TAG=8u232
FROM openjdk:${OPENJDK_TAG}

ARG SBT_VERSION=1.3.8

# Install sbt
RUN \
  curl -L -o sbt-$SBT_VERSION.deb https://dl.bintray.com/sbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install sbt && \
  sbt sbtVersion

WORKDIR /app
COPY . .
RUN sbt compile
EXPOSE 9000
CMD sbt run