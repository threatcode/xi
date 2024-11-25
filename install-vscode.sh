#!/bin/bash

# Create .vscode directory if it doesn't exist
if [ ! -d ".vscode" ]; then
  mkdir .vscode
fi

# Create VSCode settings file (if not already present)
cat > .vscode/settings.json <<EOL
{
  "java.home": "/path/to/java/home",
  "java.jdt.ls.java.home": "/path/to/java/home",
  "java.project.sourcePaths": ["src"],
  "java.project.outputPath": "bin"
}
EOL

# Create VSCode launch configuration (if not already present)
cat > .vscode/launch.json <<EOL
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Launch Tomcat",
      "request": "launch",
      "mainClass": "org.apache.catalina.startup.Bootstrap",
      "args": "start",
      "projectName": "my-tomcat-project"
    }
  ]
}
EOL

# Create VSCode tasks configuration (if not already present)
cat > .vscode/tasks.json <<EOL
{
  "version": "2.0.0",
  "tasks": [
    {
      "label": "Build Tomcat",
      "type": "shell",
      "command": "./mvnw clean install",
      "group": {
        "kind": "build",
        "isDefault": true
      }
    }
  ]
}
EOL

echo "VSCode configuration files installed in .vscode"
