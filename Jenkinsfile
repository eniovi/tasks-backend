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
        
        stage ('Deploy Backend') {
        	steps {
            	deploy adapters: [tomcat8(credentialsId: 'tomcat_login', path: '', url: 'http://172.29.0.3:8080')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
        	}               
        }
        
        stage ('API Test') {
        	steps {
        		dir('api-test') {
        			git credentialsId: 'github_login', url: 'https://github.com/eniovi/tasks-api-test'
        			sh 'mvn test'
				}
        	}               
        }
        
        stage ('Deploy Frontend') {
        	steps {
        		dir('frontend') {
        			git credentialsId: 'github_login', url: 'https://github.com/eniovi/tasks-frontend'
        			sh 'mvn clean package'
        			deploy adapters: [tomcat8(credentialsId: 'tomcat_login', path: '', url: 'http://172.29.0.3:8080')], contextPath: 'tasks', war: 'target/tasks.war'
				}
        	}               
        }

    }
    
    
}
