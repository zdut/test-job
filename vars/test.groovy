def call() {
    node {
        stage("Hello World") {
            sh """
                echo "Hello";
                whoami;
                pwd;
            """
        }

        stage("Create folder") {
            jobDsl targets: 'work/jobs/*.groovy',
                    additionalParameters: [repos: ["test1", "test2", "test3"]]
        }
    }
}