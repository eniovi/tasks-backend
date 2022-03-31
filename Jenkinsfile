pipeline {
    agent any
    
    tools {
        maven 'MVN_363'
        jdk 'JAVA_8'
    }

    stages {
        stage ('Build Backend') {
        	steps {
            	sh 'mvn clean package -DskipTests=true'
            }
        }
        
        stage ('Unit Tests') {
        	steps {
            	sh 'mvn test'
            }
        }
    }
}
