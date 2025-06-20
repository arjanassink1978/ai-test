# ---- Build Stage ----
FROM node:20-alpine AS build
WORKDIR /app
COPY . .
RUN npm ci && npm run build

# ---- Run Stage ----
FROM node:20-alpine
WORKDIR /app
COPY --from=build /app .
EXPOSE 3000
ENV NODE_ENV=production
CMD ["npm", "run", "start"] 