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
        
        stage ('Sonar Analysis') {
        	enviroment {
        	    def scannerHome = tool 'sonar_scanner'
        	}

        	steps {
        		withSonarQubeEnv('SONAR_891') {
        			sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://172.29.0.5:9000 -Dsonar.login=d6dd56533e69d90421fcd0f953c6e772e51b495a -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/src/test/**,**/model/**,**Application.java"
        		}
            }
        }
    }
}
