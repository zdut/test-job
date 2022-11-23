pipeline {
    agent any

    environment {
        PERSONAL_ACCESS_TOKEN = credentials("github-token")
        PULL_PROTO = true
    }

    stages {
        stage("Pull Proto module") {
            when {
                expression {
                    return env.PULL_PROTO
                }
            }

            steps {
                script {
                    sh """
                        git config --file=.gitmodules submodule.protos.url https://${env.PERSONAL_ACCESS_TOKEN}@github.com/bind-sys/IWM_ProtoRepo.git
                        git submodule init
                        git submodule update
                    """
                }
            }
        }

        stage("Create .npmrc file") {
            steps {
                script {
                    sh '''
                        aws configure set region ap-southeast-1

                        CODEARTIFACT_AUTH_TOKEN=`aws codeartifact get-authorization-token --domain iwm --domain-owner 332470702708 --query authorizationToken --output text`
                        npm config set registry=https://iwm-332470702708.d.codeartifact.ap-southeast-1.amazonaws.com/npm/ScopedPackages/
                        npm config set //iwm-332470702708.d.codeartifact.ap-southeast-1.amazonaws.com/npm/ScopedPackages/:_authToken=$CODEARTIFACT_AUTH_TOKEN
                        npm config set //iwm-332470702708.d.codeartifact.ap-southeast-1.amazonaws.com/npm/ScopedPackages/:always-auth=true

                        echo "registry=https://iwm-332470702708.d.codeartifact.ap-southeast-1.amazonaws.com/npm/ScopedPackages/" > .npmrc
                        echo "//iwm-332470702708.d.codeartifact.ap-southeast-1.amazonaws.com/npm/ScopedPackages/:_authToken=$CODEARTIFACT_AUTH_TOKEN" >> .npmrc
                    '''
                }
            }
        }

        stage("Publish Checks - 1") {
            steps {
                publishChecks conclusion: 'FAILURE', detailsURL: 'https://jenkins.dev.trustme.bindsystems.tech/job/diddocumentservice-PR-check/', name: 'test-check-name', summary: 'test-check-summary', text: 'test-check-text', title: 'test-check-title'
            }
        }
    }
}
