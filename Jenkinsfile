pipeline {
    agent any
   environment {
       DOCKER_CREDS = credentials('localdocker')
       registry = "876724398547.dkr.ecr.ap-south-1.amazonaws.com/mydockerrepo"
        gpg_secret = credentials("gpg-secret")
        gpg_trust = credentials("gpg-ownertrust")
        gpg_passphrase = credentials("gpg-passphrase")
        secret_passphrase = credentials("secretpassphrase")
      
        
   }
    tools
    {
        maven "MAVEN"
        jdk "JDK"
    }
    stages {

        stage("Code CheckOut")
        {
        steps
        {
     git branch: 'gayathri', credentialsId: 'GITLAB1', url: 'https://git.nagarro.com/freshertraining2021/gayathriemuru.git'
        }
        }
       stage('imoort gpg key'){
        steps {
        
    sh """
        gpg --import $gpg_secret || true
        gpg --import-ownertrust $gpg_trust || true
    """
          }   
       }
        
       stage('git secret')
       {
           steps {
                 git credentialsId: 'GITLAB1', url: 'https://git.nagarro.com/freshertraining2021/gayathriemuru.git'
    sh """
        cd $WORKSPACE
        git secret init ||true
        rm -f dbpassword.txt || true
        cat dbpassword.txt || true
        git secret tell gayathrinaidur112@gmail.com || true
         git secret reveal -p $secret_passphrase || true
        

         cat dbpassword.txt || true
         rm -f dbpassword.txt || true
    """
         }
       }
       
        stage('Code Build') {
            steps {
               
                sh 'mvn clean install'
                
            }
        }
      stage("JUnit Test Case") 
        {
            steps 
            {
                  git branch: 'gayathri', credentialsId: 'GITLAB1', url: 'https://git.nagarro.com/freshertraining2021/gayathriemuru.git'
                sh "mvn test"
                }
            }
        
         stage("Source composition analysis-SCA")
        {
        steps
        {
        git branch: 'gayathri', credentialsId: 'GITLAB1', url: 'https://git.nagarro.com/freshertraining2021/gayathriemuru.git'
        dependencyCheck additionalArguments: 'scan="https://git.nagarro.com/freshertraining2021/gayathriemuru.git" --format HTML', odcInstallation: 'DEPNDENCY-CHECK'
           
        }
        }
        
         
        stage("Static application security testing-SAST")
         {
        steps
        {
            withSonarQubeEnv('sonarscanner')
            {
            sh "mvn sonar:sonar"
            }
        }
    }
     
        
  
         stage('Building Docker Image') {
      steps{
        script {
        docker.build('mydockerrepo')
        }
      }
    }
     stage('Upload Docker Image') {
     steps{    
         script {
           
      docker.withRegistry('https://876724398547.dkr.ecr.us-east-2.amazonaws.com/mydockerrepo','ecr:us-east-2:awskey')
     {
      docker.image('mydockerrepo').push('latest')
    
     }

            }
        }
        }
      
       
    stage('Docker Deployment') {
     steps{
         script
         {
        docker.withRegistry('https://876724398547.dkr.ecr.us-east-2.amazonaws.com','ecr:us-east-2:awskey')
           {
           sshagent(['ubuntu']) {
                sh "ssh -o StrictHostKeyChecking=no ubuntu@18.191.26.36 sudo docker stop containername1 || true"
             sh "ssh -o StrictHostKeyChecking=no ubuntu@18.191.26.36 sudo docker rm containername1 || true"
           sh "ssh -o StrictHostKeyChecking=no ubuntu@18.191.26.36 sudo docker run -d --name containername1 -p 8093:8080 876724398547.dkr.ecr.us-east-2.amazonaws.com/mydockerrepo:latest"
          
  
           }
           }
      
          }
           
         }
         
      }
      stage('Docker Image Scan') {
     steps{
         script
         {
        docker.withRegistry('https://876724398547.dkr.ecr.us-east-2.amazonaws.com','ecr:us-east-2:awskey')
           {
           sshagent(['ubuntu']) {
          sh "ssh -o StrictHostKeyChecking=no ubuntu@18.191.26.36 sudo trivy image 876724398547.dkr.ecr.us-east-2.amazonaws.com/mydockerrepo:latest > docker-scan-report.txt"
  
           }
           }
      
   }
           
         }
         
      }
      stage ("Dynamic Analysis - DAST with OWASP ZAP") {
			steps {
				sh "docker run -t owasp/zap2docker-stable zap-baseline.py -t http://18.191.26.36:8093/FirstJavaProgramInSpringMVC/ >dast-scan-report.txt || true"
			}
		
		}
        
      stage('Email Notifications') {
            steps {
                mail to: 'gayathri.emuru@nagarro.com',               
                    subject: "Job $JOB_NAME success" ,
                    body: """Build $BUILD_NUMBER success.    
Go to $BUILD_URL for more info."""
            }
        }
    
    }
    
    
    } 