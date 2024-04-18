FROM node:lts-alpine

# curl 설치 (client url) -> 필수X (유사 포스트맨)
RUN apk add --no-cache curl

WORKDIR /app
COPY . ./
RUN npm install

# -- 이후 코드 필수 
CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0"]