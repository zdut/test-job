def call() {
    node {
        stage("Hello World") {
            sh """
                echo "Hello";
                whoami;
                pwd;
                ls;
            """
        }

        stage("Create folder") {
            jobDsl targets: 'jobs/*.groovy',
                    additionalParameters: [repos: ["test1", "test2", "test3"]]
        }
    }
}