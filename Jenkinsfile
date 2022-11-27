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
        }
    }
}
