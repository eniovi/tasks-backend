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
        	environment {
        	    def scannerHome = tool 'sonar_scanner'
        	}

        	steps {
        		withSonarQubeEnv('SONAR_891') {
        			sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://172.29.0.5:9000 -Dsonar.login=aff86b42faa4b6e2444f6c28fafb2d4404a9a0da -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/src/test/**,**/model/**,**Application.java"
        		}
            }
        }
        
        stage ('Quality Gate') {
        	steps {
        		sleep(10)
        		timeout(time: 1, unit: 'MINUTES') {
        			waitForQualityGate abortPipeline: true
        		}
            }
        }
    }
}
