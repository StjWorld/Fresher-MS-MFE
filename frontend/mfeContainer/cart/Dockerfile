FROM node:latest as build

# Set the working directory
WORKDIR /usr/local/app

# Add the source code to app
COPY ./ /usr/local/app/

# Install all the dependencies
RUN npm install

# Generate the build of the application
RUN npm run build


# Stage 2: Serve app with nginx server

# Use official nginx image as the base image
FROM nginx:latest

# Copy the build output to replace the default nginx contents.
COPY --from=build /usr/local/app/src/sample-angular-app /usr/share/nginx/html

# Expose port 80
EXPOSE 80
# FROM node:12.18.1
# ENV NODE_ENV=production
# WORKDIR /usr/src/app
# COPY ["package.json", "package-lock.json*", "npm-shrinkwrap.json*", "./"]
# RUN npm install --production
# COPY . .
# EXPOSE 8080
# RUN chown -R node /usr/src/app
# USER node
# CMD [ "node", "server.js" ]
# CMD ["npm", "start"]
