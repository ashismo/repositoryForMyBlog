#Install Redis for windows
Download https://github.com/MicrosoftArchive/redis/releases/download/win-3.2.100/Redis-x64-3.2.100.zip

Extract into some path.

Open folder.

Execute Server : redis-server.exe
Then execute client: redis-cli.exe

From the command prompt: set name Ashish
get name

It should print Ashish

So the setup is complete.

Default port: 6379


#Set value from Java check value from rest service

http://localhost:8010/rest/set
http://localhost:8010/rest/get
http://localhost:8010/rest/update
http://localhost:8010/rest/get

# Set value from Java and check value from redis CLI
keys "*"

# Delete all redis data
flushall

