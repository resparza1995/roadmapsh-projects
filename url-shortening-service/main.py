from fastapi import FastAPI, HTTPException, Path, Response
from pydantic import BaseModel, HttpUrl
from datetime import datetime
from typing import Dict
import string, random

app = FastAPI()

class URLRequest(BaseModel):
    url: HttpUrl

class URLResponse(URLRequest):
    id: str
    shortCode: str
    createdAt: datetime
    updatedAt: datetime

class URLStats(URLResponse):
    accessCount: int

# In-memory storage
db: Dict[str, URLStats] = {}
short_to_id: Dict[str, str] = {}

# Generate unique short code
def generate_short_code(length=6):
    chars = string.ascii_letters + string.digits
    while True:
        code = ''.join(random.choices(chars, k=length))
        if code not in short_to_id:
            return code

@app.post("/shorten", response_model=URLResponse, status_code=201)
def create_short_url(payload: URLRequest):
    short_code = generate_short_code()
    now = datetime.now()
    new_id = str(len(db) + 1)
    data = URLStats(
        id=new_id,
        url=payload.url,
        shortCode=short_code,
        createdAt=now,
        updatedAt=now,
        accessCount=0
    )
    db[new_id] = data
    short_to_id[short_code] = new_id
    return data

@app.get("/shorten/{short_code}", response_model=URLResponse)
def get_original_url(short_code: str = Path(...)):
    if short_code not in short_to_id:
        raise HTTPException(status_code=404, detail="Short URL not found")
    entry = db[short_to_id[short_code]]
    entry.accessCount += 1
    return entry

@app.put("/shorten/{short_code}", response_model=URLResponse)
def update_short_url(payload: URLRequest, short_code: str = Path(...)):
    if short_code not in short_to_id:
        raise HTTPException(status_code=404, detail="Short URL not found")
    entry = db[short_to_id[short_code]]
    entry.url = payload.url
    entry.updatedAt = datetime.utcnow()
    return entry

@app.delete("/shorten/{short_code}", status_code=204)
def delete_short_url(short_code: str = Path(...)):
    if short_code not in short_to_id:
        raise HTTPException(status_code=404, detail="Short URL not found")
    entry_id = short_to_id.pop(short_code)
    db.pop(entry_id)
    return

@app.get("/shorten/{short_code}/stats", response_model=URLStats)
def get_url_stats(short_code: str = Path(...)):
    if short_code not in short_to_id:
        raise HTTPException(status_code=404, detail="Short URL not found")
    return db[short_to_id[short_code]]

@app.get("/shorten/stats/all", response_model=list[URLStats])
def get_all_stats():
    if not db:
        return Response(status_code=204)
    return list(db.values())
