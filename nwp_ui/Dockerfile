FROM node:13.6.0-alpine
RUN node -v
RUN mkdir -p /code
WORKDIR /code
ADD . /code
# RUN npm install -g -s --no-progress yarn
RUN yarn install --check-files
# RUN yarn run build
# RUN yarn run prune
RUN yarn cache clean
CMD [ "yarn", "start" ]