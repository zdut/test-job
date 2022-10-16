def services = ["test-1", "test-2", "test-3"]

def createFolder(path, service) {
    def repoFolder = path + "/" + service
    folder(repoFolder) {
        displayName(service)
        description('Jobs that are automatically generated for ' + service)
    }

    return repoFolder
}

def createJob(repoFolder, service) {
    pipelineJob(repoFolder + "/" + service + "-dev") {
        definition {
            cps {
                script(readFileFromWorkspace("work/jobs/Jenkinsfile"))
                sandbox()
            }
        }
    }
}

def generatedFolder = 'generated'
folder(generatedFolder) {
    displayName('Generated Jobs')
    description('Jobs that are automatically generated.')
}

services.each { service ->
    println "Creating application repo for: " + service

    def repoFolder = createFolder(generatedFolder, service)

    createJob(repoFolder, service)
}