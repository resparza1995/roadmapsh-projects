# URL Shortening Service
A simple URL shortening RESTful API built with Python 3.10+ and FastAPI. This service allows users to create, retrieve, update, and delete shortened URLs, and provides statistics on URL usage.
Storage is managed in an in-memory dictionary.

## Requirements
Python 3.10 or newer  
fastapi: `pip install "fastapi[standard]"`

## How to run
Default available at `http://127.0.0.1:8000`  
Swagger at `http://127.0.0.1:8000/docs`  
More docs at `http://127.0.0.1:8000/redoc`  

## API Endpoints
**Create Short URL** `POST /shorten`  
**Retrieve Original URL** `GET /shorten/{short_code}`  
**Update Short URL** `PUT /shorten/{short_code}`     
**Delete Short URL** `DELETE /shorten/{short_code}`  
**Get URL Statistics** `GET /shorten/{short_code}/stats`  
**Get Statistics for All URLs** `GET /shorten/stats/all`  