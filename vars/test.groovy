def call() {
    node {
        stage ("Hello World") {
            sh """
                echo "Hello";
                whoami;
            """
        }
    }
}