pipeline {
    agent any

    tools {
        jdk 'JDK17'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'master', url: 'https://dagere.comiles.eu/git/SymptomClassification/TableOperations'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Maven Package') {
            steps {
                sh './mvnw clean package'
            }
        }

        stage('Build Docker Images') {
            steps {
                sh 'docker-compose build'
            }
        }
        stage('Check and Stop Container') {
            steps {
                script {
                    def containers = sh(returnStdout: true, script: 'docker ps --format "{{.Names}}"').split("\n")
                    if (containers.contains('tableoperations-api')) {
                        sh "docker stop tableoperations-api"
                        sh "docker rm tableoperations-api"
                    }
                }
            }
        }
        stage('Start Container') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }
}