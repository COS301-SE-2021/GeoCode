docker-compose stop
cd ../frontend
npm install
cp ../../environment.prod.ts src/environments/environment.ts
cp ../../environment.prod.ts src/environments/environment.prod.ts
ionic build --prod
cd ../backend
cp ../../keystore.p12 src/main/resources
mvn spring-boot:build-image -Dmaven.test.skip=true -Dspring-boot.build-image.imageName=geocode/backend
cd ../deployment
docker-compose --env-file ../../.env up -d
echo "Deployment completed successfully"