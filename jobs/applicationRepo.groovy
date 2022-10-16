def generatedFolder = 'generated'
folder(generatedFolder) {
    displayName('Generated Jobs')
    description('Jobs that are automatically generated.')
}

repos.each { repo ->
    println "Creating application repo for: " + repo
}