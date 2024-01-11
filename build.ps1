# PowerShell Script

Write-Output "Building the React project..."

# Navigate to WebApp directory
Push-Location -Path .\WebApp

# Install npm dependencies and build the project
npm install  | out-null
npm run build  | out-null

# Return to the original directory
Pop-Location

Write-Output "React project built successfully!"

# Clear the contents of src\main\resources\static directory
$staticPath = '.\src\main\resources\static\*'
Remove-Item -Path $staticPath -Recurse -Force -ErrorAction Ignore

# Copy the contents of WebApp\build to src\main\resources\static
$sourcePath = '.\WebApp\build\*'
$destinationPath = '.\src\main\resources\static\'
Copy-Item -Path $sourcePath -Destination $destinationPath -Recurse -Force

Write-Output "React project copied to src\main\resources\static successfully!"
Write-Output "Building the Spring Boot project..."
# Build the project using Gradle
.\gradlew.bat build  | out-null
Write-Output "Spring Boot project built successfully!"

# Run the Java application
java -jar .\build\libs\TFG-1.0.0-RELEASE.jar
