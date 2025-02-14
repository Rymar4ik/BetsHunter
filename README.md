# BetsHunter

Test task for Aspira

## How to Run the Docker File

1. **Build the Project and Docker Image**
   ```bash
   mvn clean package
   docker build -t betshunter .
   ```

2. **Run the Docker Container**
   ```bash
   docker run -p 8080:8080 betshunter
   ```

   The output will be printed to the Docker console. Do not use detached mode (`-d`) to view the logs and interact with
   the application directly.

