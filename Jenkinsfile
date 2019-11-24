#!groovy
// following parameter will be passed by jenkins UI (build with parameters)
// server_name, branch_name
def service_name = 'GOjek';
def service_name_os = service_name
def user_name = 'automation'
node {
    stage ('init') {
        def directory = "${env.JENKINS}"
        checkout scm
        // sh 'git clone '
        sh 'echo ${branch_name}'
    }
stage('Maven Build') {
        ansiColor('xterm') {
            timeout(time: 300, unit: 'SECONDS') {
                withEnv(["JAVA_HOME=/opt/jdk-11.0.3+7", "PATH+MAVEN=${tool 'maven'}/bin:${env.JAVA_HOME}/bin"]) {
                        sh 'mvn clean test'
                }
            }
        }
    }
    
    stage("Cucumber Reports") {
	    steps{
	    cucumber buildStatus: "UNSTABLE",
	    fileIncludePattern: "**/cucumber.json",
	    jsonReportDirectory: 'target'
	    }
	}
}

