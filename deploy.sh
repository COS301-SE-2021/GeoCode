cd frontend
npm install
cp ../../environment.prod.ts src/environments
ionic build --prod
cd ../backend
cp ../../keystore.p12 src/main/resources
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=geocode/backend
cd ..
docker stop spring
docker-compose --env-file ../.env up -d
echo "Deployment completed successfully"