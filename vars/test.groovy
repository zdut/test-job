def call() {
    node {
        stage("Checkout") {
            deleteDir()
            sh """
                git clone https://github.com/zdut/test-job.git work --depth 1
            """
        }
        stage("Hello World") {
            sh """
                echo "Hello";
                whoami;
                pwd;
                ls;
            """
        }

        stage("Create folder") {
            jobDsl targets: 'work/jobs/*.groovy',
                    additionalParameters: [repos: ["test1", "test2", "test3"]]
        }
    }
}