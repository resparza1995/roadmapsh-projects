
# Caching Proxy

A simple caching proxy server built with Spring Boot.
It forwards HTTP requests to an origin server and caches the responses using Caffeine for improved performance.   
[Project Link](https://roadmap.sh/projects/caching-server)

## Features
Forward requests to a specified origin server.
Cache successful responses automatically.
Return cached responses when available.

Custom `X-Cache` response header:
- `HIT` if the response was served from cache.
- `MISS` if the response was fetched from the origin server.

Clear the cache via command-line option.
Configure server port and origin URL through command-line arguments.

## Requirements
Spring 3
Java 17 or 21
Maven

## How to run
The generated jar will be located under `target/cachingproxy-0.0.1-SNAPSHOT.jar`.

    mvn clean package

**Run server** with port and origin:

    java -jar ./target/cachingproxy-0.0.1-SNAPSHOT.jar -- --port 3000 --origin http://dummyjson.com

`--port`: the port where the proxy server will listen.
`--origin`: the base URL of the origin server (e.g., http://dummyjson.com).

**Clear Cache**

    java -jar ./target/cachingproxy-0.0.1-SNAPSHOT.jar -- --clear-cache
You can clear the cache dynamically without restarting the application by making a `POST` request to `/clear-cache`.


### Example
Make a request with curl:

    curl http://localhost:3000/products

**On first request:** forwarded to http://dummyjson.com/products and cached (X-Cache: MISS).
**On subsequent requests**: served from cache (X-Cache: HIT).

## Project Structure
```
src/main/java
└── com.fyttaiscoding.cachingproxy
├── config # Configuration classes
├── controllers # Controllers to handle incoming requests
├── services # ProxyService handling caching logic
└── CachingproxyApplication.java # Main Spring Boot application
```
