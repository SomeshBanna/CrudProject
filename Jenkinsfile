pipeline {
    agent {label 'QA environment'}

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "MAVEN_HOME"
    }
      environment {

        NEXUS_VERSION = "nexus3"

        NEXUS_PROTOCOL = "http"

        NEXUS_URL = "localhost:8081"

        NEXUS_REPOSITORY = "java-app"

        NEXUS_CREDENTIAL_ID = "NEXUS_CRED"

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
    						-Dsonar.login=squ_9aa9701217794449eaf181b48f048ea535c5a9d7"
     			    }
     			   }
     			}
     			
     	stage('Quality Gate') {
           steps {
              timeout(time: 1, unit: 'HOURS') {
                 echo "before quality gate"
                waitForQualityGate abortPipeline: true
                	echo "after qg"
              }
            }
           
          }
     		
     	
     	  stage("Publish to Nexus Repository Manager") {

            steps {
					bat "maven package"
                script {
                	
                	
                    pom = readMavenPom file: "pom.xml";

                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");

                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"

                    artifactPath = filesByGlob[0].path;

                    artifactExists = fileExists artifactPath;

                    if(artifactExists) {

                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

                        nexusArtifactUploader(

                            nexusVersion: NEXUS_VERSION,

                            protocol: NEXUS_PROTOCOL,

                            nexusUrl: NEXUS_URL,

                            groupId: pom.groupId,

                            version: pom.version,

                            repository: NEXUS_REPOSITORY,

                            credentialsId: NEXUS_CREDENTIAL_ID,

                            artifacts: [

                                [artifactId: pom.artifactId,

                                classifier: '',

                                file: artifactPath,

                                type: pom.packaging],

                                [artifactId: pom.artifactId,

                                classifier: '',

                                file: "pom.xml",

                                type: "pom"]

                            ]

                        );

                    } else {

                        error "*** File: ${artifactPath}, could not be found";

                    }

                }

            }

        }
     	
     	
     	
     	

     			
    }
}
