pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JDK 17'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/adilet000809/rest.git'
            }
        }
        stage('Build & Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
        stage('Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'allure-results']]
                ])
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml' // TestNG XML report for Jenkins
        }
        failure {
            echo 'Build failed! Check logs and reports.'
        }
    }
}