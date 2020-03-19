FROM node:latest
WORKDIR /server
COPY package.json /server
RUN npm install --checkfiles
COPY . /server
RUN chmod +x wait-for-it.sh
CMD ["./wait-for-it.sh" , "kafka:9092" , "--strict" , "--" , "node", "session-management.js"]
EXPOSE 3002
