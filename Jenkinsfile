pipeline
{
    agent any
    tools{
        maven 'maven'
    }
    stages
    {
        stage('Build')
        {
            steps
            {
                echo("Simulated dev build stage — in a real org's pipeline, this is where the application under test would be built and packaged before QA automation runs. This project targets a public demo application, so there is nothing of ours to build here.")
            }
        }
        stage("Deploy to QA"){
            steps{
                echo("Simulated deploy stage — represents the application under test being deployed to a QA environment before automation runs against it.")
            }
        }
        stage('Smoke Automation Tests') {
            steps {
                dir('smoke') {
                    script {
                        try {
                            git branch: 'main', url: 'https://github.com/Manish12588/Cucumber_BDD_FW.git'
                            sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml -Denv=qa"
                            // Default @smoke tag filter comes from TestRunner's
                            // hardcoded @CucumberOptions — no override needed here.
                        } catch (err) {
                            unstable("Smoke tests failed: ${err}")
                        }
                    }
                }
            }
        }
        stage('Publish Smoke Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'smoke/target/allure-results']]
                    ])
                }
            }
        }
        stage('Publish Smoke ChainTest Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'smoke/target/chaintest',
                                  reportFiles: 'Index.html',
                                  reportName: 'HTML Smoke ChainTest Report',
                                  reportTitles: ''])
            }
        }
        stage("Deploy to Stage"){
            steps{
                echo("Simulated deploy stage — represents the application under test being deployed to a staging environment before the regression suite runs.")
            }
        }
        stage('Regression Automation Tests') {
            steps {
                dir('regression') {
                    script {
                        try {
                            git branch: 'main', url: 'https://github.com/Manish12588/Cucumber_BDD_FW.git'
                            sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml -Dcucumber.filter.tags='@regression' -Denv=stage"
                            // Overrides TestRunner's hardcoded @smoke default at
                            // runtime, running all @regression-tagged scenarios instead.
                        } catch (err) {
                            unstable("Regression tests failed: ${err}")
                        }
                    }
                }
            }
        }
        stage('Publish Regression ChainTest Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'regression/target/chaintest',
                                  reportFiles: 'Index.html',
                                  reportName: 'HTML Regression ChainTest Report',
                                  reportTitles: ''])
            }
        }
        stage("Deploy to PROD"){
            steps{
                echo("Simulated deploy stage — represents promotion to production after both smoke and regression suites have passed.")
            }
        }
    }
}