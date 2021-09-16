cd bountyHunter
ibmcloud app stop bountyHunters
./mvnw clean package
ibmcloud app push bountyHunters -p target/bountyHunter-0.0.1-SNAPSHOT.jar