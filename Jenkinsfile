pipeline {
    agent any

    // Tools configured in Jenkins Global Tool Configuration
    tools {
        maven 'Maven'   // Must match your Maven tool name in Jenkins
        jdk 'JDK17'     // Optional, if you want to specify JDK
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout your GitHub repository
                git branch: 'main',
                    url: 'https://github.com/AbdulKareem156/JWT.git'
            }
        }

        stage('Build') {
            steps {
                // Linux shell command to build project and skip tests
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Use the SonarQube server configured in Jenkins (name must match)
                withSonarQubeEnv('sonarqube') {
                    // Run SonarQube scanner using Maven
                    sh 'mvn sonar:sonar -Dsonar.projectKey=JWT -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_AUTH_TOKEN'
                }
            }
        }
    }

    post {
        success {
            echo "Build and SonarQube Analysis completed successfully!"
        }
        failure {
            echo "Something went wrong. Check the logs!"
        }
    }
}