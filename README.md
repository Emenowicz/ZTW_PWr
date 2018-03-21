## Usage
- import project by parent pom.xml in main directory
- set up run configuration
   -- backend: ```mvn clean install spring-boot:run```
   -- frontend: ```mvn clean install```

Backend will run on ```localhost:8088```
Frontend will run on ```localhost:8080```

There is no need to rebuild frontend module. You just need to save files to see changes (unless you changed the configuration).
