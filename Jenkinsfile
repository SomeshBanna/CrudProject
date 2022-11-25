pipeline {
    agent any

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
                 bat "mvn clean package"
                 
            }
            post{
            	failure{
            		emailext (
          subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
          body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
            <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
          recipientProviders: [[$class: 'DevelopersRecipientProvider']], 
        replyTo: '$DEFAULT_REPLYTO', 
        subject: '$DEFAULT_SUBJECT',
        to: '$DEFAULT_RECIPIENTS'
          
          
        )
        }
        }
            		
            
				
         
            
        }
    }
}
