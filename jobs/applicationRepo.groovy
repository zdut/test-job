def services = ["test-1", "test-2", "test-3"]

def createFolder(path, service) {
    folder(path + "/" + service) {
        displayName(service)
        description('Jobs that are automatically generated for ' + service)
    }
}

def generatedFolder = 'generated'
folder(generatedFolder) {
    displayName('Generated Jobs')
    description('Jobs that are automatically generated.')
}

services.each { service ->
    println "Creating application repo for: " + service

    createFolder(generatedFolder, service)
}