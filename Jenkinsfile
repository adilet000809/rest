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
        stage('Archive allure results') {
            steps {
                archiveArtifacts artifacts: 'allure-results/**', fingerprint: true
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
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, icon: '', keepAll: false, reportDir: 'allure-report', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
        }
        failure {
            echo 'Build failed! Check logs and reports.'
        }
    }
}