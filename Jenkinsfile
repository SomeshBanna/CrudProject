pipeline {
    agent {label 'QA environment'}

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "MAVEN_HOME"
    }

    stages {
    
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/SomeshBanna/CrudProject.git'

                // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                 bat "mvn compile"
                 
            	}
            post{
            	failure{
            		emailext (
          subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
          body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
            <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
          recipientProviders: [[$class: 'DevelopersRecipientProvider']], 
        replyTo: 'bannasomesh25@gmail.com', 
       
        to: 'bannasomesh25@gmail.com'
         				    )
        				}
       			 }
            		
     		 }
     		 
     	stage('Testing') {
     		steps {
     			bat "mvn test"
     			}
     			post {
     				success{
     					junit(testResults: 'target/surefire-reports/*.xml',allowEmptyResults: true)
     				}
     				
     				}	 
     		 }
     		 
     		 
     	stage('Static code analysis') {
     		steps{
     			withSonarQubeEnv('sonarqube-9.7.1') {
     			    bat "mvn clean verify sonar:sonar \
  							-Dsonar.projectKey=demoapp-project \
  							-Dsonar.host.url=http://localhost:9000 \
    						-Dsonar.login=sqp_6d8a6084617a7f37857ef0829bc5f9a6aff3d971"
     			    }
     			   }
     			}
     			
     			
     	  stage("Quality Gate"){
     	  steps{
          	timeout(time: 1, unit: 'HOURS') {
              def qg = waitForQualityGate()
              if (qg.status != 'OK') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
              }
          }
          }
      }

     		
     			
    }
}
