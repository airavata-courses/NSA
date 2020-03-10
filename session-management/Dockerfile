FROM node:9-slim
WORKDIR /server
COPY package.json /server
RUN npm install --checkfiles
COPY . /server
CMD node session-management.js
EXPOSE 3032