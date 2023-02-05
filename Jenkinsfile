pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'master', url: 'https://dagere.comiles.eu/git/SymptomClassification/SymptomSubtitleService'
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
                    if (containers.contains('subtitle-api')) {
                        sh "docker stop subtitle-api"
                        sh "docker rm subtitle-api"
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