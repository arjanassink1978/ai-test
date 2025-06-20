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
HEALTHCHECK --interval=10s --timeout=3s --start-period=10s --retries=3 CMD wget --spider --quiet http://localhost:3000/ || exit 1
ENV NODE_ENV=production
CMD ["npm", "run", "start"] 